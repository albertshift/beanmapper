package alt.beanmapper.compile.converter;

import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.MapperClass;
import alt.beanmapper.mapping.Property;

/**
 * 
 * @author Albert Shift
 *
 */

@MapperClass(impl = "ConverterReferenceMapperImpl", jsr330 = true)
public interface ConverterMapper {

	@Mapper({ @Property(src = "primitiveInt", dest = "primitiveLong", converter = IntToLongConverter.class),
			@Property(src = "wrapperedInt", dest = "wrapperedLong", converter = IntToLongConverter.class),
			@Property(src = "boxingInt", dest = "boxingLong", converter = IntToLongConverter.class),
			@Property(src = "unboxingInt", dest = "unboxingLong", converter = IntToLongConverter.class) })
	ConverterDestination map(ConverterSource src);

}
