package leet.code.math;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ReverseInteger}.
 *
 * @author guangyi
 */
public class ReverseIntegerTest {

    @Test(dataProvider = "reverseTestData")
    public void reverse(int x, int expected) {
        int result = ReverseInteger.reverse(x);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "reverseTestData")
    private static Object[][] reverseTestData() {
        return new Object[][]{
                {123, 321},
                {-123, -321},
                {120, 21},
                {1, 1},
                {0, 0},
                {-1, -1},
                // 边界测试
                // 超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1]
                {Integer.MAX_VALUE, 0},
                {Integer.MIN_VALUE, 0}
        };
    }
}
