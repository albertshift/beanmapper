package alt.beanmapper.compile.converter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.compile.Autoboxing;
import alt.beanmapper.compile.MapperClassCompiler;
import alt.beanmapper.compile.property.PropertyAccessor;
import alt.beanmapper.converter.Converter;
import alt.beanmapper.runtime.ConverterRuntime;
import alt.beanmapper.support.CompileException;
import alt.beanmapper.util.ReflectionUtil;

/**
 * 
 * @author Albert Shift
 *
 */

public class CustomObjectToObjectPropertyConverter extends PropertyConverter implements Opcodes {

	private static final String CONVERT_METHOD = "convert";
	private static final String CONVERTER_INTERFACE_DESC = Type.getDescriptor(Converter.class);
	private static final String CONVERTER_RUNTIME_TYPE = Type.getInternalName(ConverterRuntime.class);

	private final MapperClassCompiler mapperClassCompiler;
	private final Class<? extends Converter<?, ?>> converterClass;

	public CustomObjectToObjectPropertyConverter(MapperClassCompiler mapperClassCompiler,
			PropertyAccessor srcPropertyAccessor, PropertyAccessor destPropertyAccessor,
			Class<? extends Converter<?, ?>> converterClass) {
		super(srcPropertyAccessor, destPropertyAccessor);

		this.mapperClassCompiler = mapperClassCompiler;
		this.converterClass = converterClass;
	}

	@Override
	public void convert(MethodVisitor mv) {

		Type srcBoxedType = Autoboxing.box(srcPropertyAccessor.getPropertyType());
		Type destBoxedType = Autoboxing.box(destPropertyAccessor.getPropertyType());

		validateConverter(srcBoxedType, destBoxedType);

		String converterFieldName = mapperClassCompiler.getConverterFieldName(converterClass);
		String converterDesc = Type.getDescriptor(converterClass);

		if (!srcPropertyAccessor.getPropertyType().equals(srcBoxedType)) {

			if (!Autoboxing.generateAutoboxing(mv, srcPropertyAccessor.getPropertyType(), srcBoxedType)) {
				throw new CompileException("can not generate autoboxing from "
						+ srcPropertyAccessor.getPropertyType() + " to " + srcBoxedType.getClassName()
						+ " for property '" + srcPropertyAccessor.getPropertyName() + "' in "
						+ srcPropertyAccessor.getBeanClassName());

			}

		}

		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, mapperClassCompiler.getImplClassInternalName(), converterFieldName,
				converterDesc);

		mv.visitMethodInsn(INVOKESTATIC, CONVERTER_RUNTIME_TYPE, CONVERT_METHOD, "(Ljava/lang/Object;"
				+ CONVERTER_INTERFACE_DESC + ")Ljava/lang/Object;", false);

		mv.visitTypeInsn(CHECKCAST, destBoxedType.getInternalName());

		if (!destPropertyAccessor.getPropertyType().equals(destBoxedType)) {

			if (!Autoboxing.generateAutoboxing(mv, destBoxedType, destPropertyAccessor.getPropertyType())) {
				throw new CompileException("can not generate autoboxing from " + destBoxedType.getClassName()
						+ " to " + destPropertyAccessor.getPropertyType() + " for property '"
						+ destPropertyAccessor.getPropertyName() + "' in " + destPropertyAccessor.getBeanClassName());

			}

		}

	}

	private void validateConverter(Type srcBoxedType, Type destBoxedType) {
		Class<?>[] genericTypes = ReflectionUtil.findGenericTypes(converterClass, Converter.class);
		if (genericTypes != null) {
			if (genericTypes.length != 2) {
				throw new CompileException("invalid converter " + converterClass);
			}
			Class<?> srcGenericClass = genericTypes[0];
			Class<?> destGenericClass = genericTypes[1];

			if (!srcGenericClass.getName().equals(srcBoxedType.getClassName())) {
				throw new CompileException("converter " + converterClass.getName() + " has generic source type "
						+ srcGenericClass + ", but property type is " + srcBoxedType.getClassName() + " for property '"
						+ srcPropertyAccessor.getPropertyName() + "' in " + srcPropertyAccessor.getBeanClassName());
			}

			if (!destGenericClass.getName().equals(destBoxedType.getClassName())) {
				throw new CompileException("converter " + converterClass.getName() + " has generic destination type "
						+ destGenericClass + ", but property type is " + destBoxedType.getClassName() + " for property '"
						+ destPropertyAccessor.getPropertyName() + "' in " + destPropertyAccessor.getBeanClassName());
			}

		}
	}

}
