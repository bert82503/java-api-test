package leet.code.search.binary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ArrangeCoins}.
 *
 * @author guangyi
 */
public class ArrangeCoinsTest {

    @Test(dataProvider = "arrangeCoinsTestData")
    public void arrangeCoins(int n, int expected) {
        int result = ArrangeCoins.arrangeCoins(n);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "arrangeCoinsTestData")
    private static Object[][] arrangeCoinsTestData() {
        return new Object[][]{
                // 边界，未考虑到
                {Integer.MAX_VALUE, 65535},
                // n*(n+1)/2
                {105, 14},
                {91, 13},
                {78, 12},
                {66, 11},
                {55, 10},
                {45, 9},
                {28, 7},
                {21, 6},
                {15, 5},
                {11, 4},
                {10, 4},
                {9, 3},
                {8, 3},
                {7, 3},
                {6, 3},
                {5, 2},
                {4, 2},
                {3, 2},
                {2, 1},
                // 边界
                {1, 1},
        };
    }
}
