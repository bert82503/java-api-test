package jdk8.tutorial;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test of {@link PersonFactory}.
 *
 * @author dannong
 * @since 2016年10月25日 10:24
 */
public class PersonFactoryTest {
    @Test
    public void create() {
        PersonFactory<Person> personFactory = Person::new; // 构造方法引用
        Person person = personFactory.create("Peter", "Parker");

        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isEqualTo("Peter");
        assertThat(person.getLastName()).isEqualTo("Parker");
    }
}
