package alt.beanmapper.compile.field;

import alt.beanmapper.runtime.AutoboxingRuntime;

/**
 * 
 * @author Albert Shift
 *
 */

public class FieldReferenceMapperImpl implements FieldMapper {

	@Override
	public FieldDestination map(FieldSource src) {
		FieldDestination dest = new FieldDestination();
		dest.primitiveInt = src.primitiveInt;
		dest.wrapperInt = src.wrapperInt;
		dest.boxingInt = Integer.valueOf(src.boxingInt);
		dest.unboxingInt = AutoboxingRuntime.unbox(src.unboxingInt);
		dest.openAccessInt = src.getOpenAccessInt();
		dest.setCloseAccessInt(src.closeAccessInt);
		dest.openAccessIntBoxing = Integer.valueOf(src.getOpenAccessIntBoxing());
		dest.setCloseAccessIntUnboxing(AutoboxingRuntime.unbox(src.closeAccessIntUnboxing));
		return dest;
	}

}
