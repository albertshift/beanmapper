package alt.beanmapper.support;

/**
 * 
 * @author Albert Shift
 *
 */

public class MappingException extends BeanMapperException {

	private static final long serialVersionUID = 3148229047777409018L;

	public MappingException(String msg) {
		super(msg);
	}

	public MappingException(String msg, Throwable t) {
		super(msg, t);
	}

}
