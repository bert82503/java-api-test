package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 122. 买卖股票的最佳时机 II
 * <p></p>
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * <pre>
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。
 * 你在任何时候 最多 只能持有 一股 股票。
 * 你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润 。
 *
 * 提示：
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 * </pre>
 *
 * @author guangyi
 */
public class MaxProfit2 {

    public static int maxProfit(int[] prices) {
        int length = prices.length;
        // 保存每次交易利润的结果
        int profitSum = 0;
        // 递增栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int price = prices[i];
            while (!indexMonoStack.isEmpty() && price <= prices[indexMonoStack.getFirst()]) {
                // 交易利润计算
                profitSum += (prices[indexMonoStack.getFirst()] - prices[indexMonoStack.getLast()]);
                // 出栈
                while (!indexMonoStack.isEmpty()) {
                    indexMonoStack.removeFirst();
                }
            }
            // 入栈
            indexMonoStack.addFirst(i);
        }
        if (!indexMonoStack.isEmpty()) {
            // 交易利润计算
            profitSum += (prices[indexMonoStack.getFirst()] - prices[indexMonoStack.getLast()]);
        }
        return profitSum;
    }
}
