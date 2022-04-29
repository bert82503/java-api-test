package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1475. 商品折扣后的最终价格
 * <p></p>
 * https://leetcode-cn.com/problems/final-prices-with-a-special-discount-in-a-shop/
 * <pre>
 * 给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
 *
 * 商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，
 * 其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，
 * 如果没有满足条件的 j ，你将没有任何折扣。
 *
 * 请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
 *
 * 提示：
 * 1 <= prices.length <= 500
 * 1 <= prices[i] <= 10^3
 * </pre>
 *
 * @author guangyi
 */
public class FinalPrices {

    public static int[] finalPrices(int[] prices) {
        int len = prices.length;
        if (len == 1) {
            return prices;
        }
        // 折扣的价格
        int[] result = new int[len];
        // 递增栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        for (int i = len - 1; i >= 0; i--) {
            int price = prices[i];
            while (!indexMonoStack.isEmpty() && price < prices[indexMonoStack.getFirst()]) {
                // 出栈
                indexMonoStack.removeFirst();
            }
            // 结果计算
            result[i] = indexMonoStack.isEmpty() ? 0 : prices[indexMonoStack.getFirst()];
            // 出栈
            indexMonoStack.push(i);
        }
        // 支付的价格
        for (int i = 0; i < prices.length; i++) {
            result[i] = prices[i] - result[i];
        }
        return result;
    }
}
