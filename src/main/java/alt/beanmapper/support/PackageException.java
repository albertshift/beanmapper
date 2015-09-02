package alt.beanmapper.support;

/**
 * 
 * @author Albert Shift
 *
 */

public class PackageException extends BeanMapperException {

	private static final long serialVersionUID = -2887322205966219583L;

	public PackageException(String msg) {
		super(msg);
	}

	public PackageException(String msg, Throwable t) {
		super(msg, t);
	}

}
