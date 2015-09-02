package alt.beanmapper.compile.converter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.compile.Arrays;
import alt.beanmapper.compile.property.PropertyAccessor;
import alt.beanmapper.runtime.ArraysRuntime;
import alt.beanmapper.support.CompileException;

/**
 * 
 * @author Albert Shift
 *
 */

public class ArrayToArrayPropertyConverter extends PropertyConverter implements Opcodes {

	private static final String ARRAYS_RUNTIME_TYPE = Type.getInternalName(ArraysRuntime.class);
	private static final String OBJECTARRAY_TYPE_DESC = Type.getDescriptor(Object[].class);
	private static final String ARRAYS_RUNTIME_COPYOF = "(" + OBJECTARRAY_TYPE_DESC + ")"
			+ OBJECTARRAY_TYPE_DESC;

	public ArrayToArrayPropertyConverter(PropertyAccessor srcPropertyAccessor,
			PropertyAccessor destPropertyAccessor) {
		super(srcPropertyAccessor, destPropertyAccessor);
	}

	@Override
	public void convert(MethodVisitor mv) {

		if (srcPropertyAccessor.getPropertyClass() == destPropertyAccessor.getPropertyClass()) {

			if (Arrays.isPrimitiveArray(srcPropertyAccessor.getPropertyType())) {

				mv.visitMethodInsn(INVOKESTATIC, ARRAYS_RUNTIME_TYPE, "copyOf", "("
						+ srcPropertyAccessor.getPropertyType().getDescriptor() + ")"
						+ destPropertyAccessor.getPropertyType().getDescriptor(), false);

			} else if (Arrays.isWrapperArray(srcPropertyAccessor.getPropertyType())) {

				mv.visitMethodInsn(INVOKESTATIC, ARRAYS_RUNTIME_TYPE, "copyOf", ARRAYS_RUNTIME_COPYOF, false);
				mv.visitTypeInsn(CHECKCAST, destPropertyAccessor.getPropertyType().getDescriptor());

			} else {
				throw new CompileException("do not know how to convert " + srcPropertyAccessor.getPropertyClass()
						+ " to " + destPropertyAccessor.getPropertyClass() + " for property '"
						+ srcPropertyAccessor.getPropertyName() + "' in " + srcPropertyAccessor.getBeanClassName());
			}

		} else {

			if (Arrays.isBoxing(srcPropertyAccessor.getPropertyType(), destPropertyAccessor.getPropertyType())) {

				mv.visitMethodInsn(INVOKESTATIC, ARRAYS_RUNTIME_TYPE, "box", "("
						+ srcPropertyAccessor.getPropertyType().getDescriptor() + ")"
						+ destPropertyAccessor.getPropertyType().getDescriptor(), false);

			} else if (Arrays.isUnboxing(srcPropertyAccessor.getPropertyType(),
					destPropertyAccessor.getPropertyType())) {

				mv.visitMethodInsn(INVOKESTATIC, ARRAYS_RUNTIME_TYPE, "unbox", "("
						+ srcPropertyAccessor.getPropertyType().getDescriptor() + ")"
						+ destPropertyAccessor.getPropertyType().getDescriptor(), false);

			} else {
				throw new CompileException("do not know how to convert " + srcPropertyAccessor.getPropertyClass()
						+ " to " + destPropertyAccessor.getPropertyClass() + " for property '"
						+ srcPropertyAccessor.getPropertyName() + "' in " + srcPropertyAccessor.getBeanClassName());
			}

		}

	}
}
