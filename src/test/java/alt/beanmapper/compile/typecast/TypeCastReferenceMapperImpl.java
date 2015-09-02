package alt.beanmapper.compile.typecast;

import alt.beanmapper.runtime.BooleanTypeCastRuntime;

/**
 * 
 * @author Albert Shift
 *
 */

public class TypeCastReferenceMapperImpl implements TypeCastMapper {

	@Override
	public BooleanTypeCastDestination mapBoolean(TypeCastSource src) {
		BooleanTypeCastDestination dest = new BooleanTypeCastDestination();
		dest.setBooleanValue(src.isBooleanValue());
		dest.setByteValue(BooleanTypeCastRuntime.castByteToBoolean(src.getByteValue()));
		dest.setCharValue(BooleanTypeCastRuntime.castCharToBoolean(src.getCharValue()));
		dest.setShortValue(BooleanTypeCastRuntime.castShortToBoolean(src.getShortValue()));
		dest.setIntValue(BooleanTypeCastRuntime.castIntToBoolean(src.getIntValue()));
		dest.setLongValue(BooleanTypeCastRuntime.castLongToBoolean(src.getLongValue()));
		dest.setFloatValue(BooleanTypeCastRuntime.castFloatToBoolean(src.getFloatValue()));
		dest.setDoubleValue(BooleanTypeCastRuntime.castDoubleToBoolean(src.getDoubleValue()));
		return dest;
	}

	@Override
	public ByteTypeCastDestination mapByte(TypeCastSource src) {
		ByteTypeCastDestination dest = new ByteTypeCastDestination();
		dest.setBooleanValue(BooleanTypeCastRuntime.castBooleanToByte(src.isBooleanValue()));
		dest.setByteValue(src.getByteValue());
		dest.setCharValue((byte) src.getCharValue());
		dest.setShortValue((byte) src.getShortValue());
		dest.setIntValue((byte) src.getIntValue());
		dest.setLongValue((byte) src.getLongValue());
		dest.setFloatValue((byte) src.getFloatValue());
		dest.setDoubleValue((byte) src.getDoubleValue());
		return dest;
	}

	@Override
	public CharTypeCastDestination mapChar(TypeCastSource src) {
		CharTypeCastDestination dest = new CharTypeCastDestination();
		dest.setBooleanValue(BooleanTypeCastRuntime.castBooleanToChar(src.isBooleanValue()));
		dest.setByteValue((char) src.getByteValue());
		dest.setCharValue(src.getCharValue());
		dest.setShortValue((char) src.getShortValue());
		dest.setIntValue((char) src.getIntValue());
		dest.setLongValue((char) src.getLongValue());
		dest.setFloatValue((char) src.getFloatValue());
		dest.setDoubleValue((char) src.getDoubleValue());
		return dest;
	}

	@Override
	public ShortTypeCastDestination mapShort(TypeCastSource src) {
		ShortTypeCastDestination dest = new ShortTypeCastDestination();
		dest.setBooleanValue(BooleanTypeCastRuntime.castBooleanToShort(src.isBooleanValue()));
		dest.setByteValue(src.getByteValue());
		dest.setCharValue((short) src.getCharValue());
		dest.setShortValue(src.getShortValue());
		dest.setIntValue((short) src.getIntValue());
		dest.setLongValue((short) src.getLongValue());
		dest.setFloatValue((short) src.getFloatValue());
		dest.setDoubleValue((short) src.getDoubleValue());
		return dest;
	}

	@Override
	public IntTypeCastDestination mapInt(TypeCastSource src) {
		IntTypeCastDestination dest = new IntTypeCastDestination();
		dest.setBooleanValue(BooleanTypeCastRuntime.castBooleanToInt(src.isBooleanValue()));
		dest.setByteValue(src.getByteValue());
		dest.setCharValue(src.getCharValue());
		dest.setShortValue(src.getShortValue());
		dest.setIntValue(src.getIntValue());
		dest.setLongValue((int) src.getLongValue());
		dest.setFloatValue((int) src.getFloatValue());
		dest.setDoubleValue((int) src.getDoubleValue());
		return dest;
	}

	@Override
	public LongTypeCastDestination mapLong(TypeCastSource src) {
		LongTypeCastDestination dest = new LongTypeCastDestination();
		dest.setBooleanValue(BooleanTypeCastRuntime.castBooleanToLong(src.isBooleanValue()));
		dest.setByteValue(src.getByteValue());
		dest.setCharValue(src.getCharValue());
		dest.setShortValue(src.getShortValue());
		dest.setIntValue(src.getIntValue());
		dest.setLongValue(src.getLongValue());
		dest.setFloatValue((long) src.getFloatValue());
		dest.setDoubleValue((long) src.getDoubleValue());
		return dest;
	}

	@Override
	public FloatTypeCastDestination mapFloat(TypeCastSource src) {
		FloatTypeCastDestination dest = new FloatTypeCastDestination();
		dest.setBooleanValue(BooleanTypeCastRuntime.castBooleanToFloat(src.isBooleanValue()));
		dest.setByteValue(src.getByteValue());
		dest.setCharValue(src.getCharValue());
		dest.setShortValue(src.getShortValue());
		dest.setIntValue(src.getIntValue());
		dest.setLongValue(src.getLongValue());
		dest.setFloatValue(src.getFloatValue());
		dest.setDoubleValue((float) src.getDoubleValue());
		return dest;
	}

	@Override
	public DoubleTypeCastDestination mapDouble(TypeCastSource src) {
		DoubleTypeCastDestination dest = new DoubleTypeCastDestination();
		dest.setBooleanValue(BooleanTypeCastRuntime.castBooleanToDouble(src.isBooleanValue()));
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
