package leet.code.search.binary;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p></p>
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p></p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 * * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 * 注意：当存在一个目标值时，输出同一个位置。
 * <p></p>
 * 特性：
 * 1. 一个按照升序排列的整数数组
 * 2. 变体：先查找目标值，再查找目标值在数组中的开始位置和结束位置
 *
 * @author guangyi
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    private static final int[] ELEMENT_NOT_FOUND = new int[]{-1, -1};

    // 解法二，基于递归搜索的二分查找，时间复杂度为 O(log n)

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     *
     * 注意：当存在一个目标值时，输出同一个位置。
     * <p></p>
     * 特性：
     * 1. 一个按照升序排列的整数数组
     * 2. 变体：先查找目标值，再查找目标值在数组中的开始位置和结束位置
     *
     * @param nums   一个按照升序排列的整数数组
     * @param target 一个目标值
     */
    public static int[] searchRange(int[] nums, int target) {
        int firstIndex = 0;
        int lastIndex = nums.length - 1;
        // 二分查找目标值
        int curIndex = binarySearch(nums, firstIndex, lastIndex, target);
        if (curIndex >= 0 && curIndex <= nums.length - 1) {
            if (nums[curIndex] == target) {
                // 发现目标
                // 向左低区间查找目标
                firstIndex = curIndex;
                while (firstIndex >= 0 && nums[firstIndex] == target) {
                    firstIndex--;
                }
                // 向右高区间查找目标
                lastIndex = curIndex;
                while (lastIndex < nums.length && nums[lastIndex] == target) {
                    lastIndex++;
                }
                return new int[]{firstIndex + 1, lastIndex - 1};
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 使用二分查找目标值。
     * <p></p>
     * 基于递归搜索的二分查找。
     *
     * @param nums       一个按照升序排列的整数数组
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
//     * 34. 在排序数组中查找元素的第一个和最后一个位置。
//     * <p></p>
//     * 当存在一个目标值时，输出同一个位置。
//     *
//     * @param nums   一个按照升序排列的整数数组
//     * @param target 一个目标值
//     */
//    public static int[] searchRange(int[] nums, int target) {
//        if (nums == null || nums.length <= 0) {
//            return ELEMENT_NOT_FOUND;
//        }
//        int firstIndex = 0;
//        int lastIndex = nums.length - 1;
//        // 1.从左到右查找元素的第一个位置
//        for (; firstIndex < nums.length; firstIndex++) {
//            if (nums[firstIndex] == target) {
//                break;
//            }
//        }
//        if (firstIndex >= nums.length) {
//            // 未找到目标值
//            return ELEMENT_NOT_FOUND;
//        }
//        // 2.从右到左元素的最后一个位置
//        for (; lastIndex >= firstIndex; lastIndex--) {
//            if (nums[lastIndex] == target) {
//                break;
//            }
//        }
//        if (lastIndex >= firstIndex) {
//            return new int[]{firstIndex, lastIndex};
//        }
//        return ELEMENT_NOT_FOUND;
//    }
}
