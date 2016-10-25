package sun.util.function;

import org.testng.annotations.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of {@link Function}.
 * <p>
 * 函数：接受一个参数并产生一个结果
 *
 * @author dannong
 * @since 2016年10月25日 15:37
 */
public class FunctionTest {
    @Test
    public void functionTest() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        assertThat(toInteger.apply("123")).isEqualTo(123);
        assertThat(backToString.apply("123")).isEqualTo("123");
    }
}
