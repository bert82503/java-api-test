package leet.code.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 18. 四数之和
 * <p></p>
 * https://leetcode-cn.com/problems/4sum/
 * <pre>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * * 0 <= a, b, c, d < n
 * * a、b、c 和 d 互不相同
 * * nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * 你可以按 任意顺序 返回答案 。
 *
 * 四数之和与前面三数之和的思路几乎是一样的。
 *
 * 解决重复解：
 * 上面的解法存在重复解的原因是因为移动指针时可能出现重复数字的情况。
 * 所以我们要确保移动指针后，对应的数字要发生改变才行。
 * </pre>
 *
 * @author guangyi
 * @see ThreeSum
 */
public class FourSum {

    private static final int MIN_NUM_ARRAY_LEN = 4;

    // 方法一：排序 + 双指针

    /**
     * 排序 + 双指针
     *
     * 本题的难点在于如何去除重复解。
     *
     * @see ThreeSum#threeSum(int[])
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < MIN_NUM_ARRAY_LEN) {
            return Collections.emptyList();
        }
        // 2. 对数组进行排序
        Arrays.sort(nums);
        // 3. 遍历排序后数组：
        List<List<Integer>> result = new ArrayList<>(10);
        int len = nums.length;
        // a、b、c 和 d 互不相同
        for (int a = 0; a < len; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                // 对于重复元素：跳过，避免出现重复解
                // 确保nums[a]改变了
                continue;
            }
            for (int b = a + 1; b < len; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    // 对于重复元素：跳过，避免出现重复解
                    // 确保nums[b]改变了
                    continue;
                }
                int c = b + 1;
                int d = len - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        // 执行循环，判断左界和右界是否和下一位置重复，去除重复解
                        // 确保nums[c]改变了
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }
                        // 确保nums[d]改变了
                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }
                        c++;
                        d--;
                    } else if (sum > target) {
                        // 若和大于 0，说明 nums[R] 太大，R 左移
                        d--;
                    } else {
                        // sum < target
                        // 若和小于 0，说明 nums[L] 太小，L 右移
                        c++;
                    }
                }
            }
        }
        return result;
    }
}
