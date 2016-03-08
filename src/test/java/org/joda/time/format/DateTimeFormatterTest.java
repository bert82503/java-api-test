package org.joda.time.format;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
                {1454564227493L, "2016-02-04 13:37:07.493"},
        };
    }

}
