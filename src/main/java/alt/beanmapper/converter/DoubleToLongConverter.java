package alt.beanmapper.converter;

public class DoubleToLongConverter implements Converter<Double, Long> {

	@Override
	public Long convert(Double src) {
		return Long.valueOf(src.longValue());
	}

}
