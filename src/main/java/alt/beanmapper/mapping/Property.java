package alt.beanmapper.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import alt.beanmapper.converter.Converter;
import alt.beanmapper.converter.DummyConverter;

/**
 * 
 * @author Albert Shift
 *
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Property {

	String src();

	String dest() default "";

	Class<? extends Converter<?, ?>> converter() default DummyConverter.class;

	TypeCastStrategy typeCast() default TypeCastStrategy.DEFAULT;

}
