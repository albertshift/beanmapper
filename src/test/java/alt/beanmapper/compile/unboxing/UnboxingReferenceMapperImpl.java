package alt.beanmapper.compile.unboxing;

import alt.beanmapper.runtime.AutoboxingRuntime;

/**
 * 
 * @author Albert Shift
 *
 */

public class UnboxingReferenceMapperImpl implements UnboxingMapper {

	@Override
	public UnboxingDestination map(UnboxingSource src) {
		UnboxingDestination dest = new UnboxingDestination();
		dest.setBooleanValue(AutoboxingRuntime.unbox(src.getBooleanValue()));
		dest.setByteValue(AutoboxingRuntime.unbox(src.getByteValue()));
		dest.setCharValue(AutoboxingRuntime.unbox(src.getCharValue()));
		dest.setShortValue(AutoboxingRuntime.unbox(src.getShortValue()));
		dest.setIntValue(AutoboxingRuntime.unbox(src.getIntValue()));
		dest.setLongValue(AutoboxingRuntime.unbox(src.getLongValue()));
		dest.setFloatValue(AutoboxingRuntime.unbox(src.getFloatValue()));
		dest.setDoubleValue(AutoboxingRuntime.unbox(src.getDoubleValue()));
		return dest;
	}

}
