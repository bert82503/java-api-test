package leet.code.calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link CalculateTwo}.
 *
 * @author guangyi
 */
public class CalculateTwoTest {

    @Test(dataProvider = "calculateTestData")
    public void calculate(String str, int expected) {
        int result = CalculateTwo.calculate(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "calculateTestData")
    private static Object[][] calculateTestData() {
        return new Object[][]{
                {"1 + 1", 2},
                {" 2-1 + 2 ", 3},
                {"1+(2+3)", 6},
                {"(1+(4+5+2)-3)+(6+8)", 23},
                {"-1", -1},
                {"-0", 0},
        };
    }
}
