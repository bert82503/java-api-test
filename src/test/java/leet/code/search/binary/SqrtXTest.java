package leet.code.search.binary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SqrtX}.
 *
 * @author guangyi
 */
public class SqrtXTest {

    @Test(dataProvider = "mySqrtTestData")
    public void mySqrt(int x, int expected) {
        int result = SqrtX.mySqrt(x);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "mySqrtTestData")
    private static Object[][] mySqrtTestData() {
        return new Object[][]{
                // 边界，未考虑到
                {Integer.MAX_VALUE, (int) Math.sqrt(Integer.MAX_VALUE)},
                {101, 10},
                // 平方根
                {100, 10},
                {90, 9},
                {81, 9},
                {64, 8},
                {49, 7},
                {36, 6},
                {25, 5},
                {16, 4},
                {11, 3},
                {10, 3},
                {9, 3},
                {8, 2},
                {7, 2},
                {6, 2},
                {5, 2},
                {4, 2},
                {3, 1},
                {2, 1},
                {1, 1},
                // 边界
                {0, 0},
        };
    }
}
