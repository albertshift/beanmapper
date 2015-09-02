package alt.beanmapper.compile.primitive;

/**
 * 
 * @author Albert Shift
 *
 */

public class PrimitiveReferenceMapperImpl implements PrimitiveMapper {

	@Override
	public PrimitiveDestination map(PrimitiveSource src) {
		PrimitiveDestination dest = new PrimitiveDestination();
		dest.setBooleanValue(src.isBooleanValue());
		dest.setByteValue(src.getByteValue());
		dest.setCharValue(src.getCharValue());
		dest.setShortValue(src.getShortValue());
		dest.setIntValue(src.getIntValue());
		dest.setLongValue(src.getLongValue());
		dest.setFloatValue(src.getFloatValue());
		dest.setDoubleValue(src.getDoubleValue());
		return dest;
	}

}
