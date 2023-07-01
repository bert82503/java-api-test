package leet.code.search.binary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link PerfectSquare}.
 *
 * @author guangyi
 */
public class PerfectSquareTest {

    @Test(dataProvider = "isPerfectSquareTestData")
    public void isPerfectSquare(int num, boolean expected) {
        boolean result = PerfectSquare.isPerfectSquare(num);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "isPerfectSquareTestData")
    private static Object[][] isPerfectSquareTestData() {
        return new Object[][]{
                {Integer.MAX_VALUE, false},
                {102, false},
                {100, true},
                {16, true},
                {14, false},
                {4, true},
                {3, false},
                {2, false},
                {1, true},
        };
    }
}
