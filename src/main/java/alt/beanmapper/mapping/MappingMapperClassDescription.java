package alt.beanmapper.mapping;

import alt.beanmapper.context.MapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class MappingMapperClassDescription implements MapperClassDescription {

	private final Class<?> mapperInterface;
	private final String implClassName;
	private final String implPackageName;

	public MappingMapperClassDescription(Class<?> mapperInterface, String classNamePostfix) {

		this.mapperInterface = mapperInterface;

		MapperClass ms = mapperInterface.getAnnotation(MapperClass.class);

		String className = null;
		if (ms != null) {
			String impl = ms.impl();
			if (impl != null && !impl.isEmpty()) {
				if (impl.indexOf(".") != -1) {
					className = impl;
				} else {
					Package p = mapperInterface.getPackage();
					className = p != null ? p.getName() + "." + impl : impl;
				}
			}
		}

		if (className == null) {
			className = mapperInterface.getName() + classNamePostfix;
		}

		this.implClassName = className;
		this.implPackageName = getPackageName(className);

	}

	@Override
	public Class<?> getMapperInterface() {
		return mapperInterface;
	}

	@Override
	public String getImplClassName() {
		return implClassName;
	}

	@Override
	public String getImplPackageName() {
		return implPackageName;
	}

	private String getPackageName(String className) {
		int i = className.lastIndexOf(".");
		if (i != -1) {
			return className.substring(0, i);
		}
		return null;
	}
}
