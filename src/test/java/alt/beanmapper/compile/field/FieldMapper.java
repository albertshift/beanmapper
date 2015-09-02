package alt.beanmapper.compile.field;

import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;

/**
 * 
 * @author Albert Shift
 *
 */

public interface FieldMapper {

	@Mapper({ @Property(src = "primitiveInt"), @Property(src = "wrapperInt"), @Property(src = "boxingInt"),
			@Property(src = "unboxingInt"), @Property(src = "openAccessInt"), @Property(src = "closeAccessInt"),
			@Property(src = "openAccessIntBoxing"), @Property(src = "closeAccessIntUnboxing") })
	FieldDestination map(FieldSource src);

}
