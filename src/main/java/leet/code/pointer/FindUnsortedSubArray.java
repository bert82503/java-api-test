package leet.code.pointer;

/**
 * 581. 最短无序连续子数组
 * <p></p>
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 * <pre>
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，
 * 如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * </pre>
 *
 * @author guangyi
 */
public class FindUnsortedSubArray {

    /**
     * 思路清晰明了，看不懂？？不存在的！！
     * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/si-lu-qing-xi-ming-liao-kan-bu-dong-bu-cun-zai-de-/
     * <pre>
     * 分析
     * 我们可以假设把这个数组分成三段，左段和右段是标准的升序数组，中段数组虽是无序的，但满足最小值大于左段的最大值，最大值小于右段的最小值。
     * </pre>
     */
    public static int findUnsortedSubarray(int[] nums) {
        int len = nums.length - 1;
        // 左段
        int max = nums[0], end = -1;
        // 右段
        int min = nums[len], begin = 0;
        for (int i = 0; i <= len; i++) {
            // 从左到右维持最大值，寻找右边界end
            if (nums[i] < max) {
                end = i;
            } else {
                max = nums[i];
            }
            // 从右到左维持最小值，寻找左边界begin
            if (nums[len - i] > min) {
                begin = len - i;
            } else {
                min = nums[len - i];
            }
        }
        return end == -1 ? 0 : end - begin + 1;
    }
}
