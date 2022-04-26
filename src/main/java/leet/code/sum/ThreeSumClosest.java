package leet.code.sum;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * <p></p>
 * https://leetcode-cn.com/problems/3sum-closest/
 * <p></p>
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。
 * 请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 *
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * <p></p>
 * 特性：
 * 1. 全局最优解
 * 2. 变体：三数之和
 * 3. 与 target 最接近的三个数的和，等价于 min(abs(n1 + n2 + n3 - target))
 *
 * @author guangyi
 */
public class ThreeSumClosest {
    /**
     * 方法一：排序 + 双指针
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return 返回与 target 最接近的三个数的和
     */
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        // 三个数的和
        int result = 0;
        // 与 target 最接近的三个数的和
        int sumClosest = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int a = nums[i];
            if (i > 0 && a == nums[i - 1]) {
                // 剪枝：过滤重复解
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = a + nums[left] + nums[right];
                if (sum == target) {
                    result = sum;
                    return result;
                } else if (sum < target) {
                    left++;
                } else {
                    // sum > target
                    right--;
                }
                int abs = Math.abs(sum - target);
                if (abs < sumClosest) {
                    sumClosest = abs;
                    result = sum;
                }
            }
        }
        return result;
    }
}
