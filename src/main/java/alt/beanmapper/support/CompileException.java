package alt.beanmapper.support;

/**
 * 
 * @author Albert Shift
 *
 */

public class CompileException extends BeanMapperException {

	private static final long serialVersionUID = 6891282165308895381L;

	public CompileException(String msg) {
		super(msg);
	}

	public CompileException(String msg, Throwable t) {
		super(msg, t);
	}

}
