package sun.util.concurrent;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

/**
 * Test of {@link TimeUnit}.
 *
 * @since 2019-08-04
 */
public class TimeUnitTest {
    @Test
    public void toMillis() {
        assertThat(TimeUnit.SECONDS.toMillis(10L)).isEqualTo(1000L * 10);
        assertThat(TimeUnit.MINUTES.toMillis(10L)).isEqualTo(1000L * 60 * 10);
    }

    @Test
    public void toSeconds() {
        assertThat(TimeUnit.MILLISECONDS.toSeconds(1000L)).isEqualTo(1L);
        assertThat(TimeUnit.MINUTES.toSeconds(1L)).isEqualTo(60L);
    }
}
