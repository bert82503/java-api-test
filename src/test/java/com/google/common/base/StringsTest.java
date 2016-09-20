package com.google.common.base;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test of {@link Strings}.
 *
 * @author xingle
 * @since 2016年09月20日 11:28
 */
public class StringsTest {

    @Test(dataProvider = "isNullOrEmptyTestData")
    public void isNullOrEmpty(String str, boolean expected) {
        boolean result = Strings.isNullOrEmpty(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "isNullOrEmptyTestData")
    private static Object[][] isNullOrEmptyTestData() {
        return new Object[][]{
                {null, true},
                {"", true},
                {"Jordan", false},
        };
    }


    @Test(dataProvider = "nullToEmptyTestData")
    public void nullToEmpty(String str, String expected) {
        String result = Strings.nullToEmpty(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "nullToEmptyTestData")
    private static Object[][] nullToEmptyTestData() {
        return new Object[][]{
                {null, ""},
                {"", ""},
                {"start2463%$^!@#&(723day", "start2463%$^!@#&(723day"},
        };
    }

}
