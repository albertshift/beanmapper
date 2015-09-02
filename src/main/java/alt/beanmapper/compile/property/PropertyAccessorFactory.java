package alt.beanmapper.compile.property;

import org.objectweb.asm.Type;

import alt.beanmapper.compile.MapperClassCompiler;
import alt.beanmapper.context.PropertyDescription;
import alt.beanmapper.support.BeanMapperException;
import alt.beanmapper.support.MappingException;

/**
 * 
 * @author Albert Shift
 *
 */

public final class PropertyAccessorFactory {

	private final String implPackageName;
	private final Type srcType;
	private final Type destType;

	public PropertyAccessorFactory(MapperClassCompiler mapperClassCompiler, Type srcType, Type destType) {
		this.implPackageName = mapperClassCompiler.getImplPackageName();
		this.srcType = srcType;
		this.destType = destType;
	}

	public PropertyAccessor getSourcePropertyAccessor(PropertyDescription prop) {

		if (prop.isAccessibleByField(implPackageName)) {
			return new GetFieldPropertyAccessor(srcType, prop);
		} else if (prop.isAccessibleByGetter(implPackageName)) {
			return new GetterPropertyAccessor(srcType, prop);
		} else {
			throw new MappingException("field is not accessible and getter not found for " + prop.getName()
					+ " in " + srcType.getClassName());
		}
	}

	public PropertyAccessor getDestinationPropertyAccessor(PropertyDescription prop) {

		if (prop.isAccessibleByField(implPackageName)) {
			return new SetFieldPropertyAccessor(destType, prop);
		} else if (prop.isAccessibleBySetter(implPackageName)) {
			return new SetterPropertyAccessor(destType, prop);
		} else {
			throw new MappingException("field is not accessible and setter not found for " + prop.getName()
					+ " in " + destType.getClassName());
		}
	}

}
