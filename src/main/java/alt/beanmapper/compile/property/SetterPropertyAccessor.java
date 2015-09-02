package alt.beanmapper.compile.property;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.context.PropertyDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class SetterPropertyAccessor extends AbstractPropertyAccessor implements Opcodes {

	private final PropertyDescription prop;

	public SetterPropertyAccessor(Type beanType, PropertyDescription prop) {
		super(beanType, prop.getSetterType());
		this.prop = prop;
	}

	@Override
	public void implement(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, beanType.getInternalName(), prop.getSetterName(), "("
				+ getPropertyType().getDescriptor() + ")V", false);
	}

	@Override
	public String getPropertyName() {
		return prop.getName();
	}

}
