package leet.code.search.binary;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置。
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p></p>
 * 当存在一个目标值时，输出同一个位置。
 *
 * @author guangyi
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    private static final int[] ELEMENT_NOT_FOUND = new int[]{-1, -1};

    // 解法一，循环遍历，时间复杂度是O(n)

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

    // 解法二，基于二分查找的递归遍历，时间复杂度是O(n)

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置。
     * <p></p>
     * 当存在一个目标值时，输出同一个位置。
     *
     * @param nums   一个按照升序排列的整数数组
     * @param target 一个目标值
     */
    public static int[] searchRange(int[] nums, int target) {
        int firstIndex = 0;
        int lastIndex = nums.length - 1;
        // 递归查找目标值
        int curIndex = recursiveSearch(nums, firstIndex, lastIndex, target);
        if (curIndex >= 0 && curIndex <= nums.length - 1) {
            if (nums[curIndex] == target) {
                // 找到目标值
                // 向左查找
                firstIndex = curIndex;
                while (firstIndex >= 0 && nums[firstIndex] == target) {
                    firstIndex--;
                }
                // 向右查找
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
     * 使用递归查找目标值。
     *
     * @param nums       一个按照升序排列的整数数组
     * @param firstIndex 数组元素的的第一个位置
     * @param lastIndex  数组元素的的最后一个位置
     * @param target     一个目标值
     * @return 当前位置
     */
    public static int recursiveSearch(int[] nums, int firstIndex, int lastIndex, int target) {
        if (firstIndex < 0 || lastIndex >= nums.length || firstIndex > lastIndex) {
            // 递归终止条件
            // 数组越界
            return -1;
        }
        int midIndex = (firstIndex + lastIndex) / 2;
        int num = nums[midIndex];
        if (num == target) {
            // 递归终止条件
            return midIndex;
        } else if (num > target) {
            // 上半区递归查找
            return recursiveSearch(nums, firstIndex, midIndex - 1, target);
        } else {
            // 下半区递归查找
            return recursiveSearch(nums, midIndex + 1, lastIndex, target);
        }
    }
}
