package alt.beanmapper.runtime;

import java.util.Arrays;

/**
 * 
 * @author Albert Shift
 *
 */

public final class ArraysRuntime {

	public static <T> T[] copyOf(T[] src) {
		if (src != null) {
			return (T[]) Arrays.copyOf(src, src.length);
		}
		return null;
	}

	public static final Boolean[] box(boolean[] src) {
		int len = src.length;
		Boolean[] array = new Boolean[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final boolean[] unbox(Boolean[] src) {
		if (src != null) {
			int len = src.length;
			boolean[] array = new boolean[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final boolean[] copyOf(boolean[] src) {
		return Arrays.copyOf(src, src.length);
	}

	public static final Byte[] box(byte[] src) {
		int len = src.length;
		Byte[] array = new Byte[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final byte[] unbox(Byte[] src) {
		if (src != null) {
			int len = src.length;
			byte[] array = new byte[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final byte[] copyOf(byte[] src) {
		return Arrays.copyOf(src, src.length);
	}

	public static final Character[] box(char[] src) {
		int len = src.length;
		Character[] array = new Character[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final char[] unbox(Character[] src) {
		if (src != null) {
			int len = src.length;
			char[] array = new char[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final char[] copyOf(char[] src) {
		return Arrays.copyOf(src, src.length);
	}

	public static final Short[] box(short[] src) {
		int len = src.length;
		Short[] array = new Short[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final short[] unbox(Short[] src) {
		if (src != null) {
			int len = src.length;
			short[] array = new short[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final short[] copyOf(short[] src) {
		return Arrays.copyOf(src, src.length);
	}

	public static final Integer[] box(int[] src) {
		int len = src.length;
		Integer[] array = new Integer[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final int[] unbox(Integer[] src) {
		if (src != null) {
			int len = src.length;
			int[] array = new int[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final int[] copyOf(int[] src) {
		return Arrays.copyOf(src, src.length);
	}

	public static final Long[] box(long[] src) {
		int len = src.length;
		Long[] array = new Long[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final long[] unbox(Long[] src) {
		if (src != null) {
			int len = src.length;
			long[] array = new long[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final long[] copyOf(long[] src) {
		return Arrays.copyOf(src, src.length);
	}

	public static final Float[] box(float[] src) {
		int len = src.length;
		Float[] array = new Float[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final float[] unbox(Float[] src) {
		if (src != null) {
			int len = src.length;
			float[] array = new float[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final float[] copyOf(float[] src) {
		return Arrays.copyOf(src, src.length);
	}

	public static final Double[] box(double[] src) {
		int len = src.length;
		Double[] array = new Double[len];
		for (int i = 0; i != len; ++i) {
			array[i] = src[i];
		}
		return array;
	}

	public static final double[] unbox(Double[] src) {
		if (src != null) {
			int len = src.length;
			double[] array = new double[len];
			for (int i = 0; i != len; ++i) {
				array[i] = AutoboxingRuntime.unbox(src[i]);
			}
			return array;
		}
		return null;
	}

	public static final double[] copyOf(double[] src) {
		return Arrays.copyOf(src, src.length);
	}

}
