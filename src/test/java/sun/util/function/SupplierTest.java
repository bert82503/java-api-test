package sun.util.function;

import jdk8.tutorial.Person;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of {@link Supplier}.
 * <p>
 * 供应者：产生给定泛型类型的一个结果
 *
 * @author dannong
 * @since 2016年10月25日 16:08
 */
public class SupplierTest {
    @Test
    public void functionTest() {
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();// new Person
        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isNull();
        assertThat(person.getLastName()).isNull();
    }
}
