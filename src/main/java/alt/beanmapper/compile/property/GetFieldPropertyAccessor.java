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

public class GetFieldPropertyAccessor extends AbstractPropertyAccessor implements Opcodes {

	private final PropertyDescription prop;

	public GetFieldPropertyAccessor(Type beanType, PropertyDescription prop) {
		super(beanType, prop.getFieldType());
		this.prop = prop;
	}

	@Override
	public void implement(MethodVisitor mv) {
		mv.visitFieldInsn(GETFIELD, beanType.getInternalName(), prop.getName(), getPropertyType().getDescriptor());
	}

	@Override
	public String getPropertyName() {
		return prop.getName();
	}

}
