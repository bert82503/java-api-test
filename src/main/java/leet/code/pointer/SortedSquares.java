package leet.code.pointer;

/**
 * 977. 有序数组的平方
 * <p></p>
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 * <pre>
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 *
 * 进阶：
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 * </pre>
 * <pre>
 * 1.认识题目
 * 有序整数数组
 * 有序，按 非递减顺序 排序
 * 对于大于0的整数x，其平方也是递增的
 * 对于小于0的整数x，其平方是递减的
 * </pre>
 *
 * @author guangyi
 */
public class SortedSquares {

    public static int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int[] squares = new int[length];
        // 从右向左填充平方最大值元素
        int tail = length - 1;
        int left = 0;
        int right = length - 1;
        int num;
        while (left <= right) {
            int leftNum = nums[left];
            int rightNum = nums[right];
            int leftSquare = leftNum * leftNum;
            int rightSquare = rightNum * rightNum;
            if (rightSquare > leftSquare) {
                num = rightSquare;
                right--;
            } else {
                num = leftSquare;
                left++;
            }
            squares[tail--] = num;
        }
        return squares;
    }
}
