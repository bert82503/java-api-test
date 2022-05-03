package leet.code.math;

/**
 * 9. 回文数
 * <p></p>
 * https://leetcode-cn.com/problems/palindrome-number/
 * <pre>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 *
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 *
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 * </pre>
 * <pre>
 * 1.认识问题
 * 根据示例 1，其逆序等于它自身
 * 根据示例 2，负数一定不是回文数
 * </pre>
 *
 * @author guangyi
 */
public class IsPalindrome {

    private static final int MAX_RESULT = Integer.MAX_VALUE / 10;

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int result = 0;
        int digit;
        int num = x;
        while (num != 0) {
            digit = num % 10;
            // 正数溢出
            if (result > MAX_RESULT || (result == MAX_RESULT && digit > 7)) {
                return false;
            }
            result = result * 10 + digit;
            num /= 10;
        }
        return result == x;
    }
}
