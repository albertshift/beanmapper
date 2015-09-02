package alt.beanmapper.context;

/**
 * 
 * @author Albert Shift
 *
 */

public interface MapperClassDescription {

	Class<?> getMapperInterface();

	String getImplClassName();

	String getImplPackageName();

}
