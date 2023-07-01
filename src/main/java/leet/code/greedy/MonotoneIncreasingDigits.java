package leet.code.greedy;

/**
 * 738. 单调递增的数字
 * <p></p>
 * https://leetcode-cn.com/problems/monotone-increasing-digits/
 * <pre>
 * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
 *
 * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
 * </pre>
 *
 * @author guangyi
 */
public class MonotoneIncreasingDigits {
    /**
     * 方法一：贪心
     * <p></p>
     * 可以从高到低按位构造这个小于等于 n 的最大单调递增的数字。
     */
    public static int monotoneIncreasingDigits(int n) {
        if (n < 10) {
            return n;
        }
        char[] charNumbers = Integer.toString(n).toCharArray();
        int i = 1;
        int len = charNumbers.length;
        while (i < len && charNumbers[i - 1] <= charNumbers[i]) {
            // 数字是单调递增的
            i += 1;
        }
        if (i < len) {
            // 十进位-1
            while (i > 0 && charNumbers[i - 1] > charNumbers[i]) {
                charNumbers[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < len; i++) {
                charNumbers[i] = '9';
            }
        }
        return Integer.parseInt(new String(charNumbers));
    }
}
