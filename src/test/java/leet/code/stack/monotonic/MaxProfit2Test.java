package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MaxProfit2}.
 *
 * @author guangyi
 */
public class MaxProfit2Test {

    @Test(dataProvider = "maxProfitTestData")
    public void maxProfit(int[] prices, int expected) {
        int result = MaxProfit2.maxProfit(prices);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "maxProfitTestData")
    private static Object[][] maxProfitTestData() {
        return new Object[][]{
                {new int[]{7,1,5,3,6,4},
                        7},
                {new int[]{1,2,3,4,5},
                        4},
                {new int[]{7,6,4,3,1},
                        0},
                {new int[]{1,1},
                        0},
                {new int[]{0,0},
                        0},
                {new int[]{1},
                        0},
                {new int[]{0},
                        0},
//                {new int[]{},
//                },
        };
    }
}
