package alt.beanmapper.compile.boxing;

/**
 * 
 * @author Albert Shift
 *
 */

public class BoxingReferenceMapperImpl implements BoxingMapper {

	@Override
	public BoxingDestination map(BoxingSource src) {
		BoxingDestination dest = new BoxingDestination();
		dest.setBooleanValue(Boolean.valueOf(src.isBooleanValue()));
		dest.setByteValue(Byte.valueOf(src.getByteValue()));
		dest.setCharValue(Character.valueOf(src.getCharValue()));
		dest.setShortValue(Short.valueOf(src.getShortValue()));
		dest.setIntValue(Integer.valueOf(src.getIntValue()));
		dest.setLongValue(Long.valueOf(src.getLongValue()));
		dest.setFloatValue(Float.valueOf(src.getFloatValue()));
		dest.setDoubleValue(Double.valueOf(src.getDoubleValue()));
		return dest;
	}

}
