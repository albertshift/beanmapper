package alt.beanmapper.support;

/**
 * 
 * @author Albert Shift
 *
 */

public class BeanMapperException extends RuntimeException {

	private static final long serialVersionUID = -5805956692305458267L;

	public BeanMapperException(String msg) {
		super(msg);
	}

	public BeanMapperException(String msg, Throwable t) {
		super(msg, t);
	}

}
