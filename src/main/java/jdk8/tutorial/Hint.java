package jdk8.tutorial;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 提示注解。
 *
 * @author dannong
 * @since 2016年10月27日 11:46
 */
@Target({TYPE, FIELD, METHOD, TYPE_PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
