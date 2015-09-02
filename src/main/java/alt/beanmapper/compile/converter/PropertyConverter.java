package alt.beanmapper.compile.converter;

import org.objectweb.asm.MethodVisitor;

import alt.beanmapper.compile.property.PropertyAccessor;

public abstract class PropertyConverter {

	protected final PropertyAccessor srcPropertyAccessor;
	protected final PropertyAccessor destPropertyAccessor;

	public PropertyConverter(PropertyAccessor srcPropertyAccessor, PropertyAccessor destPropertyAccessor) {
		this.srcPropertyAccessor = srcPropertyAccessor;
		this.destPropertyAccessor = destPropertyAccessor;
	}

	public abstract void convert(MethodVisitor mv);

}
