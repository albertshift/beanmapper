package alt.beanmapper.compile;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.mapping.MappingConstants;
import alt.beanmapper.mapping.TypeCastStrategy;
import alt.beanmapper.runtime.BooleanTypeCastRuntime;

/**
 * 
 * @author Albert Shift
 *
 */

public final class TypeCast implements Opcodes {

	private static final String BOOLEAN_TYPECAST_RUNTIME_TYPE = Type
			.getInternalName(BooleanTypeCastRuntime.class);

	private static final NothingCast NO_CAST = new NothingCast();

	private static Map<String, Cast> typeCastMap = new HashMap<String, Cast>();

	static {

		/**
		 * all to boolean
		 */

		typeCastMap.put("ZZ", NO_CAST);
		typeCastMap.put("BZ", new BooleanRuntimeCast("castByteToBoolean", "(B)Z"));
		typeCastMap.put("CZ", new BooleanRuntimeCast("castCharToBoolean", "(C)Z"));
		typeCastMap.put("SZ", new BooleanRuntimeCast("castShortToBoolean", "(S)Z"));
		typeCastMap.put("IZ", new BooleanRuntimeCast("castIntToBoolean", "(I)Z"));
		typeCastMap.put("JZ", new BooleanRuntimeCast("castLongToBoolean", "(J)Z"));
		typeCastMap.put("FZ", new BooleanRuntimeCast("castFloatToBoolean", "(F)Z"));
		typeCastMap.put("DZ", new BooleanRuntimeCast("castDoubleToBoolean", "(D)Z"));

		/**
		 * all to byte
		 */

		typeCastMap.put("ZB", new BooleanRuntimeCast("castBooleanToByte", "(Z)B"));
		typeCastMap.put("BB", NO_CAST);
		typeCastMap.put("CB", new InsnCast(I2B));
		typeCastMap.put("SB", new InsnCast(I2B));
		typeCastMap.put("IB", new InsnCast(I2B));
		typeCastMap.put("JB", new TwoInsnCast(L2I, I2B));
		typeCastMap.put("FB", new TwoInsnCast(F2I, I2B));
		typeCastMap.put("DB", new TwoInsnCast(D2I, I2B));

		/**
		 * all to char
		 */

		typeCastMap.put("ZC", new BooleanRuntimeCast("castBooleanToChar", "(Z)C"));
		typeCastMap.put("BC", new InsnCast(I2C));
		typeCastMap.put("CC", NO_CAST);
		typeCastMap.put("SC", new InsnCast(I2C));
		typeCastMap.put("IC", new InsnCast(I2C));
		typeCastMap.put("JC", new TwoInsnCast(L2I, I2C));
		typeCastMap.put("FC", new TwoInsnCast(F2I, I2C));
		typeCastMap.put("DC", new TwoInsnCast(D2I, I2C));

		/**
		 * all to short
		 */

		typeCastMap.put("ZS", new BooleanRuntimeCast("castBooleanToShort", "(Z)S"));
		typeCastMap.put("BS", new InsnCast(I2S, true));
		typeCastMap.put("CS", new InsnCast(I2S));
		typeCastMap.put("SS", NO_CAST);
		typeCastMap.put("IS", new InsnCast(I2S));
		typeCastMap.put("JS", new TwoInsnCast(L2I, I2S));
		typeCastMap.put("FS", new TwoInsnCast(F2I, I2S));
		typeCastMap.put("DS", new TwoInsnCast(D2I, I2S));

		/**
		 * all to int
		 */

		typeCastMap.put("ZI", new BooleanRuntimeCast("castBooleanToInt", "(Z)I"));
		typeCastMap.put("BI", NO_CAST);
		typeCastMap.put("CI", NO_CAST);
		typeCastMap.put("SI", NO_CAST);
		typeCastMap.put("II", NO_CAST);
		typeCastMap.put("JI", new InsnCast(L2I));
		typeCastMap.put("FI", new InsnCast(F2I));
		typeCastMap.put("DI", new InsnCast(D2I));

		/**
		 * all to long
		 */

		typeCastMap.put("ZJ", new BooleanRuntimeCast("castBooleanToLong", "(Z)J"));
		typeCastMap.put("BJ", new InsnCast(I2L, true));
		typeCastMap.put("CJ", new InsnCast(I2L, true));
		typeCastMap.put("SJ", new InsnCast(I2L, true));
		typeCastMap.put("IJ", new InsnCast(I2L, true));
		typeCastMap.put("JJ", NO_CAST);
		typeCastMap.put("FJ", new InsnCast(F2L));
		typeCastMap.put("DJ", new InsnCast(D2L));

		/**
		 * all to float
		 */

		typeCastMap.put("ZF", new BooleanRuntimeCast("castBooleanToFloat", "(Z)F"));
		typeCastMap.put("BF", new InsnCast(I2F, true));
		typeCastMap.put("CF", new InsnCast(I2F, true));
		typeCastMap.put("SF", new InsnCast(I2F, true));
		typeCastMap.put("IF", new InsnCast(I2F, true));
		typeCastMap.put("JF", new InsnCast(L2F, true));
		typeCastMap.put("FF", NO_CAST);
		typeCastMap.put("DF", new InsnCast(D2F));

		/**
		 * all to double
		 */

		typeCastMap.put("ZD", new BooleanRuntimeCast("castBooleanToDouble", "(Z)D"));
		typeCastMap.put("BD", new InsnCast(I2D, true));
		typeCastMap.put("CD", new InsnCast(I2D, true));
		typeCastMap.put("SD", new InsnCast(I2D, true));
		typeCastMap.put("ID", new InsnCast(I2D, true));
		typeCastMap.put("JD", new InsnCast(L2D, true));
		typeCastMap.put("FD", new InsnCast(F2D, true));
		typeCastMap.put("DD", NO_CAST);

	}

