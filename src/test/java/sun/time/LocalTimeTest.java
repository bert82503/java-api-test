package sun.time;

import org.testng.annotations.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LocalTime. (当地时间：表示一个时间)
 * <p>
 * {@link LocalTime}是不可变和线程安全的类。
 *
 * @author dannong
 * @since 2016年10月26日 14:57
 */
public class LocalTimeTest {
    @Test
    public void functionTest() {
        ZoneId berlinZone = ZoneId.of("Europe/Berlin");
        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");

        // compare both times and calculate the difference in hours and minutes between both times
        LocalTime berlinTime = LocalTime.now(berlinZone);
        LocalTime shanghaiTime = LocalTime.now(shanghaiZone);
        assertThat(berlinTime.isBefore(shanghaiTime)).isTrue();

        long hoursBetween = ChronoUnit.HOURS.between(berlinTime, shanghaiTime);
        long minutesBetween = ChronoUnit.MINUTES.between(berlinTime, shanghaiTime);
        assertThat(hoursBetween).isEqualTo(6L);
        assertThat(minutesBetween).isEqualTo(360L);

        // factory methods to simplify the creation of new instances
        LocalTime independenceTime = LocalTime.of(23, 59, 59);
        assertThat(independenceTime.toString()).isEqualTo("23:59:59");

        DateTimeFormatter germanFormatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);
        LocalTime parsedTime = LocalTime.parse("13:37", germanFormatter);
        assertThat(parsedTime.toString()).isEqualTo("13:37");
    }
}
