package alt.beanmapper.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import alt.beanmapper.context.PropertyDescription;
import alt.beanmapper.support.BeanMapperException;
import alt.beanmapper.util.BeanUtil;
import alt.beanmapper.util.StringUtil;

/**
 * 
 * @author Albert Shift
 *
 */

public class MappingPropertyDescription implements PropertyDescription {

	private final String classPackageName;
	private final String propertyName;
	private final Field field;
	private final AccessType fieldAccess;

	private final String getterMethodName;
	private final String checkerMethodName;
	private Method getterMethod = null;
	private AccessType getterAccess = null;

	private final String setterMethodName;
	private Method setterMethod = null;
	private Class<?> setterMethodArg = null;
	private AccessType setterAccess = null;

	public MappingPropertyDescription(Class<?> beanClass, Field propertyField) {

		Package beanPackage = beanClass.getPackage();
		this.classPackageName = beanPackage != null ? beanPackage.getName() : null;

		this.propertyName = propertyField.getName();
		this.field = propertyField;
		this.fieldAccess = getAccessType(propertyField.getModifiers());

		this.getterMethodName = BeanUtil.getGetterName(propertyName);
		this.checkerMethodName = BeanUtil.getCheckerName(propertyName);
		this.setterMethodName = BeanUtil.getSetterName(propertyName);

	}

	public void visitMethod(Method method) {

		String methodName = method.getName();

		if (getterMethod == null && (methodName.equals(getterMethodName) || methodName.equals(checkerMethodName))) {

			if (method.getParameterTypes().length == 0 && method.getReturnType().isAssignableFrom(field.getType())) {

				getterMethod = method;
				getterAccess = getAccessType(method.getModifiers());
			}

		}

		if (methodName.equals(setterMethodName)) {

			Class<?>[] args = method.getParameterTypes();
			if (args.length == 1) {

				Class<?> methodArg = args[0];

				if (setterMethod == null || setterMethodArg.isAssignableFrom(methodArg)) {

					if (field.getType().isAssignableFrom(methodArg) && method.getReturnType() == void.class) {

						setterMethod = method;
						setterMethodArg = methodArg;
						setterAccess = getAccessType(method.getModifiers());

					}

				}

			}

		}

	}

	@Override
	public String getName() {
		return propertyName;
	}

	@Override
	public Class<?> getFieldType() {
		return field.getType();
	}

	@Override
	public String getGetterName() {
		return getterMethod != null ? getterMethod.getName() : null;
	}

	@Override
	public Class<?> getGetterType() {
		return getterMethod != null ? getterMethod.getReturnType() : null;
	}

	@Override
	public String getSetterName() {
		return setterMethodName;
	}

	@Override
	public Class<?> getSetterType() {
		return setterMethodArg;
	}

	@Override
	public boolean isAccessibleByField(String fromPackageName) {
		return isAccessible(fieldAccess, fromPackageName);
	}

	@Override
	public boolean isAccessibleByGetter(String fromPackageName) {
		return getterAccess != null ? isAccessible(getterAccess, fromPackageName) : false;
	}

	@Override
	public boolean isAccessibleBySetter(String fromPackageName) {
		return setterAccess != null ? isAccessible(setterAccess, fromPackageName) : false;
	}

	private enum AccessType {
		PUBLIC, PROTECTED, PRIVATE;
	}

	private AccessType getAccessType(int mod) {
		if (Modifier.isPublic(mod)) {
			return AccessType.PUBLIC;
		}
		if (Modifier.isProtected(mod)) {
			return AccessType.PROTECTED;
		}
		if (Modifier.isPrivate(mod)) {
			return AccessType.PRIVATE;
		}
		throw new BeanMapperException("unknown modifiers " + mod);
	}

	private boolean isAccessible(AccessType access, String fromPackageName) {
		if (access == AccessType.PUBLIC) {
			return true;
		}
		if (access == AccessType.PROTECTED && StringUtil.nullableEquals(classPackageName, fromPackageName)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "MappingPropertyDescription [classPackageName=" + classPackageName + ", propertyName="
				+ propertyName + ", field=" + field + ", fieldAccess=" + fieldAccess + ", getterMethodName="
				+ getterMethodName + ", checkerMethodName=" + checkerMethodName + ", getterMethod=" + getterMethod
				+ ", getterAccess=" + getterAccess + ", setterMethodName=" + setterMethodName + ", setterMethod="
				+ setterMethod + ", setterMethodArg=" + setterMethodArg + ", setterAccess=" + setterAccess + "]";
	}

}
