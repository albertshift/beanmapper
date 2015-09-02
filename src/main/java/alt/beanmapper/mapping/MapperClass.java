package alt.beanmapper.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Albert Shift
 *
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface MapperClass {

	TypeCastStrategy typeCast() default TypeCastStrategy.DEFAULT;

	boolean jsr330() default false;

	String impl() default "";

}
