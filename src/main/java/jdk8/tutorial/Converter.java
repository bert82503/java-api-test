package jdk8.tutorial;

/**
 * Functional Interfaces. (函数式接口)
 *
 * @author dannong
 * @since 2016年10月24日
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
