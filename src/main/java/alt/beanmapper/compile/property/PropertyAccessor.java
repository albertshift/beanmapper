package alt.beanmapper.compile.property;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public interface PropertyAccessor {

	String getPropertyName();

	String getBeanClassName();

	Class<?> getPropertyClass();

	Type getPropertyType();

	void implement(MethodVisitor mv);
}
