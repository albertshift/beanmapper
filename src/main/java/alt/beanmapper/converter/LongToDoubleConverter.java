package alt.beanmapper.converter;

public class LongToDoubleConverter implements Converter<Long, Double> {

	@Override
	public Double convert(Long src) {
		return new Double(src.doubleValue());
	}

}
