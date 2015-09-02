package alt.beanmapper.context;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import alt.beanmapper.compile.Compiler;
import alt.beanmapper.context.ClassLoaderWrapper.Fallback;
import alt.beanmapper.converter.Converter;
import alt.beanmapper.mapping.MappingMapperClassDescription;
import alt.beanmapper.mapping.MappingMetadataContext;
import alt.beanmapper.support.ConverterNotFoundException;
import alt.beanmapper.support.ReflectionAccessException;

/**
 * 
 * @author Albert Shift
 *
 */

public class DefaultBeanMapperContext implements BeanMapperContext {

	private final Map<Class<?>, Converter<?, ?>> converters = new HashMap<Class<?>, Converter<?, ?>>();

	private final Map<Class<?>, Object> context = new HashMap<Class<?>, Object>();

	private final ClassLoaderWrapper classLoaderWrapper;
	private final Compiler compiler;

	private final boolean instantiateConverters;

	public DefaultBeanMapperContext(Set<Class<?>> mappers, Map<Class<?>, Converter<?, ?>> converters,
			ClassLoader classLoader, String classNamePostfix, boolean instantiateConverters) {

		this.instantiateConverters = instantiateConverters;

		this.converters.putAll(converters);

		MetadataContext metadataContext = new MappingMetadataContext();

		this.classLoaderWrapper = new ClassLoaderWrapper(classLoader);
		this.compiler = new Compiler(metadataContext);

		for (final Class<?> mapperInterface : mappers) {

			final MapperClassDescription mapperClassDescription = new MappingMapperClassDescription(
					mapperInterface, classNamePostfix);

			Class<?> implClass = classLoaderWrapper.loadClass(mapperClassDescription.getImplClassName(),
					new Fallback() {

						@Override
						public Class<?> fallback(String name) {
							byte[] byteCode = compiler.compileMapper(mapperClassDescription);
							return classLoaderWrapper.defineClass(name, byteCode, 0, byteCode.length);
						}

					});

			Object implInstance = instantiateClass(implClass);

			context.put(mapperInterface, implInstance);

		}

		doPostProcess();

		metadataContext.clear();

	}

	@SuppressWarnings("unchecked")
	@Override
	public <M> M getMapperService(Class<M> mapperInterface) {
		return (M) context.get(mapperInterface);
	}

	protected void doPostProcess() {

		for (Map.Entry<Class<?>, Object> entry : context.entrySet()) {

			Object beanMapper = entry.getValue();

			injectConverters(beanMapper);

		}

	}

	protected Object instantiateClass(Class<?> clazz) {

		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new ReflectionAccessException("error in contruction of " + clazz, e);
		} catch (IllegalAccessException e) {
			throw new ReflectionAccessException("no access to default constructor in " + clazz, e);
		}

	}

	private void injectConverters(Object beanMapper) {

		Class<?> beanMapperClass = beanMapper.getClass();

		for (Field field : beanMapperClass.getDeclaredFields()) {

			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			Class<?> fieldClass = field.getType();

			if (Converter.class.isAssignableFrom(fieldClass)) {

				field.setAccessible(true);

				Converter<?, ?> converter = getOrCreateConverter(fieldClass);

				injectFieldDirect(field, beanMapper, converter);

			}

		}

	}

	private void injectFieldDirect(Field field, Object bean, Object value) {
		field.setAccessible(true);
		try {
			field.set(bean, value);
		} catch (IllegalArgumentException e) {
			throw new ReflectionAccessException("fail to inject " + value + " to the field " + field.getName()
					+ " in " + bean.getClass(), e);
		} catch (IllegalAccessException e) {
			throw new ReflectionAccessException("no permissions to inject " + value + " to the field "
					+ field.getName() + " in " + bean.getClass(), e);
		}

	}

	private Converter<?, ?> getOrCreateConverter(Class<?> converterClass) {
		Converter<?, ?> converter = converters.get(converterClass);

		if (converter == null) {

			if (instantiateConverters) {
				converter = (Converter<?, ?>) instantiateClass(converterClass);
				converters.put(converterClass, converter);
			} else {
				throw new ConverterNotFoundException("converter not found " + converterClass
						+ ", please add all converters in builder");
			}

		}

		return converter;
	}

}
