package sun.time;

import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LocalDateTime. (当地日期时间：表示一个日期-时间)
 * <p>
 * {@link LocalDateTime}是不可变和线程安全的类。
 *
 * @author dannong
 * @since 2016年10月26日 20:17
 */
public class LocalDateTimeTest {
    @Test
    public void functionTest() {
        // utilize methods for retrieving certain fields from a date-time
        LocalDateTime independenceDateTime = LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 59, 59);
        // 星期几
        DayOfWeek dayOfWeek = independenceDateTime.getDayOfWeek();
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.SATURDAY);

        // 第几月份
        Month month = independenceDateTime.getMonth();
        assertThat(month).isEqualTo(Month.DECEMBER);

        long minuteOfHour = independenceDateTime.getLong(ChronoField.MINUTE_OF_HOUR);
        assertThat(minuteOfHour).isEqualTo(59);

        Instant instant = independenceDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant();
        Date date = Date.from(instant);
        assertThat(date.toString()).isEqualTo("Sat Dec 31 23:59:59 CST 2016");

        // Formatting date-times
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.CHINA);
        LocalDateTime localDateTime = LocalDateTime.parse("2016-10-20 07:13", formatter);
        String dateTimeString = formatter.format(localDateTime);
        assertThat(dateTimeString).isEqualTo("2016-10-20 07:13");
    }
}
