package alt.beanmapper.compile.unboxing;

import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;

/**
 * 
 * @author Albert Shift
 *
 */

public interface UnboxingMapper {

	@Mapper({ @Property(src = "booleanValue"), @Property(src = "byteValue"), @Property(src = "charValue"),
			@Property(src = "shortValue"), @Property(src = "intValue"), @Property(src = "longValue"),
			@Property(src = "floatValue"), @Property(src = "doubleValue") })
	UnboxingDestination map(UnboxingSource src);

}
