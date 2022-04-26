package leet.code.stack.monotonic;

/**
 * 896. 单调数列
 * <p></p>
 * https://leetcode-cn.com/problems/monotonic-array/
 * <pre>
 * 如果数组是单调递增或单调递减的，那么它是 单调 的。
 * 如果对于所有 i <= j，nums[i] <= nums[j]，那么数组 nums 是单调递增的。
 * 如果对于所有 i <= j，nums[i] >= nums[j]，那么数组 nums 是单调递减的。
 * 当给定的数组 nums 是单调数组时返回 true，否则返回 false。
 * </pre>
 *
 * @author guangyi
 */
public class IsMonotonic {
    /**
     * 方法一：一次遍历
     *
     * @param nums 整数数组
     * @return 是否为单调数列
     */
    public static boolean isMonotonic(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int len = nums.length - 1;
        // 技巧
        if (nums[len] >= nums[0]) {
            // 单调递增
            for (int i = 0; i < len; i++) {
                if (nums[i] > nums[i + 1]) {
                    return false;
                }
            }
        } else {
            // 单调递减
            for (int i = 0; i < len; i++) {
                if (nums[i] < nums[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
