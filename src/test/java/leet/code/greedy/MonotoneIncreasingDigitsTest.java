package leet.code.greedy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MonotoneIncreasingDigits}.
 *
 * @author guangyi
 */
public class MonotoneIncreasingDigitsTest {

    @Test(dataProvider = "monotoneIncreasingDigitsTestData")
    public void monotoneIncreasingDigits(int n, int expected) {
        int result = MonotoneIncreasingDigits.monotoneIncreasingDigits(n);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "monotoneIncreasingDigitsTestData")
    private static Object[][] monotoneIncreasingDigitsTestData() {
        return new Object[][]{
                {10, 9},
                {1234, 1234},
                {332, 299},
                {9, 9},
                {0, 0},
        };
    }
}
