package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MaxProfit}.
 *
 * @author guangyi
 */
public class MaxProfitTest {
    @Test(dataProvider = "maxProfitTestData")
    public void maxProfit(int[] prices, int expected) {
        int result = MaxProfit.maxProfit(prices);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "maxProfitTestData")
    private static Object[][] maxProfitTestData() {
        return new Object[][]{
                {new int[]{7,1,5,3,6,4}, 5},
                {new int[]{1,3,6,7,9}, 8},
                {new int[]{1,2}, 1},
                {new int[]{2,1}, 0},
                {new int[]{1}, 0},
                {new int[]{7,6,4,3,1}, 0},
        };
    }
}
