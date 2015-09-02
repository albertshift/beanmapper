package alt.beanmapper.test.converter;

import java.util.Date;

import alt.beanmapper.converter.Converter;

public class LongToDateConverter implements Converter<Long, Date> {

	@Override
	public Date convert(Long src) {
		return src != null ? new Date(src) : null;
	}

}
