package alt.beanmapper.util;

/**
 * 
 * @author Albert Shift
 *
 */

public final class BeanUtil {

	private BeanUtil() {
	}

	public static final String getGetterName(String propertyName) {
		return "get" + StringUtil.capitalize(propertyName);
	}

	public static final String getCheckerName(String propertyName) {
		return "is" + StringUtil.capitalize(propertyName);
	}

	public static final String getSetterName(String propertyName) {
		return "set" + StringUtil.capitalize(propertyName);
	}

}
