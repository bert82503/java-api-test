package sun.time;

import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link Clock}. (时钟：提供当前日期和时间的访问)
 *
 * @author dannong
 * @since 2016年10月26日 14:16
 */
public class ClockTest {
    @Test
    public void functionTest() {
        // retrieve the current time in milliseconds since Unix EPOCH
        Clock clock = Clock.systemDefaultZone(); // SystemClock[Asia/Shanghai]
        long millis = clock.millis();
        assertThat(millis).isGreaterThan(1477538629060L);

        Instant instant = clock.instant(); // 2016-10-27T03:24:24.391Z
        Date date = Date.from(instant); // 2016-10-27T11:24:24.391+0800
        assertThat(date).isNotNull();
    }
}
