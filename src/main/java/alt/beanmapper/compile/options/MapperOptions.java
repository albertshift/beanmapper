package alt.beanmapper.compile.options;

import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;
import alt.beanmapper.mapping.TypeCastStrategy;

/**
 * 
 * @author Albert Shift
 *
 */

public class MapperOptions {

	private final MapperServiceOptions mapperServiceOptions;
	private final TypeCastStrategy typeCastStrategy;

	private final Property[] properties;

	public MapperOptions(MapperServiceOptions mapperServiceOptions, Mapper mapper) {
		this.mapperServiceOptions = mapperServiceOptions;
		if (mapper != null) {
			this.properties = mapper.value();
			this.typeCastStrategy = mapper.typeCast();
		} else {
			this.properties = new Property[0];
			this.typeCastStrategy = null;
		}
	}

	public TypeCastStrategy getTypeCastStrategy() {
		return TypeCastStrategy.detect(typeCastStrategy, mapperServiceOptions.getTypeCastStrategy());
	}

	public MapperServiceOptions getMapperServiceOptions() {
		return mapperServiceOptions;
	}

	public Property[] getProperties() {
		return properties;
	}

}
