package alt.beanmapper.util;

/**
 * 
 * @author Albert Shift
 *
 */

public final class ClassLoaderUtil {

	private ClassLoaderUtil() {
	}

	public static ClassLoader getCurrentClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassLoaderUtil.class.getClassLoader();
		}
		return classLoader;
	}

}
