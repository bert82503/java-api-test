package sun.util.function;

import jdk8.tutorial.Person;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of {@link Consumer}.
 * <p>
 * 消费者：表示对单个输入参数要执行的操作
 *
 * @author dannong
 * @since 2016年10月25日 16:26
 */
public class ConsumerTest {
    @Test
    public void functionTest() {
        Consumer<Person> personConsumer = person -> assertThat(person.getFirstName()).isEqualTo("Luke");
        personConsumer.accept(new Person("Luke", "Walker"));
    }
}
