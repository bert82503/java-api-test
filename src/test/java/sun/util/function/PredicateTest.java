package sun.util.function;

import org.testng.annotations.Test;

import java.util.Objects;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of {@link Predicate}.
 * <p>
 * 谓词：一个参数的布尔值函数
 *
 * @author dannong
 * @since 2016年10月25日 15:15
 */
public class PredicateTest {

    private static final Predicate<CharSequence> predicate = cs -> cs.length() > 0;

    public static boolean isNotEmpty(CharSequence charSequence) {
        return predicate.test(charSequence);
    }

    @Test
    public void functionTest() {
        assertThat(isNotEmpty("")).isFalse();

        Predicate<String> predicate = s -> s.length() > 0;

        assertThat(predicate.test("foo")).isTrue();
        assertThat(predicate.negate().test("foo")).isFalse();


        // Objects
        Predicate<Boolean> nonNull = Objects::nonNull; // 静态方法引用
        Predicate<Boolean> isNull = Objects::isNull;

        assertThat(nonNull.test(null)).isFalse();
        assertThat(nonNull.test(Boolean.TRUE)).isTrue();
        assertThat(isNull.test(null)).isTrue();
        assertThat(isNull.test(Boolean.FALSE)).isFalse();


        // String
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate(); // 简化版（非null保护）
        assertThat(isEmpty.test("")).isTrue();
        assertThat(isEmpty.test("foo")).isFalse();
        assertThat(isNotEmpty.test("foo")).isTrue();
        assertThat(isNotEmpty.test("")).isFalse();
    }
}
