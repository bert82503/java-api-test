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

    public static int reverse(int x) {
        int flag = 1;
        if (x < 0) {
            flag = -1;
            x = -x;
        }
        String numStr = String.valueOf(x);
        int right = numStr.length() - 1;
        while (right >= 0 && numStr.charAt(right) == '0') {
            // 忽略前面的N个0
            right--;
        }
        if (right < 0) {
            return 0;
        }
        long num = numStr.charAt(right--) - '0';
        while (right >= 0) {
            char ch = numStr.charAt(right--);
            num = num * 10 + (ch - '0');
        }
        num = flag * num;
        if (num < Integer.MIN_VALUE || num > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) num;
    }
}
