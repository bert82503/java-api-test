package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 496. 下一个更大元素 I
 * <p></p>
 * https://leetcode-cn.com/problems/next-greater-element-i/
 * <pre>
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中 nums1 是 nums2 的子集。
 *
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。
 * 如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 *
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 *
 * 提示：
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 * 单调栈
 * </pre>
 *
 * @author guangyi
 */
public class NextGreaterElement {

    /**
     * 下一个更大元素 I
     * https://leetcode-cn.com/problems/next-greater-element-i/solution/xia-yi-ge-geng-da-yuan-su-i-by-leetcode-bfcoj/
     * <p></p>
     * 方法二：单调栈 + 哈希表
     * <pre>
     * 思路
     * 我们可以先预处理 nums2，使查询 nums1 中的每个元素在 nums2 中对应位置的右边的第一个更大的元素值时不需要再遍历 nums2。
     * 于是，我们将题目分解为两个子问题：
     * 第 1 个子问题：如何更高效地计算 nums2 中每个元素右边的第一个更大的值；
     * 第 2 个子问题：如何存储第 1 个子问题的结果。
     *
     * 算法
     * 我们可以使用单调栈来解决第 1 个子问题。
     * 倒序遍历 nums2，并用单调栈中维护当前位置右边的更大的元素列表，从栈底到栈顶的元素是单调递减的。
     *
     * 具体地，每次我们移动到数组中一个新的位置 i，就将当前单调栈中所有小于 nums2[i] 的元素弹出单调栈，
     * 当前位置右边的第一个更大的元素即为栈顶元素，如果栈为空则说明当前位置右边没有更大的元素。
     * 随后将位置 i 的元素入栈。
     *
     * 因为题目规定了 nums2 是没有重复元素的，所以可以使用哈希表来解决第 2 个子问题，
     * 将元素值与其右边第一个更大的元素值的对应关系存入哈希表。
     *
     * 细节
     * 因为在这道题中我们只需要用到 nums2 中元素的顺序而不需要用到下标，
     * 所以栈中直接存储 nums2 中元素的值即可。
     *
     * 复杂度分析
     * 时间复杂度：O(m + n)，其中 m 是 nums1 的长度，n 是 nums2 的长度。
     * 我们需要遍历 nums2 以计算 nums2 中每个元素右边的第一个更大的值；需要遍历 nums1 以生成查询结果。
     * 空间复杂度：O(n)，用于存储哈希表。
     * </pre>
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> nextGreaterElementMap = nextGreaterElement(nums2);
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = nextGreaterElementMap.get(nums1[i]);
        }
        return ans;
    }

    /**
     * 标识不存在下一个更大元素
     */
    private static final int NOT_EXIST_NEXT_GREATER_ELEMENT = -1;

    /**
     * Next Greater Number 的原始问题
     * <pre>
     * 给定一个数组 nums，请你返回一个等长的结果数组，结果数组中对应索引存储着下一个更大元素，如果没有更大的元素，就存 -1。
     *
     * nums 中数字 x 的 下一个更大元素 是指 x 在 nums 中对应位置 右侧 的 第一个 比 x 大的元素。
     * </pre>
     *
     * @param nums 没有重复元素的整数数组
     */
    private static Map<Integer, Integer> nextGreaterElement(int[] nums) {
        // 存放答案的哈希表
        // <num, nextGreaterElement>
        Map<Integer, Integer> result = new HashMap<>(nums.length * 4 / 3 + 1);
        // 单调递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        // 倒着往栈里放
        // 从右向左遍历，逆序遍历
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            // 判定个子高矮
            while (!indexMonoStack.isEmpty() && num >= nums[indexMonoStack.getFirst()]) {
                // 出栈
                // 矮个起开，反正也被挡着了
                indexMonoStack.removeFirst();
            }
            // 计算结果
            // nums[i] 身后的 next great number
            int nextGreaterElement = indexMonoStack.isEmpty() ? NOT_EXIST_NEXT_GREATER_ELEMENT :
                    nums[indexMonoStack.getFirst()];
            result.put(num, nextGreaterElement);
            // 入栈
            indexMonoStack.addFirst(i);
        }
        return result;
    }
}
