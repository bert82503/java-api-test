package leet.code.calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link Calculate}.
 *
 * @author guangyi
 */
public class CalculateTest {

    @Test(dataProvider = "calculateTestData")
    public void calculate(String str, int expected) {
        int result = Calculate.calculate(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "calculateTestData")
    private static Object[][] calculateTestData() {
        return new Object[][]{
                {"1 + 1", 2},
                {"3+2*2", 7},
                {" 3/2 ", 1},
                {" 3+5 / 2 ", 5},
                {"5/2+3", 5},
                {"123", 123},
                {"1", 1},
                {"0", 0},
                {"+1", 1},
                {"+0", 0},
                {"-1", -1},
                {"-0", 0},
                // 未考虑到加减法计算顺序，从左往右
                {"1-1+1", 1},
        };
    }
}