	private TypeCast() {
	}

	public static boolean generateCast(MethodVisitor mv, Type srcPrimitiveType, Type destPrimitiveType,
			TypeCastStrategy strategy) {
		Cast cast = typeCastMap.get(srcPrimitiveType.getDescriptor() + destPrimitiveType.getDescriptor());
		if (cast != null && canApply(cast, strategy)) {
			cast.visitInst(mv);
			return true;
		}
		return false;
	}

	public static boolean canApply(Cast cast, TypeCastStrategy strategy) {

		if (cast == NO_CAST) {
			return true;
		}

		switch (strategy) {

		case DEFAULT:
			return canApply(cast, MappingConstants.DEFAULT_TYPECAST_STRATEGY);

		case NO_CAST:
			return false;

		case JAVA_AUTO:
			if (cast instanceof InsnCast) {
				return ((InsnCast) cast).isJavaAuto();
			}
			return false;

		case NO_BOOLEAN:
			return !(cast instanceof BooleanRuntimeCast);

		case ALL:
			return true;
		}

		return false;
	}

	public interface Cast extends Opcodes {

		void visitInst(MethodVisitor mv);

	}

	public static class NothingCast implements Cast {

		@Override
		public void visitInst(MethodVisitor mv) {
		}

	}

	public static class BooleanRuntimeCast implements Cast {

		private final String methodName;
		private final String methodDesc;

		public BooleanRuntimeCast(String methodName, String methodDesc) {
			this.methodName = methodName;
			this.methodDesc = methodDesc;
		}

		public void visitInst(MethodVisitor mv) {
			mv.visitMethodInsn(INVOKESTATIC, BOOLEAN_TYPECAST_RUNTIME_TYPE, methodName, methodDesc, false);
		}

	}

	public static class InsnCast implements Cast {

		private final int opcode;
		private final boolean javaAuto;

		public InsnCast(int opcode) {
			this.opcode = opcode;
			this.javaAuto = false;
		}

		public InsnCast(int opcode, boolean javaAuto) {
			this.opcode = opcode;
			this.javaAuto = javaAuto;
		}

		public boolean isJavaAuto() {
			return javaAuto;
		}

		public void visitInst(MethodVisitor mv) {
			mv.visitInsn(opcode);
		}

	}

	public static class TwoInsnCast implements Cast {

		private final int firstOpcode;
		private final int secondOpcode;

		public TwoInsnCast(int firstOpcode, int secondOpcode) {
			this.firstOpcode = firstOpcode;
			this.secondOpcode = secondOpcode;
		}

		public void visitInst(MethodVisitor mv) {
			mv.visitInsn(firstOpcode);
			mv.visitInsn(secondOpcode);
		}

	}

}
