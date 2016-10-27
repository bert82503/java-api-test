package jdk8.tutorial;

import lombok.Getter;
import lombok.ToString;

/**
 * 个人信息。
 *
 * @author dannong
 * @since 2016年10月25日 16:15
 */
@Hint("hint1")
@Hint("hint2")
@ToString
public class Person {
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
