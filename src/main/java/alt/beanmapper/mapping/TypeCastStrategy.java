package alt.beanmapper.mapping;

/**
 * 
 * @author Albert Shift
 *
 */

public enum TypeCastStrategy {
	DEFAULT, NO_CAST, JAVA_AUTO, NO_BOOLEAN, ALL;

	public static TypeCastStrategy detect(TypeCastStrategy... fromCustomToGeneral) {
		for (TypeCastStrategy current : fromCustomToGeneral) {
			if (current != null && current != TypeCastStrategy.DEFAULT) {
				return current;
			}
		}
		return MappingConstants.DEFAULT_TYPECAST_STRATEGY;
	}

}
