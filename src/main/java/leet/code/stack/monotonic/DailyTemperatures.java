package leet.code.stack.monotonic;

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
        // 存放答案的数组
        int[] result = new int[temperatures.length];
        // 存放元素索引的单调递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        // 倒着往栈里放，从右往左
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int num = temperatures[i];
            while (!indexMonoStack.isEmpty() && num >= temperatures[indexMonoStack.getLast()]) {
                indexMonoStack.removeLast();
            }
            result[i] = indexMonoStack.isEmpty() ? 0 : indexMonoStack.getLast() - i;
            indexMonoStack.addLast(i);
        }
        return result;
    }
}
