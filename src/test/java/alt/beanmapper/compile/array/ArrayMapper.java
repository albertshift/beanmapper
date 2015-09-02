package alt.beanmapper.compile.array;

import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;

/**
 * 
 * @author Albert Shift
 *
 */

public interface ArrayMapper {

	@Mapper({ @Property(src = "booleanValue"), @Property(src = "byteValue"), @Property(src = "charValue"),
			@Property(src = "shortValue"), @Property(src = "intValue"), @Property(src = "longValue"),
			@Property(src = "floatValue"), @Property(src = "doubleValue") })
	ArrayDestination pmap(ArraySource src);

	@Mapper({ @Property(src = "booleanValue"), @Property(src = "byteValue"), @Property(src = "charValue"),
			@Property(src = "shortValue"), @Property(src = "intValue"), @Property(src = "longValue"),
			@Property(src = "floatValue"), @Property(src = "doubleValue") })
	ArrayWrappedDestination wmap(ArrayWrappedSource src);

	@Mapper({ @Property(src = "booleanValue"), @Property(src = "byteValue"), @Property(src = "charValue"),
			@Property(src = "shortValue"), @Property(src = "intValue"), @Property(src = "longValue"),
			@Property(src = "floatValue"), @Property(src = "doubleValue") })
	ArrayWrappedDestination box(ArraySource src);

	@Mapper({ @Property(src = "booleanValue"), @Property(src = "byteValue"), @Property(src = "charValue"),
			@Property(src = "shortValue"), @Property(src = "intValue"), @Property(src = "longValue"),
			@Property(src = "floatValue"), @Property(src = "doubleValue") })
	ArrayDestination unbox(ArrayWrappedSource src);

}
