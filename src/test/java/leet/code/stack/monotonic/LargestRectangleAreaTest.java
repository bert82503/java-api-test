package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link LargestRectangleArea}.
 *
 * @author guangyi
 */
public class LargestRectangleAreaTest {

    @Test(dataProvider = "largestRectangleAreaTestData")
    public void largestRectangleArea(int[] heights, int expected) {
        int result = LargestRectangleArea.largestRectangleArea(heights);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "largestRectangleAreaTestData")
    private static Object[][] largestRectangleAreaTestData() {
        return new Object[][]{
                {new int[]{2,1,5,6,2,3}, 10},
                {new int[]{2,4}, 4},
                {new int[]{4,4}, 8},
                {new int[]{1,4}, 4},
                {new int[]{1}, 1},
                {new int[]{0}, 0},
                // 未考虑到
                {new int[]{2,0,2}, 2},
        };
    }
}
