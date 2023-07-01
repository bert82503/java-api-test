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

    /**
     * 回文数
     * https://leetcode-cn.com/problems/palindrome-number/solution/hui-wen-shu-by-leetcode-solution/
     * <pre>
     * 方法1：数字转换为字符串
     * 正序字符串，反序字符串
     *
     * 方法1：数字转换为字符串 - 进阶
     * 字符串对半对称比较
     *
     * 方法2：将数字本身反转
     * 整数溢出问题
     *
     * 方法2：进阶 - 反转一半数字
     * 奇数位，偶数位
     *
     * 方法一：反转一半数字
     * 思路
     * 映入脑海的第一个想法是将数字转换为字符串，并检查字符串是否为回文。
     * 但是，这需要额外的非常量空间来创建问题描述中所不允许的字符串。
     *
     * 第二个想法是将数字本身反转，然后将反转后的数字与原始数字进行比较，
     * 如果它们是相同的，那么这个数字就是回文。
     * 但是，如果反转后的数字大于 \text{int.MAX}int.MAX，我们将遇到整数溢出问题。
     *
     * 按照第二个想法，为了避免数字反转可能导致的溢出问题，为什么不考虑只反转 int 数字的一半？
     * 毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。
     * </pre>
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        // 循环建立反转一半的数字
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        // 针对奇数位和偶数位的数字，分别判断是否为回文
        // 奇数位：x == revertedNumber / 10
        return x == revertedNumber || x == revertedNumber / 10;
    }

    private static final int MAX_RESULT = Integer.MAX_VALUE / 10;

    public static boolean isPalindrome_One(int x) {
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
