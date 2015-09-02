package alt.beanmapper.compile;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import alt.beanmapper.compile.converter.PropertyConverter;
import alt.beanmapper.compile.converter.PropertyConverterFactory;
import alt.beanmapper.compile.options.MapperOptions;
import alt.beanmapper.compile.options.PropertyOptions;
import alt.beanmapper.compile.property.PropertyAccessor;
import alt.beanmapper.compile.property.PropertyAccessorFactory;
import alt.beanmapper.context.PropertyDescription;
import alt.beanmapper.mapping.Property;

/**
 * 
 * @author Albert Shift
 *
 */

public final class CopyPropertyCompiler implements Opcodes {

	private final PropertyDescription srcProp;
	private final PropertyDescription destProp;

	private final PropertyOptions propertyOptions;

	public CopyPropertyCompiler(PropertyDescription srcProp, PropertyDescription destProp,
			Property fieldAnnotation, MapperOptions mapperOptions) {
		this.srcProp = srcProp;
		this.destProp = destProp;
		this.propertyOptions = new PropertyOptions(mapperOptions, fieldAnnotation);
	}

	public void implement(MethodVisitor mv, PropertyAccessorFactory propertyAccessorFactory,
			PropertyConverterFactory propertyConverterFactory) {
		mv.visitVarInsn(ALOAD, 2);
		mv.visitVarInsn(ALOAD, 1);

		PropertyAccessor srcPropertyAccessor = propertyAccessorFactory.getSourcePropertyAccessor(srcProp);
		PropertyAccessor destPropertyAccessor = propertyAccessorFactory.getDestinationPropertyAccessor(destProp);

		srcPropertyAccessor.implement(mv);

		PropertyConverter propertyConverter = propertyConverterFactory.createPropertyConverter(
				srcPropertyAccessor, destPropertyAccessor, propertyOptions);

		if (propertyConverter != null) {
			propertyConverter.convert(mv);
		}

		destPropertyAccessor.implement(mv);
	}

}
