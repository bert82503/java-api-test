package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MaxArea}.
 *
 * @author guangyi
 */
public class MaxAreaTest {

    @Test(dataProvider = "maxAreaTestData")
    public void maxArea(int[] height, int expected) {
        int result = MaxArea.maxArea(height);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "maxAreaTestData")
    private static Object[][] maxAreaTestData() {
        return new Object[][]{
                {new int[]{1,8,6,2,5,4,8,3,7}, 49},
                {new int[]{1,2,3,4}, 4},
                {new int[]{1,2,3}, 2},
                {new int[]{1,2}, 1},
                {new int[]{1,1}, 1},
        };
    }
}
