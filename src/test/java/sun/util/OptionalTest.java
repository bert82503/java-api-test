package sun.util;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of {@link Optional}.
 * <p>
 * 可选项：一个值的简单容器
 *
 * @author dannong
 * @since 2016年10月25日 16:53
 */
public class OptionalTest {
    @Test
    public void functionTest() {
        Optional<String> stringOptional = Optional.of("bam");

        assertThat(stringOptional.isPresent()).isTrue();
        assertThat(stringOptional.get()).isEqualTo("bam");
        assertThat(stringOptional.orElse("fallback")).isEqualTo("bam");

        stringOptional.ifPresent(s -> assertThat(s.charAt(0)).isEqualTo('b'));
    }
}
