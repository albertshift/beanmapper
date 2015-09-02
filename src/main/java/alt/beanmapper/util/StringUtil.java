package alt.beanmapper.util;

/**
 * 
 * @author Albert Shift
 *
 */

public final class StringUtil {

	private StringUtil() {
	}

	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	public static String replaceAll(String str, String subString, String replacement) {
		if (str == null) {
			return null;
		}
		if (subString == null || replacement == null) {
			return str;
		}
		StringBuilder sbuf = new StringBuilder();
		int pos = 0;
		int index = str.indexOf(subString);
		int subStringLength = subString.length();
		while (index >= 0) {
			sbuf.append(str.substring(pos, index));
			sbuf.append(replacement);
			pos = index + subStringLength;
			index = str.indexOf(subString, pos);
		}
		sbuf.append(str.substring(pos));
		return sbuf.toString();
	}

	public static boolean nullableEquals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

}
