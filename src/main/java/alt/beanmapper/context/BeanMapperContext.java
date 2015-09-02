package alt.beanmapper.context;

/**
 * 
 * @author Albert Shift
 *
 */

public interface BeanMapperContext {

	<M> M getMapperService(Class<M> mapperInterface);

}
