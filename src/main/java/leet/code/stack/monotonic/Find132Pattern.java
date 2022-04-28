package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 当前项向**左**找第一个比自己**大**的位置：从左向右维护一个单调**递减栈**
 * 当前项向**左**找第一个比自己**小**的位置：从左向右维护一个单调**递增栈**
 *
 * @author guangyi
 */
public class Find132Pattern {

    /**
     * 详解为何使用「单调栈」来找最大的 K 是正确的
     * https://leetcode-cn.com/problems/132-pattern/solution/xiang-xin-ke-xue-xi-lie-xiang-jie-wei-he-95gt/
     * <pre>
     * 基本思路
     *
     * 因为如果存在 (j,k) 满足要求的话，我们只需要找到一个最大的满足条件的 k，通过与 i 的比较即可。
     *
     * 过程 & 证明
     * 这样做的本质是：我们通过维护「单调递减」来确保已经找到了有效的 (j,k)。
     * 换句话说如果 k 有值的话，那么必然是因为有 j > k，导致的有值。
     * 也就是 132 结构中，我们找到了 32，剩下的 i （也就是 132 结构中的 1）则是通过遍历过程中与 k 的比较来找到。
     * 这样做的复杂度是 O(n) 的，比树状数组还要快。
     * </pre>
     */
    public static boolean find132pattern(int[] nums) {
        // 递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        int numk = Integer.MIN_VALUE;
        // 从右向左
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (num < numk) {
                return true;
            }
            while (!indexMonoStack.isEmpty() && num > nums[indexMonoStack.getFirst()]) {
                numk = Math.max(numk, nums[indexMonoStack.removeFirst()]);
            }
            indexMonoStack.addFirst(i);
        }
        return false;
    }
}
