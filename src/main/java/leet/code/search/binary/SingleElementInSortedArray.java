package leet.code.search.binary;

/**
 * 540. 有序数组中的单一元素
 * <p></p>
 * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 * <p></p>
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p></p>
 * 特性：
 * 1. 整数组成的有序数组
 * 2. 数组元素的数量为奇数
 * 3. 变体：返回只出现一次的那个数，搜索左右相邻的元素是否存在相等
 *
 * @author guangyi
 */
public class SingleElementInSortedArray {

    /**
     * 有序数组中的单一元素
     * <p></p>
     * 特性：
     * 1. 整数组成的有序数组
     * 2. 数组元素的数量为奇数
     * 3. 变体：返回只出现一次的那个数，搜索左右相邻的元素是否存在相等
     *
     * @param nums 整数组成的有序数组
     * @return 返回只出现一次的那个数
     */
    public static int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        // 二分查找左边界
        while (left < right) {
            // 偶数
            int mid = (left + right) / 2;
            // 位异或的性质
            if (nums[mid] == nums[mid ^ 1]) {
                // 收缩左边界
                left = mid + 1;
            } else {
                // 收缩右边界
                right = mid;
            }
        }
        return nums[left];
    }
}
