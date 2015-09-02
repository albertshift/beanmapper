package alt.beanmapper.context;

public interface PropertyDescription {

	String getName();

	Class<?> getFieldType();

	String getGetterName();

	Class<?> getGetterType();

	String getSetterName();

	Class<?> getSetterType();

	boolean isAccessibleByField(String fromPackageName);

	boolean isAccessibleByGetter(String fromPackageName);

	boolean isAccessibleBySetter(String fromPackageName);

}
