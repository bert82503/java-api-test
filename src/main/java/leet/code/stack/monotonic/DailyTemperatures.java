package leet.code.stack.monotonic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度
 * <p></p>
 * https://leetcode-cn.com/problems/daily-temperatures/
 * <pre>
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指在第 i 天之后，才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * </pre>
 * 特性：
 * 1. 单调递减栈
 *
 * @author guangyi
 */
public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {
        return dailyTemperatures_FromRightToLeft(temperatures);
    }

    /**
     * 动画演示 单调栈 739.每日温度
     * https://leetcode-cn.com/problems/daily-temperatures/solution/dong-hua-yan-shi-dan-diao-zhan-739mei-ri-iita/
     * <pre>
     * 题目分析
     * 需要等待的天数就是那个更高温度所在的索引值与当前温度所在的索引值的差值；如果其后没有比其更高的温度，则等待天数为0。
     * 这个问题的关键就是对于某天温度来说，如何找出其后面的更高温度？
     * 又，最终结果是索引的差值。因此，过程中记录的应该是索引值。
     *
     * 方法一：单调栈 正序遍历
     * 代码实现
     * 思路分析
     * 动画演示
     *
     * 方法二：单调栈 逆序遍历
     * </pre>
     */
    public static int[] dailyTemperatures_FromRightToLeft(int[] temperatures) {
        // 存放答案的数组
        int[] result = new int[temperatures.length];
        // 存放元素索引的单调递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        // 倒着往栈里放
        // 从右向左遍历，逆序遍历
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int num = temperatures[i];
            // 区别：大于等于栈顶元素，出栈
            while (!indexMonoStack.isEmpty() && num >= temperatures[indexMonoStack.getFirst()]) {
                // 区别：忽略栈顶的索引下标
                indexMonoStack.removeFirst();
            }
            // 区别：计算结果
            result[i] = indexMonoStack.isEmpty() ? 0 : indexMonoStack.getFirst() - i;
            // 入栈
            indexMonoStack.addFirst(i);
        }
        return result;
    }

    /**
     * 每日温度
     * https://leetcode-cn.com/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/
     * <pre>
     * 方法二：单调栈
     * 可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
     * 如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
     *
     * 正向遍历温度列表。
     *
     * 为什么可以在弹栈的时候更新 ans[prevIndex] 呢？
     * 因为在这种情况下，即将进栈的 i 对应的 temperatures[i] 一定是 temperatures[prevIndex] 右边第一个比它大的元素。
     *
     * 由于单调栈满足从栈底到栈顶元素对应的温度递减，因此每次有元素进栈时，
     * 会将温度更低的元素全部移除，并更新出栈元素对应的等待天数，这样可以确保等待天数一定是最小的。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是温度列表的长度。正向遍历温度列表一遍，对于温度列表中的每个下标，最多有一次进栈和出栈的操作。
     * 空间复杂度：O(n)，其中 n 是温度列表的长度。需要维护一个单调栈存储温度列表中的下标。
     * </pre>
     *
     * LeetCode 图解 | 739.每日温度
     * https://leetcode-cn.com/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/
     */
    public static int[] dailyTemperatures_FromLeftToRight(int[] temperatures) {
        // 存放答案的数组
        int[] result = new int[temperatures.length];
        // 区别：初始化数组
        Arrays.fill(result, 0);
        // 存放元素索引的单调递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        // 顺着往栈里放
        // 从左往右遍历，正序遍历
        for (int i = 0; i < temperatures.length; i++) {
            int num = temperatures[i];
            // 区别：大于栈顶元素，出栈
            while (!indexMonoStack.isEmpty() && num > temperatures[indexMonoStack.getFirst()]) {
                // 区别：栈顶的索引下标
                int prevIndex = indexMonoStack.removeFirst();
                // 区别：计算结果
                result[prevIndex] = i - prevIndex;
            }
            // 入栈
            indexMonoStack.addFirst(i);
        }
        return result;
    }
}
