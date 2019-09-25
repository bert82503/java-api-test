package sun.time;

import static org.assertj.core.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.testng.annotations.Test;

/**
 * Test of {@link LocalDate}. (当地日期：表示不同的日期)
 * <p>
 * {@link LocalDate}是不可变和线程安全的类。
 *
 * @author dannong
 * @since 2016年10月26日
 */
public class LocalDateTest {

    @Test
    public void functionTest() {
        // how to calculate new dates by adding or subtracting days, months or years
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1L, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2L);
        assertThat(tomorrow.isAfter(today)).isTrue();
        assertThat(yesterday.isBefore(today)).isTrue();

        LocalDate independenceDay = LocalDate.of(2016, Month.OCTOBER, 20);
        // 星期几
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.THURSDAY);

        // Parsing a LocalDate from a string
        DateTimeFormatter chinaFormatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(Locale.CHINA);
        LocalDate localDate = LocalDate.parse("2016-10-20", chinaFormatter);
        assertThat(localDate.toString()).isEqualTo("2016-10-20");
    }

}
