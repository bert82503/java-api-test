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
 * 对于大于0的整数x，其平方也是升序的
 * 对于小于0的整数x，其平方是降序的
 *
 * 在这道题中，最关键的点就是：原数组中的元素平方最大值一定产生在原数组的最左边或者最右边。
 * </pre>
 *
 * @author guangyi
 */
public class SortedSquares {

    /**
     * 有序数组的平方
     * https://leetcode-cn.com/problems/squares-of-a-sorted-array/solution/you-xu-shu-zu-de-ping-fang-by-leetcode-solution/
     * <pre>
     * 方法二：双指针
     * 思路与算法
     * 方法一没有利用「数组 nums 已经按照升序排序」这个条件。
     * 显然，如果数组 nums 中的所有数都是非负数，那么将每个数平方后，数组仍然保持升序；
     * 如果数组 nums 中的所有数都是负数，那么将每个数平方后，数组会保持降序。
     *
     * 这样一来，如果我们能够找到数组 nums 中负数与非负数的分界线，那么就可以用类似「归并排序」的方法了。
     * 由于我们得到了两个已经有序的子数组，因此就可以使用归并的方法进行排序了。
     *
     * 方法三：双指针
     * 思路与算法
     * 同样地，我们可以使用两个指针分别指向位置 0 和 n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
     * 这种方法无需处理某一指针移动至边界的情况，读者可以仔细思考其精髓所在。
     * </pre>
     */
    public static int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int[] squares = new int[length];
        // 从右向左填充平方最大值元素
        int pos = length - 1;
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
            squares[pos--] = num;
        }
        return squares;
    }
}
