package alt.beanmapper.util;

/**
 * 
 * @author Albert Shift
 *
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class ReflectionUtil {

	private ReflectionUtil() {
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> findGenericType(Class<?> currentClass, Class<?> genericInterface, int index) {

		Type[] interfaces = currentClass.getGenericInterfaces();

		for (Type iface : interfaces) {

			if (iface instanceof ParameterizedType) {

				ParameterizedType ptype = (ParameterizedType) iface;

				if (ptype.getRawType().equals(genericInterface)) {

					return (Class<T>) ptype.getActualTypeArguments()[index];

				}

			}

			if (iface instanceof Class) {

				Class<T> resultClass = findGenericType((Class<?>) iface, genericInterface, 0);

				if (resultClass != null) {
					return resultClass;
				}

			}

		}

		return null;

	}

	public static Class<?>[] findGenericTypes(Class<?> currentClass, Class<?> genericInterface) {

		Type[] interfaces = currentClass.getGenericInterfaces();

		for (Type iface : interfaces) {

			if (iface instanceof ParameterizedType) {

				ParameterizedType ptype = (ParameterizedType) iface;

				if (ptype.getRawType().equals(genericInterface)) {

					Type[] genericTypes = ptype.getActualTypeArguments();
					int len = genericTypes.length;

					Class<?>[] result = new Class[len];

					for (int i = 0; i != len; ++i) {
						result[i] = (Class<?>) genericTypes[i];
					}

					return result;

				}

			}

			if (iface instanceof Class) {

				Class<?>[] result = findGenericTypes((Class<?>) iface, genericInterface);

				if (result != null) {
					return result;
				}

			}

		}

		return null;

	}

	public static boolean hasDefaultPublicConstructor(Class<?> currentClass) {

		int constructors = 0;

		for (Constructor<?> con : currentClass.getDeclaredConstructors()) {

			if (con.getParameterTypes().length == 0 && Modifier.isPublic(con.getModifiers())) {
				return true;
			}

			constructors++;

		}

		return constructors == 0;

	}
}
