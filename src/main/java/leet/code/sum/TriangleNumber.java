package leet.code.sum;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * <p></p>
 * https://leetcode-cn.com/problems/valid-triangle-number/
 * <p></p>
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * <p></p>
 * 特性：
 * 1. 三元组有序
 * 2. 三元组允许重复出现
 * 3. c是最大的
 *
 * @author guangyi
 */
public class TriangleNumber {
    public static int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // 数组排序
        Arrays.sort(nums);
        // 组成三角形三条边的三元组个数
        int result = 0;
        for (int i = nums.length - 1; i > 1; i--) {
            // c是最大的
            int c = nums[i];
            int right = i - 1;
            int left = 0;
            while (left < right) {
                int a = nums[left];
                int b = nums[right];
                if (a + b > c) {
                    result += right - left;
                    right--;
                } else {
                    // a + b <= c
                    left++;
                }
            }
        }
        return result;
    }
}
