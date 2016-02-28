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
    public void print(DateTimeFormatter formatter, long currentTimeMillis, String expected) {
        String actual = formatter.print(currentTimeMillis);
        assertThat(actual).isEqualTo(expected);
    }

    @DataProvider(name = "printTestData")
    public static Object[][] printTestData() {
        return new Object[][] {
                { DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"), 1454564227493L, "2016-02-04 13:37:07.493" },
        };
    }
}
