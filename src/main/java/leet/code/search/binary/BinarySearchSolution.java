package leet.code.search.binary;

/**
 * 704. 二分查找
 * <p></p>
 * https://leetcode-cn.com/problems/binary-search/
 * <p></p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p></p>
 * 特性：
 * 1. 有序的（升序）整型数组
 * 2. 一个目标值，并返回其下标
 *
 * @author guangyi
 */
public class BinarySearchSolution {

    // 解法二，基于递归搜索的二分查找，时间复杂度为 O(log n)

    /**
     * 二分查找
     * <p></p>
     * 特性：
     * 1. 有序的（升序）整型数组
     * 2. 一个目标值，并返回其下标
     *
     * @param nums   有序的的整型数组，升序
     * @param target 一个目标值
     * @return 目标值所在的数组下标，未找到返回-1
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    /**
     * 使用二分查找目标值。
     * <p></p>
     * 基于递归搜索的二分查找。
     *
     * @param nums       有序的的整型数组，升序
     * @param startIndex 起始下标，包含
     * @param endIndex   终止下标，不包含
     * @param target     一个目标值
     * @return 目标值所在的数组下标，未找到返回-1
     */
    public static int binarySearch(int[] nums, int startIndex, int endIndex, int target) {
        if (startIndex < 0 || endIndex >= nums.length || startIndex > endIndex) {
            // 递归终止条件
            // 数组越界
            return -1;
        }
        int midIndex = (startIndex + endIndex) / 2;
        int num = nums[midIndex];
        if (num == target) {
            // 递归终止条件
            return midIndex;
        } else if (num > target) {
            // 上半区递归查找
            return binarySearch(nums, startIndex, midIndex - 1, target);
        } else if (num < target) {
            // 下半区递归查找
            return binarySearch(nums, midIndex + 1, endIndex, target);
        }
        return -1;
    }


    // 解法一，循环遍历，时间复杂度为 O(n)

//    /**
//     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
//     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
//     * <pre>
//     * 过程：
//     *   1.设定左右指针
//     *   2.找出中间位置，并判断该位置值是否等于 target
//     *   3.nums[mid] == target，则返回该位置下标
//     *   4.nums[mid] > target，则右侧指针移到中间
//     *   5.nums[mid] < target，则左侧指针移到中间
//     * </pre>
//     *
//     * @param nums   有序的的整型数组，升序
//     * @param target 目标值
//     * @return 目标值所在的数组下标，未找到返回-1
//     */
//    public static int search(int[] nums, int target) {
//        if (nums == null || nums.length <= 0) {
//            return -1;
//        }
//        // 1.设定左右指针
//        int leftIndex = 0;
//        int rightIndex = nums.length - 1;
//        int midIndex;
//        while (leftIndex <= rightIndex) {
//            // 2.找出中间位置，并判断该位置值是否等于 target
//            midIndex = (leftIndex + rightIndex) / 2;
//            int midNum = nums[midIndex];
//            if (midNum == target) {
//                // 3.nums[mid] == target，则返回该位置下标
//                return midIndex;
//            } else if (midNum > target) {
//                // 4.nums[mid] > target，则右侧指针移到中间
//                rightIndex = midIndex - 1;
//            } else {
//                // 5.nums[mid] < target，则左侧指针移到中间
//                leftIndex = midIndex + 1;
//            }
//        }
//        return -1;
//    }
}
