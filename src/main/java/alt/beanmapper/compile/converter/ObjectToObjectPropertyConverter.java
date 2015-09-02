package alt.beanmapper.compile.converter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.compile.Autoboxing;
import alt.beanmapper.compile.TypeCast;
import alt.beanmapper.compile.property.PropertyAccessor;
import alt.beanmapper.mapping.TypeCastStrategy;
import alt.beanmapper.support.CompileException;

/**
 * 
 * @author Albert Shift
 *
 */

public class ObjectToObjectPropertyConverter extends PropertyConverter implements Opcodes {

	private final TypeCastStrategy typeCastStrategy;

	public ObjectToObjectPropertyConverter(PropertyAccessor srcPropertyAccessor,
			PropertyAccessor destPropertyAccessor, TypeCastStrategy typeCastStrategy) {
		super(srcPropertyAccessor, destPropertyAccessor);

		this.typeCastStrategy = typeCastStrategy;
	}

	@Override
	public void convert(MethodVisitor mv) {

		if (Autoboxing.generateAutoboxing(mv, srcPropertyAccessor.getPropertyType(),
				destPropertyAccessor.getPropertyType())) {
			return;
		}

		Type srcPrimitiveType = Autoboxing.findPrimitiveType(srcPropertyAccessor.getPropertyType());
		Type destPrimitiveType = Autoboxing.findPrimitiveType(destPropertyAccessor.getPropertyType());

		if (srcPrimitiveType != null && destPrimitiveType != null) {

			typeCast(mv, srcPrimitiveType, destPrimitiveType);

		} else {

			throw new CompileException("do not know how to convert " + srcPropertyAccessor.getPropertyClass()
					+ " to " + destPropertyAccessor.getPropertyClass() + " for property '"
					+ srcPropertyAccessor.getPropertyName() + "' in " + srcPropertyAccessor.getBeanClassName());
		}

	}

	private void typeCast(MethodVisitor mv, Type srcPrimitiveType, Type destPrimitiveType) {

		if (!srcPropertyAccessor.getPropertyType().equals(srcPrimitiveType)) {

			if (!Autoboxing.generateAutoboxing(mv, srcPropertyAccessor.getPropertyType(), srcPrimitiveType)) {
				throw new CompileException("can not generate autoboxing from "
						+ srcPropertyAccessor.getPropertyType() + " to " + srcPrimitiveType.getClassName()
						+ " for property '" + srcPropertyAccessor.getPropertyName() + "' in "
						+ srcPropertyAccessor.getBeanClassName());
			}

		}

		if (!TypeCast.generateCast(mv, srcPrimitiveType, destPrimitiveType, typeCastStrategy)) {
			throw new CompileException("do not know how to convert from " + srcPropertyAccessor.getPropertyClass()
					+ " to " + destPropertyAccessor.getPropertyClass() + " for property '"
					+ srcPropertyAccessor.getPropertyName() + "' in " + srcPropertyAccessor.getBeanClassName());
		}

		if (!destPropertyAccessor.getPropertyType().equals(destPrimitiveType)) {

			if (!Autoboxing.generateAutoboxing(mv, destPrimitiveType, destPropertyAccessor.getPropertyType())) {
				throw new CompileException("can not generate autoboxing from " + destPrimitiveType.getClassName()
						+ " to " + destPropertyAccessor.getPropertyClass() + " for property '"
						+ destPropertyAccessor.getPropertyName() + "' in " + destPropertyAccessor.getBeanClassName());
			}

		}
	}

}
