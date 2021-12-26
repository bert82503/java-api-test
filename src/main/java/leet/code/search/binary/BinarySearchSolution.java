package leet.code.search.binary;

/**
 * <a href="https://leetcode-cn.com/problems/binary-search/">
 *     704. 二分查找</a>
 *
 * @author guangyi
 * @since 2021-12-26
 */
public class BinarySearchSolution {

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * @param nums   有序的的整型数组，升序
     * @param target 目标值
     * @return 目标值所在的数组下标，未找到返回-1
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    /**
     * 递归地二分搜索。
     *
     * @param nums       有序的的整型数组，升序
     * @param startIndex 起始下标，包含
     * @param endIndex   终止下标，包含
     * @param target     目标值
     * @return 目标值所在的数组下标，未找到返回-1
     */
    public static int binarySearch(int[] nums, int startIndex, int endIndex, int target) {
        // 递归搜索的终止条件
        if (startIndex > endIndex) {
            return -1;
        } else if (startIndex == endIndex) {
            if (nums[startIndex] == target) {
                // 发现目标值
                return startIndex;
            }
            return -1;
        }
        // 中区下标
        int midIndex = (startIndex + endIndex) / 2;
        int num = nums[midIndex];
        if (num == target) {
            // 发现目标值
            return midIndex;
        } else if (num < target) {
            // 递归地二分搜索下半区
            return binarySearch(nums, midIndex + 1, endIndex, target);
        } else {
            // 递归地二分搜索上半区
            return binarySearch(nums, startIndex, midIndex, target);
        }
    }
}
