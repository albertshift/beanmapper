package alt.beanmapper.compile;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.runtime.AutoboxingRuntime;

/**
 * 
 * @author Albert Shift
 *
 */

public final class Autoboxing implements Opcodes {

	public static final String AUTOBOXING_RUNTIME_TYPE = Type.getInternalName(AutoboxingRuntime.class);
	public static final String VALUE_OF_METHOD_NAME = "valueOf";
	public static final String UNBOX_METHOD_NAME = "unbox";

	private static Map<Type, Entry> primitiveIndex = new HashMap<Type, Entry>();
	private static Map<Type, Entry> wrapperIndex = new HashMap<Type, Entry>();
	private static Map<Type, Entry> allIndex = new HashMap<Type, Entry>();

	static {

		add(new Entry(boolean.class, Boolean.class));
		add(new Entry(byte.class, Byte.class));
		add(new Entry(char.class, Character.class));
		add(new Entry(short.class, Short.class));
		add(new Entry(int.class, Integer.class));
		add(new Entry(long.class, Long.class));
		add(new Entry(float.class, Float.class));
		add(new Entry(double.class, Double.class));

	}

	private static void add(Entry entry) {
		primitiveIndex.put(entry.primitiveType, entry);
		wrapperIndex.put(entry.wrapperType, entry);
		allIndex.put(entry.primitiveType, entry);
		allIndex.put(entry.wrapperType, entry);
	}

	private Autoboxing() {
	}

	public static Type findPrimitiveType(Type descType) {
		Entry entry = allIndex.get(descType);
		if (entry != null) {
			return entry.primitiveType;
		}
		return null;
	}

	public static Type box(Type unknownType) {
		Entry entry = primitiveIndex.get(unknownType);
		if (entry != null) {
			return entry.wrapperType;
		}
		return unknownType;
	}

	public static boolean isBoxing(Type srcType, Type destType) {
		Entry entry = primitiveIndex.get(srcType);
		if (entry != null && entry.wrapperType.equals(destType)) {
			return true;
		}
		return false;
	}

	public static boolean isUnboxing(Type srcType, Type destType) {
		Entry entry = wrapperIndex.get(srcType);
		if (entry != null && entry.primitiveType.equals(destType)) {
			return true;
		}
		return false;
	}

	public static boolean generateAutoboxing(MethodVisitor mv, Type srcPropType, Type destPropType) {

		if (Autoboxing.isBoxing(srcPropType, destPropType)) {
			mv.visitMethodInsn(INVOKESTATIC, destPropType.getInternalName(), VALUE_OF_METHOD_NAME, "("
					+ srcPropType.getDescriptor() + ")" + destPropType.getDescriptor(), false);
			return true;
		}

		if (Autoboxing.isUnboxing(srcPropType, destPropType)) {
			mv.visitMethodInsn(INVOKESTATIC, AUTOBOXING_RUNTIME_TYPE, UNBOX_METHOD_NAME,
					"(" + srcPropType.getDescriptor() + ")" + destPropType.getDescriptor(), false);
			return true;
		}

		return false;
	}

	private static final class Entry {

		private final Type primitiveType;
		private final Type wrapperType;

		private Entry(Class<?> primitiveClass, Class<?> wrapperClass) {
			this.primitiveType = Type.getType(primitiveClass);
			this.wrapperType = Type.getType(wrapperClass);
		}

	}

}
