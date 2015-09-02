package alt.beanmapper.compile.options;

import alt.beanmapper.converter.Converter;
import alt.beanmapper.mapping.Property;
import alt.beanmapper.mapping.TypeCastStrategy;

/**
 * 
 * @author Albert Shift
 *
 */

public class PropertyOptions {

	private final MapperOptions mapperOptions;
	private final TypeCastStrategy typeCastStrategy;
	private final Class<? extends Converter<?, ?>> converterClass;

	public PropertyOptions(MapperOptions mapperOptions, Property propAnnotation) {
		this.mapperOptions = mapperOptions;

		if (propAnnotation != null) {
			this.converterClass = propAnnotation.converter();
			this.typeCastStrategy = propAnnotation.typeCast();
		} else {
			this.converterClass = null;
			this.typeCastStrategy = null;
		}
	}

	public TypeCastStrategy getTypeCastStrategy() {
		return TypeCastStrategy.detect(typeCastStrategy, mapperOptions.getTypeCastStrategy(), mapperOptions
				.getMapperServiceOptions().getTypeCastStrategy());
	}

	public Class<? extends Converter<?, ?>> getConverterClass() {
		return converterClass;
	}

	public MapperOptions getMapperOptions() {
		return mapperOptions;
	}

}
