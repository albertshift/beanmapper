package alt.beanmapper.compile;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Textifier;

/**
 * 
 * @author Albert Shift
 *
 */

public class MethodSelectorTextifier extends Textifier {

	private String methodName;

	public MethodSelectorTextifier(String methodName) {
		super(Opcodes.ASM5);
		this.methodName = methodName;
	}

	@Override
	public Textifier visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		if (name.equals(methodName)) {

			return super.visitMethod(access, name, desc, signature, exceptions);

		}

		else {

			return null;
		}

	}

}
