package alt.beanmapper.compile.converter;

import alt.beanmapper.compile.MapperClassCompiler;
import alt.beanmapper.compile.options.PropertyOptions;
import alt.beanmapper.compile.property.PropertyAccessor;
import alt.beanmapper.converter.Converter;
import alt.beanmapper.converter.DummyConverter;

/**
 * 
 * @author Albert Shift
 *
 */
public final class PropertyConverterFactory {

	private final MapperClassCompiler mapperClassCompiler;

	public PropertyConverterFactory(MapperClassCompiler mapperClassCompiler) {
		this.mapperClassCompiler = mapperClassCompiler;
	}

	public PropertyConverter createPropertyConverter(PropertyAccessor srcPropertyAccessor,
			PropertyAccessor destPropertyAccessor, PropertyOptions propertyOptions) {
		if (srcPropertyAccessor.getPropertyClass().isArray() && destPropertyAccessor.getPropertyClass().isArray()) {
			return new ArrayToArrayPropertyConverter(srcPropertyAccessor, destPropertyAccessor);
		}
		if (srcPropertyAccessor.getPropertyClass() != destPropertyAccessor.getPropertyClass()) {

			Class<? extends Converter<?, ?>> converterClass = propertyOptions.getConverterClass();

			if (converterClass != null && converterClass != DummyConverter.class) {
				return new CustomObjectToObjectPropertyConverter(mapperClassCompiler, srcPropertyAccessor,
						destPropertyAccessor, converterClass);
			} else {
				return new ObjectToObjectPropertyConverter(srcPropertyAccessor, destPropertyAccessor,
						propertyOptions.getTypeCastStrategy());
			}

		}
		return null;
	}

}
