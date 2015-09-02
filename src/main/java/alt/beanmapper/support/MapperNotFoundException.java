package alt.beanmapper.support;

/**
 * 
 * @author Albert Shift
 *
 */

public class MapperNotFoundException extends BeanMapperException {

	private static final long serialVersionUID = 8592418558742793003L;

	public MapperNotFoundException(String msg) {
		super(msg);
	}

	public MapperNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

}
