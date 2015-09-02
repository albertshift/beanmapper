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

public class GetterPropertyAccessor extends AbstractPropertyAccessor implements Opcodes {

	private final PropertyDescription prop;

	public GetterPropertyAccessor(Type beanType, PropertyDescription prop) {
		super(beanType, prop.getGetterType());
		this.prop = prop;
	}

	@Override
	public void implement(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, beanType.getInternalName(), prop.getGetterName(), "()"
				+ getPropertyType().getDescriptor(), false);
	}

	@Override
	public String getPropertyName() {
		return prop.getName();
	}

}
