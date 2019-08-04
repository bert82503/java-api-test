package jdk8.tutorial;

/**
 * how the :: keyword works for constructors. (构造方法引用)
 *
 * @author dannong
 * @since 2016年10月25日
 */
// 2. specify a person factory interface to be used for creating new persons
public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

