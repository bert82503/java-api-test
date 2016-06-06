package org.apache.commons.lang3.math;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link NumberUtils}.
 *
 * @author xingle
 * @since 2016年06月06日 12:29
 */
public class NumberUtilsTest {

    @Test(dataProvider = "isDigitsTestData")
    public void isDigits(String str, boolean expected) {
        boolean result = NumberUtils.isDigits(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "isDigitsTestData")
    private static Object[][] isDigitsTestData() {
        return new Object[][]{
                {"", false},
                {"a12", false},
                {"12b", false},
                {"+12", false},
                {"12.3", false},
                //
                {"1", true},
                {"123", true},
        };
    }
}
