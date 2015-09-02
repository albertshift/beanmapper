package alt.beanmapper.test.converter;

import java.util.Date;

import alt.beanmapper.converter.Converter;

public class DateToLongConverter implements Converter<Date, Long> {

	@Override
	public Long convert(Date src) {
		return src != null ? src.getTime() : 0;
	}

}
