package alt.beanmapper.runtime;

/**
 * 
 * @author Albert Shift
 *
 */

public final class BooleanTypeCastRuntime {

	private BooleanTypeCastRuntime() {
	}

	public static byte castBooleanToByte(boolean b) {
		return b ? (byte) 255 : (byte) 0;
	}

	public static char castBooleanToChar(boolean b) {
		return b ? '?' : '0';
	}

	public static short castBooleanToShort(boolean b) {
		return b ? (short) -1 : (short) 0;
	}

	public static int castBooleanToInt(boolean b) {
		return b ? -1 : 0;
	}

	public static long castBooleanToLong(boolean b) {
		return b ? -1L : 0L;
	}

	public static float castBooleanToFloat(boolean b) {
		return b ? Float.MAX_VALUE : 0.0f;
	}

	public static double castBooleanToDouble(boolean b) {
		return b ? Double.MAX_VALUE : 0.0;
	}

	public static boolean castByteToBoolean(byte b) {
		return b != 0;
	}

	public static boolean castCharToBoolean(char c) {
		return c != '0';
	}

	public static boolean castShortToBoolean(short s) {
		return s != 0;
	}

	public static boolean castIntToBoolean(int i) {
		return i != 0;
	}

	public static boolean castLongToBoolean(long l) {
		return l != 0L;
	}

	public static boolean castFloatToBoolean(float f) {
		return f != 0f;
	}

	public static boolean castDoubleToBoolean(double d) {
		return d != 0.0;
	}

}
