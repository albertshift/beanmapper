package alt.beanmapper.runtime;

/**
 * 
 * @author Albert Shift
 *
 */

public final class AutoboxingRuntime {

	private AutoboxingRuntime() {
	}

	public static boolean unbox(Boolean val) {
		if (val != null) {
			return val.booleanValue();
		} else {
			return false;
		}
	}

	public static byte unbox(Byte val) {
		if (val != null) {
			return val.byteValue();
		} else {
			return 0;
		}
	}

	public static char unbox(Character val) {
		if (val != null) {
			return val.charValue();
		} else {
			return '0';
		}
	}

	public static short unbox(Short val) {
		if (val != null) {
			return val.shortValue();
		} else {
			return 0;
		}
	}

	public static int unbox(Integer val) {
		if (val != null) {
			return val.intValue();
		} else {
			return 0;
		}
	}

	public static long unbox(Long val) {
		if (val != null) {
			return val.longValue();
		} else {
			return 0L;
		}
	}

	public static float unbox(Float val) {
		if (val != null) {
			return val.floatValue();
		} else {
			return 0f;
		}
	}

	public static double unbox(Double val) {
		if (val != null) {
			return val.doubleValue();
		} else {
			return 0.0;
		}
	}
}
