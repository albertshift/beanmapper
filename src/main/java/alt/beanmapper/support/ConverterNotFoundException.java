package alt.beanmapper.support;

/**
 * 
 * @author Albert Shift
 *
 */

public class ConverterNotFoundException extends BeanMapperException {

	private static final long serialVersionUID = -871345985180242605L;

	public ConverterNotFoundException(String msg) {
		super(msg);
	}

	public ConverterNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

}
