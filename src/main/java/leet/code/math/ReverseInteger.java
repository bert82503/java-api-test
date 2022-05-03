package leet.code.math;

/**
 * 7. 整数反转
 * <p></p>
 * https://leetcode-cn.com/problems/reverse-integer/
 * <pre>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 * </pre>
 *
 * @author guangyi
 */
public class ReverseInteger {

    private static final int MAX_RESULT = Integer.MAX_VALUE / 10;
    private static final int MIN_RESULT = Integer.MIN_VALUE / 10;

    /**
     * 整数反转
     * https://leetcode-cn.com/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode-solution-bccn/
     * <pre>
     * 方法1：按位转换
     * - 明确 % 运算类型
     *   求模
     *   求余
     * - 弹出 和 推入 数字
     *   弹出：num = x % 10; x /= 10;
     *   推入：result = result * 10 + num;
     * - 模式识别：整数运算注意溢出
     *   转换为 INT_MAX / INT_MIN 的逆运算
     *
     * 方法一：数学
     * 思路
     * 按位转换
     * 记 rev 为翻转后的数字，为完成翻转，我们可以重复「弹出」x 的末尾数字，将其「推入」rev 的末尾，直至 x 为 0。
     * 要在没有辅助栈或数组的帮助下「弹出」和「推入」数字，我们可以使用如下数学方法：
     *     // 弹出 x 的末尾数字 digit
     *     digit = x % 10
     *     x /= 10
     *
     *     // 将数字 digit 推入 rev 末尾
     *     rev = rev * 10 + digit
     *
     * 题目需要判断反转后的数字是否超过 3232 位有符号整数的范围 [−2^31, 2^31−1]。
     * </pre>
     *
     * 图解 7. 整数反转
     * https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/
     * <pre>
     * 解题思路：
     * 首先我们想一下，怎么去反转一个整数？
     * 用栈？或者把整数变成字符串，再去反转这个字符串？
     * 这两种方式是可以，但并不好。实际上我们只要能拿到这个整数的 末尾数字 就可以了。
     * 以12345为例，先拿到5，再拿到4，之后是3，2，1，我们按这样的顺序就可以反向拼接处一个数字了，也就能达到 反转 的效果。
     * 怎么拿末尾数字呢？好办，用取模运算就可以了。
     *
     * 这么看起来，一个循环就搞定了，循环的判断条件是x>0
     * 但这样不对，因为忽略了 负数。
     * 循环的判断条件应该是while(x!=0)，无论正数还是负数，按照上面不断的/10这样的操作，最后都会变成0，所以判断终止条件就是!=0。
     *
     * 有了取模和除法操作，对于像12300这样的数字，也可以完美的解决掉了。
     * 看起来这道题就这么解决了，但请注意，题目上还有这么一句。
     * > 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
     * 也就是说我们不能用long存储最终结果，而且有些数字可能是合法范围内的数字，但是反转过来就超过范围了。
     * </pre>
     */
    public static int reverse(int x) {
        int result = 0;
        int digit;
        while (x != 0) {
            // 正数溢出/负数溢出
            if (result < MIN_RESULT || result > MAX_RESULT) {
                return 0;
            }
            digit = x % 10;
            x /= 10;
            result = result * 10 + digit;
        }
        return result;
    }

    /**
     * 方法一：数学
     * 思路
     * 按位转换
     */
    public static int reverse_Math(int x) {
        // 长整数
        long num = x;
        // 符号位
        int sign = 1;
        if (num < 0L) {
            sign = -1;
            // 转换为正长整数
            num = -num;
        }

        // 直接反转整数
        long result = 0L;
        do {
            result = result * 10 + (num % 10);
            if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
                return 0;
            }
            num /= 10;
        } while (num > 0L);

        result = sign * result;
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) result;
    }

    /**
     * 方法二：双指针
     */
    public static int reverse_DoublePointer(int x) {
        // 长整数
        long num = x;
        // 符号位
        int sign = 1;
        if (num < 0L) {
            sign = -1;
            // 转换为正长整数
            num = -num;
        }

        char[] charArray = String.valueOf(num).toCharArray();
        char temp;
        // 双指针，从两端向中央同时收缩，并互相字符
        int left = 0;
        int right = charArray.length - 1;
        while (left < right) {
            // 高低字符互换
            temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        num = Long.parseLong(String.valueOf(charArray));
        num = sign * num;
        if (num < Integer.MIN_VALUE || num > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) num;
    }
}
