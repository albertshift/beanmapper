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
@Target(value = { ElementType.METHOD })
public @interface Mapper {

	Property[] value() default {};

	Class<? extends CustomMapper<?, ?>> custom() default DummyCustomMapper.class;

	TypeCastStrategy typeCast() default TypeCastStrategy.DEFAULT;

}
