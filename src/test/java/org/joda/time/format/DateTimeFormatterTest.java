package org.joda.time.format;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test for {@link DateTimeFormatter}.
 *
 * @author xingle
 * @since 1.0
 */
public class DateTimeFormatterTest {
    @Test(dataProvider = "printTestData")
    public void print(long currentTimeMillis, String expected) {
        String actual = DATE_TIME_FORMATTER.print(currentTimeMillis);
        assertThat(actual).isEqualTo(expected);
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(
            "yyyy-MM-dd HH:mm:ss.SSS");

    @DataProvider(name = "printTestData")
    private static Object[][] printTestData() {
        return new Object[][]{
            {1505986098088L, "2017-09-21 17:28:18.088"},
            {1506068411484L, "2017-09-22 16:20:11.484"}
        };
    }
}
