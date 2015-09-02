package alt.beanmapper.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import alt.beanmapper.context.BeanDescription;
import alt.beanmapper.context.PropertyDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class MappingBeanDescription implements BeanDescription {

	private List<MappingPropertyDescription> properties = new ArrayList<MappingPropertyDescription>();

	public MappingBeanDescription(Class<?> beanClass) {

		addAllFields(beanClass);

		visitAllMethods(beanClass);

	}

	private void visitAllMethods(Class<?> mappingClass) {

		traverseClassHierarchy(mappingClass, new Callback() {

			@Override
			public void onClass(Class<?> investigationClass) {

				for (Method method : investigationClass.getDeclaredMethods()) {

					if (!Modifier.isStatic(method.getModifiers())) {

						visitAllProperties(method);

					}
				}
			}

		});

	}

	private void visitAllProperties(Method method) {

		for (MappingPropertyDescription property : properties) {

			property.visitMethod(method);

		}

	}

	private void addAllFields(Class<?> mappingClass) {

		traverseClassHierarchy(mappingClass, new Callback() {

			@Override
			public void onClass(Class<?> investigationClass) {

				for (Field field : investigationClass.getDeclaredFields()) {

					if (!Modifier.isStatic(field.getModifiers())) {

						MappingPropertyDescription property = new MappingPropertyDescription(investigationClass, field);

						properties.add(property);

					}
				}
			}

		});

	}

	public PropertyDescription[] getProperties() {
		return properties.toArray(new PropertyDescription[properties.size()]);
	}

	private interface Callback {

		void onClass(Class<?> investigationClass);

	}

	protected void traverseClassHierarchy(Class<?> currentClass, Callback cb) {

		while (currentClass != null && currentClass != Object.class) {

			cb.onClass(currentClass);

			currentClass = currentClass.getSuperclass();

		}

	}

}
