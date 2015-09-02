package alt.beanmapper.compile.converter;

import alt.beanmapper.converter.Converter;

/**
 * 
 * @author Albert Shift
 *
 */

public class IntToLongConverter implements Converter<Integer, Long> {

	@Override
	public Long convert(Integer src) {
		return src.longValue();
	}

}
