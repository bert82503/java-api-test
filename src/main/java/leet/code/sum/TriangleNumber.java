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
 * <pre>
 * 解题思路：
 * 判断三条边能组成三角形的条件为：
 * 任意两边之和大于第三边，任意两边之差小于第三边。
 * 三条边长从小到大为 a、b、c，当且仅当 a + b > c 这三条边能组成三角形。
 *
 * 方法二：二分查找
 * 首先对数组排序。
 * 固定最短的两条边，二分查找最后一个小于两边之和的位置。可以求得固定两条边长之和满足条件的结果。枚举结束后，总和就是答案。
 * 时间复杂度为 O(n^2logN)。
 *
 * https://leetcode-cn.com/problems/valid-triangle-number/solution/ming-que-tiao-jian-jin-xing-qiu-jie-by-jerring/
 * </pre>
 *
 * @author guangyi
 */
public class TriangleNumber {

    // 方法一：排序 + 二分查找

    /**
     * 方法二：排序 + 双指针
     *
     * @param nums 非负整数的数组
     * @return 返回其中可以组成三角形三条边的三元组个数
     */
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
