package leet.code.stack.monotonous;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 121. 买卖股票的最佳时机
 * <p></p>
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * <p></p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。
 * 如果你不能获取任何利润，返回 0 。
 * <p></p>
 * 特性：
 * 1. 全局最优解
 *
 * @author guangyi
 */
public class MaxProfit {
    /**
     * 利用哨兵👨‍✈️，维护一个单调栈📈(图解，直观掌握)
     * <p></p>
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/c-li-yong-shao-bing-wei-hu-yi-ge-dan-diao-zhan-tu-/
     * <pre>
     * 解题思路
     * 这道题的本质是要求某个数与其右边最大的数的差值，这符合单调栈的应用场景。
     * 当你需要高效率查询某个位置左右两侧比他大（或小）的数的位置的时候，于是我就用单调栈解决了。
     *
     * 我们来看看如何用单调栈解决这个问题。
     * 这里我们维护一个单调增的栈 📈，要赚钱嘛，肯定单调增。
     *
     * 首先讲下维护单调栈的 具体思路：
     * * 在 pricesprices 数组的末尾加上一个 哨兵‍(也就是一个很小的元素，这里设为 -1))，就相当于作为股市收盘的标记(后面就清楚他的作用了)。
     * * 假如栈空或者入栈元素大于栈顶元素，直接入栈
     * * 假如入栈元素小于栈顶元素则循环弹栈，直到入栈元素大于栈顶元素或者栈空
     * * 在每次弹出的时候，我们拿他与买入的值(也就是栈底)做差，维护一个最大值。
     *
     * 第四步，入栈元素为 3，他比栈顶元素 5 大，我们直接弹栈，并拿他减去栈底元素1。
     * 这就是最重要的，模拟了买卖，因为 5 遇上了比它小的 3，因此即使后面遇到更大的元素 C，但是存在 C−3>C−5。
     * 因此它已经没用了，计算之后弹出它。
     *
     * 第七步，现在 哨兵 ‍的作用就非常清楚啦，假如没有哨兵，单调栈中还有残留的元素没有进行判断。
     * (比如 prices 数组单调增的情况下，不加哨兵会出现 max=0 的情况)
     * 因此，哨兵‍的作用就是确保单调栈中的每个元素都被进行判定。
     * </pre>
     *
     * @param prices 价格整数数组
     * @return 返回可以从这笔交易中获取的最大利润
     */
    public static int maxProfit(int[] prices) {
        // 最大利润
        int maxProfit = 0;
        // 单调栈
        Deque<Integer> monotonicStack = new LinkedList<>();
        int len = prices.length;
        int price;
        for (int i = 0; i <= len; i++) {
            if (i == len) {
                // 在 prices 数组的末尾加上一个 哨兵，保证所有的元素出栈
                price = -1;
            } else {
                price = prices[i];
            }
            while (!monotonicStack.isEmpty() && monotonicStack.getLast() > price) {
                // 维护单调栈
                maxProfit = Math.max(maxProfit, monotonicStack.getLast() - monotonicStack.getFirst());
                monotonicStack.removeLast();
            }
            // 入栈元素比栈顶元素大，直接入栈
            monotonicStack.addLast(price);
        }
        return maxProfit;
    }
}
