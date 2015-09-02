package alt.beanmapper.compile.converter;

import javax.inject.Inject;
import javax.inject.Named;

import alt.beanmapper.runtime.AutoboxingRuntime;
import alt.beanmapper.runtime.ConverterRuntime;

/**
 * 
 * @author Albert Shift
 *
 */

@Named
public class ConverterReferenceMapperImpl implements ConverterMapper {

	@Inject
	private IntToLongConverter intToLongConverter;

	@Override
	public ConverterDestination map(ConverterSource src) {
		ConverterDestination dest = new ConverterDestination();
		dest.setPrimitiveLong(AutoboxingRuntime.unbox(ConverterRuntime.convert(
				Integer.valueOf(src.getPrimitiveInt()), intToLongConverter)));
		dest.setWrapperedLong(ConverterRuntime.convert(src.getWrapperedInt(), intToLongConverter));
		dest.setBoxingLong(ConverterRuntime.convert(Integer.valueOf(src.getBoxingInt()), intToLongConverter));
		dest.setUnboxingLong(AutoboxingRuntime.unbox(ConverterRuntime.convert(src.getUnboxingInt(),
				intToLongConverter)));
		return dest;
	}

	public void setIntToLongConverter(IntToLongConverter intToLongConverter) {
		this.intToLongConverter = intToLongConverter;
	}

}
