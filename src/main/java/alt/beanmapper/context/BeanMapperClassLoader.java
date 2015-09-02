package alt.beanmapper.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Albert Shift
 *
 */

public class BeanMapperClassLoader extends ClassLoader {

	private Map<String, byte[]> classes = new HashMap<String, byte[]>();

	public BeanMapperClassLoader(ClassLoader parent) {
		super(parent);
	}

	public void addByteCode(String className, byte[] byteCode) {
		classes.put(className, byteCode);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try {
			return super.loadClass(name);
		} catch (ClassNotFoundException e) {
			byte[] byteCode = classes.get(name);
			if (byteCode != null) {
				return defineImplClass(name, byteCode);
			} else {
				throw e;
			}
		}
	}

	protected Class<?> defineImplClass(String className, byte[] byteCode) {
		return defineClass(className, byteCode, 0, byteCode.length);
	}

	@Override
	public String toString() {
		return "MapperClassLoader " + super.toString();
	}

}
