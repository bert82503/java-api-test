package leet.code.search.binary;

/**
 * 162. 寻找峰值
 * <p></p>
 * https://leetcode-cn.com/problems/find-peak-element/
 * <p></p>
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 提示：
 * * 1 <= nums.length <= 1000
 * * -2^31 <= nums[i] <= 2^31 - 1
 * * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * <p></p>
 * 特性：
 * 1. 题目让我们实现一个 O(log n) 算法，这是对使用「二分」的强烈暗示
 * 2. 应当从是否具有「二段性」来考虑是否可以进行「二分」
 * 3. 证明 11 ：对于任意数组而言，一定存在峰值（一定有解）
 * 4. 证明 22 ：二分不会错过峰值
 * 5. 我们证明了始终选择大于边界一端进行二分，可以确保选择的区间一定存在峰值，并随着二分过程不断逼近峰值位置。
 *
 * @author guangyi
 */
public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
