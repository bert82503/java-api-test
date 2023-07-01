package sun.time;

import static org.assertj.core.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test of {@link LocalDateTime}. (当地日期时间：表示一个日期-时间)
 * <p>
 * {@link LocalDateTime}是不可变和线程安全的类。
 *
 * @author dannong
 * @since 2016年10月26日
 */
public class LocalDateTimeTest {
    /**
     * Test of {@link Instant#toEpochMilli()}, {@link System#currentTimeMillis()}.
     */
    @Test
    public void toEpochMilli() {
        long localDateTimeEpochMilli = LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        long instantEpochMilli = Instant.now().toEpochMilli();
        long currentTimeMillis = System.currentTimeMillis();
        assertThat(TimeUnit.MILLISECONDS.toSeconds(localDateTimeEpochMilli))
                .isEqualTo(TimeUnit.MILLISECONDS.toSeconds(instantEpochMilli))
                .isEqualTo(TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis));
    }

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

    // UT, unit test

    /**
     * <pre>
     * Instant Class
     * https://docs.oracle.com/javase/tutorial/datetime/iso/instant.html
     *
     * Date and Time Classes
     * https://docs.oracle.com/javase/tutorial/datetime/iso/datetime.html
     *
     * Parsing and Formatting
     * https://docs.oracle.com/javase/tutorial/datetime/iso/format.html
     * </pre>
     */
    @Test(dataProvider = "formatDateTimeTestData")
    public void formatDateTime(long timeMillis, String expected) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String result = localDateTime.format(dateTimeFormatter);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "formatDateTimeTestData")
    public static Object[][] formatDateTimeTestData() {
        return new Object[][]{
                {1569403175997L, "2019-09-25 17:19:35.997"},
                {1567966035000L, "2019-09-09 02:07:15.000"},
        };
    }

    @Test(dataProvider = "formatChineseDateTimeTestData")
    public void formatChineseDateTime(long timeMillis, String expected) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String result = localDateTime.format(dateTimeFormatter);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "formatChineseDateTimeTestData")
    public static Object[][] formatChineseDateTimeTestData() {
        return new Object[][]{
                {1569403175997L, "2019年09月25日"},
                {1567966035000L, "2019年09月09日"},
        };
    }
}
