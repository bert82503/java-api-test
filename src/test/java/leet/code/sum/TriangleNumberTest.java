package leet.code.sum;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link TriangleNumber}.
 *
 * @author guangyi
 */
public class TriangleNumberTest {

    @Test(dataProvider = "triangleNumberTestData")
    public void triangleNumber(int[] nums, int expected) {
        int result = TriangleNumber.triangleNumber(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "triangleNumberTestData")
    private static Object[][] triangleNumberTestData() {
        return new Object[][]{
                {new int[]{2,2,3,4}, 3},
                {new int[]{4,2,3,4}, 4},
                {new int[]{2,3,4}, 1},
                // 无解
                {new int[]{1,2,3}, 0},
                {new int[]{1,2}, 0},
                {new int[]{1}, 0},
        };
    }
}
