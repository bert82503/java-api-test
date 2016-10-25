package sun.util.function;

import jdk8.tutorial.Person;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of {@link Comparator}.
 * <p>
 * 比较器
 *
 * @author dannong
 * @since 2016年10月25日 16:37
 */
public class ComparatorTest {
    @Test
    public void functionTest() {
        Comparator<Person> personComparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());

        Person person1 = new Person("John", "Doe");
        Person person2 = new Person("Alice", "Wonderland");

        assertThat(personComparator.compare(person1, person2)).isGreaterThan(0);
        assertThat(personComparator.reversed().compare(person1, person2)).isLessThan(0);
    }
}
