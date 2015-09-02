package alt.beanmapper.runtime;

import alt.beanmapper.converter.Converter;

/**
 * 
 * @author Albert Shift
 *
 */

public final class ConverterRuntime {

	public static <S, D, C extends Converter<S, D>> D convert(S src, C converter) {
		if (src != null) {
			return converter.convert(src);
		} else {
			return null;
		}
	}

}
