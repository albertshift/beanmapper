package alt.beanmapper.compile;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.compile.converter.PropertyConverterFactory;
import alt.beanmapper.compile.options.MapperOptions;
import alt.beanmapper.compile.property.PropertyAccessorFactory;
import alt.beanmapper.context.PropertyDescription;
import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;
import alt.beanmapper.support.MappingException;
import alt.beanmapper.util.ReflectionUtil;

/**
 * 
 * @author Albert Shift
 *
 */

public final class MapperCompiler implements Opcodes {

	private final MapperClassCompiler mapperClassCompiler;

	private final String methodName;

	private final Type srcType;
	private final Type destType;

	private final Map<String, PropertyDescription> srcProps;
	private final Map<String, PropertyDescription> destProps;

	private final MapperOptions mapperOptions;

	private final PropertyAccessorFactory propertyAccessorFactory;
	private final PropertyConverterFactory propertyConverterFactory;

	public MapperCompiler(MapperClassCompiler mapperClassCompiler, Method m) {

		this.mapperClassCompiler = mapperClassCompiler;
		this.methodName = m.getName();

		if (m.getParameterTypes().length != 1) {
			throw new MappingException("invalid method in interface " + m);
		}

		Class<?> srcClass = m.getParameterTypes()[0];
		Class<?> destClass = m.getReturnType();

		if (!ReflectionUtil.hasDefaultPublicConstructor(destClass)) {
			throw new MappingException("return type does not have default public constructor " + destClass);
		}

		this.srcType = Type.getType(srcClass);
		this.destType = Type.getType(destClass);

		this.srcProps = indexProperties(mapperClassCompiler.getMetadataContext().getProperties(srcClass));
		this.destProps = indexProperties(mapperClassCompiler.getMetadataContext().getProperties(destClass));

		Mapper mapper = m.getAnnotation(Mapper.class);
		this.mapperOptions = new MapperOptions(mapperClassCompiler.getMapperServiceOptions(), mapper);

		this.propertyAccessorFactory = new PropertyAccessorFactory(mapperClassCompiler, srcType, destType);
		this.propertyConverterFactory = new PropertyConverterFactory(mapperClassCompiler);

		mapperClassCompiler.addInnerClassIfNeeded(srcClass);
		mapperClassCompiler.addInnerClassIfNeeded(destClass);

	}

	public MethodVisitor startImplementation(ClassWriter cv) {

		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, methodName,
				"(" + srcType.getDescriptor() + ")" + destType.getDescriptor(), null, null);
		mv.visitCode();

		mv.visitTypeInsn(NEW, destType.getInternalName());
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, destType.getInternalName(), "<init>", "()V", false);
		mv.visitVarInsn(ASTORE, 2);

		return mv;
	}

	public void endImplementation(MethodVisitor mv) {

		mv.visitVarInsn(ALOAD, 2);
		mv.visitInsn(ARETURN);
		mv.visitMaxs(2, 3);
		mv.visitEnd();

	}

	public void copyProperties(MethodVisitor mv) {

		for (Property prop : mapperOptions.getProperties()) {

			String srcPropName = prop.src();
			String destPropName = prop.dest();

			if (destPropName == null || destPropName.isEmpty()) {
				destPropName = srcPropName;
			}

			PropertyDescription srcProp = srcProps.get(srcPropName);
			PropertyDescription destProp = destProps.get(destPropName);

			if (srcProp == null) {
				throw new MappingException("source property '" + srcPropName + "' not found in "
						+ srcType.getClassName() + " for " + prop);
			}

			if (destProp == null) {
				throw new MappingException("destination property '" + destPropName + "' not found in "
						+ destType.getClassName() + " for " + prop);
			}

			CopyPropertyCompiler copyPropertyCompiler = new CopyPropertyCompiler(srcProp, destProp, prop,
					mapperOptions);
			copyPropertyCompiler.implement(mv, propertyAccessorFactory, propertyConverterFactory);

		}

	}

	private static Map<String, PropertyDescription> indexProperties(PropertyDescription[] props) {
		Map<String, PropertyDescription> map = new HashMap<String, PropertyDescription>();
		for (PropertyDescription prop : props) {
			map.put(prop.getName(), prop);
		}
		return map;
	}

}
