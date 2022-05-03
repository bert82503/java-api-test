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

    /**
     * <pre>
     * 数学
     * </pre>
     */
    public static int reverse(int x) {
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
