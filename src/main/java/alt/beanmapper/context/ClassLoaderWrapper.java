package alt.beanmapper.context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import alt.beanmapper.support.ReflectionAccessException;

/**
 * 
 * @author Albert Shift
 *
 */

public class ClassLoaderWrapper {

	private final static String DEFINECLASS_METHOD = "defineClass";
	private final static Class<?>[] DEFINECLASS_ARGS = new Class<?>[] { String.class, byte[].class, int.class,
			int.class };

	private final ClassLoader classLoader;
	private final Method defineClassMethod;

	public ClassLoaderWrapper(ClassLoader classLoader) {
		this.classLoader = classLoader;

		this.defineClassMethod = findDefineClassMethod(classLoader.getClass());

		if (defineClassMethod == null) {
			throw new ReflectionAccessException("method defineClass not found in " + classLoader);
		}

		defineClassMethod.setAccessible(true);

	}

	public Class<?> loadClass(String name, Fallback fb) {
		try {
			return classLoader.loadClass(name);
		} catch (ClassNotFoundException e) {
			return fb.fallback(name);
		}
	}

	public Class<?> defineClass(String name, byte[] b, int off, int len) throws ClassFormatError {
		try {
			return (Class<?>) defineClassMethod.invoke(classLoader, name, b, off, len);
		} catch (IllegalArgumentException e) {
			throw new ReflectionAccessException("invalid arguments", e);
		} catch (IllegalAccessException e) {
			throw new ReflectionAccessException("no permissions to call defineClass method", e);
		} catch (InvocationTargetException e) {
			throw new ReflectionAccessException("defineClass fail", e);
		}
	}

	protected Method findDefineClassMethod(Class<?> investigationClass) {

		while (investigationClass != null && investigationClass != Object.class) {

			for (Method m : investigationClass.getDeclaredMethods()) {

				if (DEFINECLASS_METHOD.endsWith(m.getName())
						&& Arrays.equals(DEFINECLASS_ARGS, m.getParameterTypes())) {

					return m;
				}
			}

			investigationClass = investigationClass.getSuperclass();

		}

		return null;
	}

	public interface Fallback {

		Class<?> fallback(String name);

	}

}
