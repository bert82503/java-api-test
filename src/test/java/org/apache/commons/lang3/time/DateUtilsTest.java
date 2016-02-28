package org.apache.commons.lang3.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link DateUtils}.
 *
 * @author xingle
 * @since 1.0
 */
public class DateUtilsTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(
            "yyyy-MM-dd HH:mm");

    @Test(dataProvider = "truncateTestData")
    public void truncate(long currentTimeMillis, int field, String expected) {
        Date currentDate = new Date(currentTimeMillis);
        long time = DateUtils.truncate(currentDate, field).getTime();
        String actual = DATE_TIME_FORMATTER.print(time);
        assertThat(actual).isEqualTo(expected);
    }

    @DataProvider(name = "truncateTestData")
    public static Object[][] truncateTestData() {
        return new Object[][] {
                // 2016-02-04 13:37:07.493
                { 1454564227493L, Calendar.DAY_OF_MONTH, "2016-02-04 00:00" },
                { 1454564227493L, Calendar.HOUR_OF_DAY, "2016-02-04 13:00" },
                // 2016-02-28 22:00:46.300
                { 1456668046300L, Calendar.DAY_OF_MONTH, "2016-02-28 00:00" },
                { 1456668046300L, Calendar.HOUR_OF_DAY, "2016-02-28 22:00" },
        };
    }

    @Test(dataProvider = "roundTestData")
    public void round(long currentTimeMillis, int field, String expected) {
        Date currentDate = new Date(currentTimeMillis);
        long time = DateUtils.round(currentDate, field).getTime();
        String actual = DATE_TIME_FORMATTER.print(time);
        assertThat(actual).isEqualTo(expected);
    }

    @DataProvider(name = "roundTestData")
    public static Object[][] roundTestData() {
        return new Object[][] {
                // 2016-02-04 13:37:07.493
                { 1454564227493L, Calendar.DAY_OF_MONTH, "2016-02-05 00:00" },
                { 1454564227493L, Calendar.HOUR_OF_DAY, "2016-02-04 14:00" },
                // 2016-02-28 22:00:46.300
                { 1456668046300L, Calendar.DAY_OF_MONTH, "2016-02-29 00:00" },
                { 1456668046300L, Calendar.HOUR_OF_DAY, "2016-02-28 22:00" },
        };
    }

}
