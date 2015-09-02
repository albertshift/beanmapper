package alt.beanmapper.compile.array;

import alt.beanmapper.runtime.ArraysRuntime;

/**
 * 
 * @author Albert Shift
 *
 */

public class ArrayReferenceMapperImpl implements ArrayMapper {

	@Override
	public ArrayDestination pmap(ArraySource src) {
		ArrayDestination dest = new ArrayDestination();
		dest.setBooleanValue(ArraysRuntime.copyOf(src.getBooleanValue()));
		dest.setByteValue(ArraysRuntime.copyOf(src.getByteValue()));
		dest.setCharValue(ArraysRuntime.copyOf(src.getCharValue()));
		dest.setShortValue(ArraysRuntime.copyOf(src.getShortValue()));
		dest.setIntValue(ArraysRuntime.copyOf(src.getIntValue()));
		dest.setLongValue(ArraysRuntime.copyOf(src.getLongValue()));
		dest.setFloatValue(ArraysRuntime.copyOf(src.getFloatValue()));
		dest.setDoubleValue(ArraysRuntime.copyOf(src.getDoubleValue()));
		return dest;
	}

	@Override
	public ArrayWrappedDestination wmap(ArrayWrappedSource src) {
		ArrayWrappedDestination dest = new ArrayWrappedDestination();
		dest.setBooleanValue(ArraysRuntime.copyOf(src.getBooleanValue()));
		dest.setByteValue(ArraysRuntime.copyOf(src.getByteValue()));
		dest.setCharValue(ArraysRuntime.copyOf(src.getCharValue()));
		dest.setShortValue(ArraysRuntime.copyOf(src.getShortValue()));
		dest.setIntValue(ArraysRuntime.copyOf(src.getIntValue()));
		dest.setLongValue(ArraysRuntime.copyOf(src.getLongValue()));
		dest.setFloatValue(ArraysRuntime.copyOf(src.getFloatValue()));
		dest.setDoubleValue(ArraysRuntime.copyOf(src.getDoubleValue()));
		return dest;
	}

	@Override
	public ArrayWrappedDestination box(ArraySource src) {
		ArrayWrappedDestination dest = new ArrayWrappedDestination();
		dest.setBooleanValue(ArraysRuntime.box(src.getBooleanValue()));
		dest.setByteValue(ArraysRuntime.box(src.getByteValue()));
		dest.setCharValue(ArraysRuntime.box(src.getCharValue()));
		dest.setShortValue(ArraysRuntime.box(src.getShortValue()));
		dest.setIntValue(ArraysRuntime.box(src.getIntValue()));
		dest.setLongValue(ArraysRuntime.box(src.getLongValue()));
		dest.setFloatValue(ArraysRuntime.box(src.getFloatValue()));
		dest.setDoubleValue(ArraysRuntime.box(src.getDoubleValue()));
		return dest;
	}

	@Override
	public ArrayDestination unbox(ArrayWrappedSource src) {
		ArrayDestination dest = new ArrayDestination();
		dest.setBooleanValue(ArraysRuntime.unbox(src.getBooleanValue()));
		dest.setByteValue(ArraysRuntime.unbox(src.getByteValue()));
		dest.setCharValue(ArraysRuntime.unbox(src.getCharValue()));
		dest.setShortValue(ArraysRuntime.unbox(src.getShortValue()));
		dest.setIntValue(ArraysRuntime.unbox(src.getIntValue()));
		dest.setLongValue(ArraysRuntime.unbox(src.getLongValue()));
		dest.setFloatValue(ArraysRuntime.unbox(src.getFloatValue()));
		dest.setDoubleValue(ArraysRuntime.unbox(src.getDoubleValue()));
		return dest;
	}

}
