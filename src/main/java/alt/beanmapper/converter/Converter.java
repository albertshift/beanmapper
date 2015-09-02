package alt.beanmapper.converter;

/**
 * 
 * @author Albert Shift
 *
 */

public interface Converter<S, D> {

	D convert(S src);

}
