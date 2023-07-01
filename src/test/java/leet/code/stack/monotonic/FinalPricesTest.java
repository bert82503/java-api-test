package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link FinalPrices}.
 *
 * @author guangyi
 */
public class FinalPricesTest {

    @Test(dataProvider = "finalPricesTestData")
    public void finalPrices(int[] prices, int[] expected) {
        int[] result = FinalPrices.finalPrices(prices);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "finalPricesTestData")
    private static Object[][] finalPricesTestData() {
        return new Object[][]{
                {new int[]{8,4,6,2,3},
                        new int[]{4,2,4,2,3}},
                {new int[]{1,2,3,4,5},
                        new int[]{1,2,3,4,5}},
                {new int[]{5,4,3,2,1},
                        new int[]{1,1,1,1,1}},
                {new int[]{10,1,1,6},
                        new int[]{9,0,1,6}},
                {new int[]{1,2},
                        new int[]{1,2}},
                {new int[]{2,1},
                        new int[]{1,1}},
                {new int[]{1,1,1},
                        new int[]{0,0,1}},
                {new int[]{1},
                        new int[]{1}},
//                {new int[]{},
//                        new int[]{}},
        };
    }
}
