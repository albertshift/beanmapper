package alt.beanmapper.converter;

/**
 * 
 * @author Albert Shift
 *
 */

public class DummyConverter implements Converter<Object, Object> {

	@Override
	public Object convert(Object src) {
		return src;
	}

}
