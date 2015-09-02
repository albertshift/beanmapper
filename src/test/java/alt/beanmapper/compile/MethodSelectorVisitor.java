package alt.beanmapper.compile;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 
 * @author Albert Shift
 *
 */

public class MethodSelectorVisitor extends ClassVisitor {

	private final String methodName;

	public MethodSelectorVisitor(ClassVisitor cv, String methodName) {
		super(Opcodes.ASM5, cv);
		this.methodName = methodName;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		if (methodName.equals(name)) {

			return super.visitMethod(access, name, desc, signature, exceptions);
		}

		return null;
	}
}
