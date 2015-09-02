package alt.beanmapper.compile.typecast;

import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;
import alt.beanmapper.mapping.TypeCastStrategy;

/**
 * 
 * @author Albert Shift
 *
 */

public interface TypeCastMapper {

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	BooleanTypeCastDestination mapBoolean(TypeCastSource src);

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	ByteTypeCastDestination mapByte(TypeCastSource src);

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	CharTypeCastDestination mapChar(TypeCastSource src);

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	ShortTypeCastDestination mapShort(TypeCastSource src);

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	IntTypeCastDestination mapInt(TypeCastSource src);

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	LongTypeCastDestination mapLong(TypeCastSource src);

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	FloatTypeCastDestination mapFloat(TypeCastSource src);

	@Mapper(value = { @Property(src = "booleanValue"), @Property(src = "byteValue"),
			@Property(src = "charValue"), @Property(src = "shortValue"), @Property(src = "intValue"),
			@Property(src = "longValue"), @Property(src = "floatValue"), @Property(src = "doubleValue") }, typeCast = TypeCastStrategy.ALL)
	DoubleTypeCastDestination mapDouble(TypeCastSource src);

}
