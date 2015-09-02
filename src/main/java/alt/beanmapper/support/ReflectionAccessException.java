package alt.beanmapper.support;

/**
 * 
 * @author Albert Shift
 *
 */

public class ReflectionAccessException extends BeanMapperException {

	private static final long serialVersionUID = 6153682619698172728L;

	public ReflectionAccessException(String msg) {
		super(msg);
	}

	public ReflectionAccessException(String msg, Throwable t) {
		super(msg, t);
	}
}
