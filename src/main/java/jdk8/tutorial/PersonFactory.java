package jdk8.tutorial;

import lombok.Getter;
import lombok.ToString;

/**
 * how the :: keyword works for constructors. (构造方法引用)
 *
 * @author dannong
 * @since 2016年10月25日 10:17
 */
// 2. specify a person factory interface to be used for creating new persons
public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

@ToString
class Person {
    @Getter
    private String firstName;
    @Getter
    private String lastName;

    // 1. define different constructors
    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
