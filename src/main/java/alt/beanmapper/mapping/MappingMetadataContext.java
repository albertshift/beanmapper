package alt.beanmapper.mapping;

import java.util.HashMap;
import java.util.Map;

import alt.beanmapper.context.BeanDescription;
import alt.beanmapper.context.MetadataContext;
import alt.beanmapper.context.PropertyDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class MappingMetadataContext implements MetadataContext {

	private final Map<Class<?>, BeanDescription> beanDescriptions = new HashMap<Class<?>, BeanDescription>();

	@Override
	public PropertyDescription[] getProperties(Class<?> beanClass) {
		return getBeanDescription(beanClass).getProperties();
	}

	@Override
	public void clear() {
		beanDescriptions.clear();
	}

	private BeanDescription getBeanDescription(Class<?> beanClass) {
		BeanDescription beanDescription = beanDescriptions.get(beanClass);
		if (beanDescription == null) {
			beanDescription = new MappingBeanDescription(beanClass);
			beanDescriptions.put(beanClass, beanDescription);
		}
		return beanDescription;
	}

}
