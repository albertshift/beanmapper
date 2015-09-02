package alt.beanmapper.compile.property;

import org.objectweb.asm.Type;

/**
 * 
 * @author Albert Shift
 *
 */

public abstract class AbstractPropertyAccessor implements PropertyAccessor {

	protected final Type beanType;
	protected final Class<?> propertyClass;
	protected final Type propertyType;

	AbstractPropertyAccessor(Type beanType, Class<?> propertyClass) {
		this.beanType = beanType;
		this.propertyClass = propertyClass;
		this.propertyType = Type.getType(propertyClass);
	}

	@Override
	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	@Override
	public Type getPropertyType() {
		return propertyType;
	}

	@Override
	public String getBeanClassName() {
		return beanType.getClassName();
	}

}
