package leet.code.one;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 1. 两数之和。
 * <pre>
 * 执行用时：3 ms，击败了 99.14% 的用户
 * 内存消耗：36.7 MB，击败了 94.00% 的用户
 * </pre>
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @since 2019-09-21
 */
public class TwoNumSum {
    /**
     * 考察点：
     * <pre>
     * 1.整数可能包含负数
     * 2.整数可能重复，故相同整数的数组下标可能存在多个值，且有序，但最多使用最前面的两个数组下标
     * 3.整数和数组下标映射的哈希表(HashMap: <num, index>)
     * </pre>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("No two sum solution");
        }
        int len = nums.length;
        // 整数和数组下标映射的哈希表(<num, index>)
        Map<Integer, Integer> numIndexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            // 另一个操作数
            int operand = target - num;
            Integer index = numIndexMap.get(operand);
            if (index != null) {
                return new int[]{index, i};
            } else {
                numIndexMap.put(num, i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    @Test(dataProvider = "twoSumTestData")
    public void twoSumTest(int[] nums, int target, int[] expected) {
        int[] result = twoSum(nums, target);
        assertThat(result).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "twoSumTestData")
    public static Object[][] twoSumTestData() {
        return new Object[][]{
                {new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}},
                {new int[]{2, 7, 11, 15}, 26, new int[]{2, 3}},
                {new int[]{3, 2, 4}, 6, new int[]{1, 2}},
                {new int[]{3, 2, 4}, 7, new int[]{0, 2}},
                {new int[]{3, 2, 4, 3}, 6, new int[]{1, 2}},
                {new int[]{3, 3}, 6, new int[]{0, 1}},
        };
    }

}
