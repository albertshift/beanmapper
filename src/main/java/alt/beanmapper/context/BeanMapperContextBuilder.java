package alt.beanmapper.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import alt.beanmapper.converter.Converter;
import alt.beanmapper.mapping.MapperClass;
import alt.beanmapper.support.MappingException;
import alt.beanmapper.util.ClassLoaderUtil;
import alt.beanmapper.util.PackageUtil;

/**
 * 
 * @author Albert Shift
 *
 */

public class BeanMapperContextBuilder {

	private static final Logger log = LoggerFactory.getLogger(BeanMapperContextBuilder.class);

	private final Set<Class<?>> mappers = new HashSet<Class<?>>();
	private final Map<Class<?>, Converter<?, ?>> converters = new HashMap<Class<?>, Converter<?, ?>>();

	private ClassLoader classLoader;
	private String classNamePostfix = "Impl";
	private boolean instantiateConverters = false;

	public BeanMapperContextBuilder() {
		classLoader = ClassLoaderUtil.getCurrentClassLoader();
	}

	public BeanMapperContextBuilder withClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		return this;
	}

	public BeanMapperContextBuilder instantiateConverters() {
		this.instantiateConverters = true;
		return this;
	}

	public BeanMapperContextBuilder notInstantiateConverters() {
		this.instantiateConverters = false;
		return this;
	}

	public BeanMapperContextBuilder withPostfix(String classNamePostfix) {
		this.classNamePostfix = classNamePostfix;
		return this;
	}

	public BeanMapperContextBuilder addConverter(Converter<?, ?> converter) {
		converters.put(converter.getClass(), converter);
		return this;
	}

	public BeanMapperContextBuilder addMapperInterface(Class<?> mapperInterface) {
		mappers.add(mapperInterface);
		return this;
	}

	public BeanMapperContextBuilder addPackage(String packageName) throws ClassNotFoundException {

		Set<Class<?>> allClasses = PackageUtil.discoveryPackage(packageName, classLoader);

		for (Class<?> currentClass : allClasses) {

			if (currentClass.getAnnotation(MapperClass.class) != null) {

				if (!currentClass.isInterface()) {
					throw new MappingException("mapper can be only interface " + currentClass);
				}

				addMapperInterface(currentClass);

			}

		}

		return this;

	}

	public BeanMapperContext build() {
		return new DefaultBeanMapperContext(mappers, converters, classLoader, classNamePostfix, instantiateConverters);
	}

}
