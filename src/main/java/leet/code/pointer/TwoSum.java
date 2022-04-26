package leet.code.pointer;

/**
 * 167. 两数之和 II - 输入有序数组
 * <p></p>
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * <pre>
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 *
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 * 提示：
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 * </pre>
 * 一张图告诉你 O(n) 的双指针解法的本质原理
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/yi-zhang-tu-gao-su-ni-on-de-shuang-zhi-zhen-jie-fa/
 *
 * @author guangyi
 */
public class TwoSum {

    // 方法一：二分查找
    // 时间复杂度：O(n log n)
    // 空间复杂度：O(1)

    /**
     * 方法二：双指针
     * <pre>
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是数组的长度。两个指针移动的总次数最多为 n 次。
     * 空间复杂度：O(1)。
     * </pre>
     *
     * @param numbers 非递减顺序排列的整数数组
     * @param target  目标数
     * @return 从数组中找出满足相加之和等于目标数 target 的两个数的下标
     */
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                // sum > target
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
