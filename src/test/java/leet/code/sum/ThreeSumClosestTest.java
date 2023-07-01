package leet.code.sum;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ThreeSumClosest}.
 *
 * @author guangyi
 */
public class ThreeSumClosestTest {
    @Test(dataProvider = "threeSumClosestTestData")
    public void threeSumClosest(int[] nums, int target, int expected) {
        int result = ThreeSumClosest.threeSumClosest(nums, target);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "threeSumClosestTestData")
    private static Object[][] threeSumClosestTestData() {
        return new Object[][]{
                {new int[]{1,2,3,5,8,13}, 23, 23},
                {new int[]{-1,2,1,-4}, 1, 2},
                {new int[]{0,0,0}, 1, 0},
                {new int[]{-1,2,1,-4}, -1, -1},
        };
    }
}
