package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 503. 下一个更大元素 II
 * <p></p>
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 * <pre>
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 *
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1 。
 * </pre>
 *
 * @author guangyi
 */
public class NextGreaterElements {

    private static final int NOT_EXIST_NEXT_GREATER_ELEMENT = -1;

    public static int[] nextGreaterElements(int[] nums) {
        return nextGreaterElements_MonotoneStack(nums);
    }

    /**
     * 下一个更大元素 II
     * https://leetcode-cn.com/problems/next-greater-element-ii/solution/xia-yi-ge-geng-da-yuan-su-ii-by-leetcode-bwam/
     * <pre>
     * 方法一：单调栈 + 循环数组
     *
     * 思路及算法
     * 我们可以使用单调栈解决本题。单调栈中保存的是下标，从栈底到栈顶的下标在数组 nums 中对应的值是单调不升的。
     *
     * 一个朴素的思想是，我们可以把这个循环数组「拉直」，即复制该序列的前 n−1 个元素拼接在原序列的后面。
     * 这样我们就可以将这个新序列当作普通序列，用上文的方法来处理。
     * 而在本题中，我们不需要显性地将该循环数组「拉直」，而只需要在处理时对下标取模即可。
     * </pre>
     *
     * 为啥使用「单调栈」呀？从「朴素解法」的角度去理解「单调栈」
     * https://leetcode-cn.com/problems/next-greater-element-ii/solution/cong-po-su-jie-fa-de-jiao-du-qu-li-jie-d-trht/
     * <pre>
     * 对于「找最近一个比当前值大/小」的问题，都可以使用单调栈来解决。
     * 单调栈就是在栈的基础上维护一个栈内元素单调。
     *
     * 总结
     * 要从逻辑上去理解为什么能用「单调栈」解决问题：
     * 1. 我们希望将 O(n^2) 算法优化为 O(n) 算法，因此需要将「主动」获取答案转换为「被动」更新
     * 2. 我们需要使用数据结构保持那些「尚未更新」的位置下标，由于题目要求的是找「下一个更大的元素」，因此使用栈来保存
     * 3. 「被动」更新答案的逻辑导致了栈内元素单调
     * </pre>
     */
    public static int[] nextGreaterElements_MonotoneStack(int[] nums) {
        int len = nums.length;
        // 保存结果的数组
        int[] result = new int[len];
        // 递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        for (int i = 2 * len - 2; i >= 0; i--) {
            int index = i % len;
            int num = nums[index];
            while (!indexMonoStack.isEmpty() && num >= nums[indexMonoStack.getFirst()]) {
                indexMonoStack.removeFirst();
            }
            result[index] = indexMonoStack.isEmpty() ? NOT_EXIST_NEXT_GREATER_ELEMENT :
                    nums[indexMonoStack.getFirst()];
            indexMonoStack.addFirst(index);
        }
        return result;
    }
}
