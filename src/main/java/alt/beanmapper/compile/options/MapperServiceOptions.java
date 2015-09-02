package alt.beanmapper.compile.options;

import alt.beanmapper.mapping.MapperClass;
import alt.beanmapper.mapping.MappingConstants;
import alt.beanmapper.mapping.TypeCastStrategy;

/**
 * 
 * @author Albert Shift
 *
 */

public class MapperServiceOptions {

	private final TypeCastStrategy typeCastStrategy;
	private final boolean jsr330;

	public MapperServiceOptions(MapperClass mc) {
		if (mc != null) {
			this.typeCastStrategy = mc.typeCast();
			this.jsr330 = mc.jsr330();
		} else {
			this.typeCastStrategy = null;
			this.jsr330 = MappingConstants.DEFAULT_JSR330;
		}
	}

	public TypeCastStrategy getTypeCastStrategy() {
		return typeCastStrategy;
	}

	public boolean useJsr330() {
		return jsr330;
	}

}
