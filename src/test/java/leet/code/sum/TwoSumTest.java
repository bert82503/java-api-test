package leet.code.sum;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link TwoSum}.
 *
 * @author guangyi
 */
public class TwoSumTest {

    @Test(dataProvider = "twoSumTestData")
    public void twoSum(int[] nums, int target, int[] expected) {
        int[] result = TwoSum.twoSum(nums, target);
        assertThat(result).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "twoSumTestData")
    public static Object[][] twoSumTestData() {
        return new Object[][]{
                // 最坏情况
                {new int[]{2, 7, 11, 15}, 17,
                        new int[]{0, 3}},
                {new int[]{2, 7, 11, 15}, 9,
                        new int[]{0, 1}},
                {new int[]{2, 7, 11, 15}, 26,
                        new int[]{2, 3}},
                {new int[]{3, 2, 4}, 6,
                        new int[]{1, 2}},
                {new int[]{3, 2, 4}, 7,
                        new int[]{0, 2}},
                {new int[]{3, 3, 1, 2}, 6,
                        new int[]{0, 1}},
                {new int[]{1, 4, 3, 3}, 6,
                        new int[]{2, 3}},
                {new int[]{1, 2, 3, 4, 1}, 2,
                        new int[]{0, 4}},
                {new int[]{0, 0}, 0,
                        new int[]{0, 1}},
                // 两个答案，不符合题目要求
//                {new int[]{3, 2, 4, 3}, 6,
//                        new int[]{1, 2}},
        };
    }
}
