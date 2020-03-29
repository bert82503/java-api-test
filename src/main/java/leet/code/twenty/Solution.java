package leet.code.twenty;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="有效的括号">https://leetcode-cn.com/problems/valid-parentheses/</a>
 *
 * <pre>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *    1. 左括号必须用相同类型的右括号闭合。
 *    2. 左括号必须以正确的顺序闭合。
 *
 * 注意：空字符串可被认为是有效字符串。
 * </pre>
 *
 * 压栈出栈，栈的特性。
 *
 * @author EdwardLee03
 * @since 2020-03-26
 */
public class Solution {
    /**
     * 括号闭合对映射
     */
    private static final char[] CLOSURE_MAP;
    static {
        // 256包含字符值数组下标的范围
        CLOSURE_MAP = new char[256];
        // <左括号，右括号>
        CLOSURE_MAP['('] = ')';
        CLOSURE_MAP['{'] = '}';
        CLOSURE_MAP['['] = ']';
        // <右括号，左括号>
//        CLOSURE_MAP[')'] = '(';
//        CLOSURE_MAP['}'] = '{';
//        CLOSURE_MAP[']'] = '[';
    }

    /**
     * 解法1
     * <pre>
     * 执行用时 : 2 ms, 在所有 Java 提交中击败了 90.61% 的用户
     * 内存消耗 : 37.7 MB, 在所有 Java 提交中击败了 5.02% 的用户
     * </pre>
     */
    public boolean isValid_1(String s) {
        if (s == null || s.isEmpty()) {
            // 注意：空字符串可被认为是有效字符串
            return true;
        }
        int strLen = s.length();
        if ((strLen & 1) != 0) {
            // 剪枝：奇数个括号
            return false;
        }
        // 使用"双端队列"模拟栈（基本类型与包装类型存在自动解包装，损耗性能）
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < strLen; i++) {
            char c = s.charAt(i);
            switch (c) {
                // 左括号：入栈
                case '(':
                case '{':
                case '[':
                    // 对应的右括号值压栈
                    char right = CLOSURE_MAP[c];
                    deque.offerFirst(right);
                    break;
                // 右括号：出栈/闭合
                case ')':
                case '}':
                case ']':
                    // 判断括号对是否闭合
                    if (deque.isEmpty() || c != deque.peekFirst()) {
                        // 2. 左括号必须以正确的顺序闭合
                        // 剪枝：右括号与左括号的类型不相同，未闭合，直接结束
                        return false;
                    } else {
                        // 闭合出栈
                        deque.pollFirst();
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + c);
            }
        }
        // 栈是否为空，表示所有括号对都是闭合的
        return deque.isEmpty();
    }

    /**
     * 解法2
     * <pre>
     * 执行用时 : 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗 : 37.2 MB, 在所有 Java 提交中击败了 5.01% 的用户
     * </pre>
     */
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            // 注意：空字符串可被认为是有效字符串
            return true;
        }
        int strLen = s.length();
        if ((strLen & 1) != 0) {
            // 剪枝：奇数个括号
            return false;
        }
        // 优化：使用"数组+下标"模拟"栈"
        char[] deque = new char[strLen];
        int index = -1;
        for (int i = 0; i < strLen; i++) {
            char c = s.charAt(i);
            switch (c) {
                // 左括号：入栈
                case '(':
                case '{':
                case '[':
                    // 对应的右括号值压栈
                    char right = CLOSURE_MAP[c];
                    deque[++index] = right;
                    break;
                // 右括号：出栈/闭合
                case ')':
                case '}':
                case ']':
                    // 判断括号对是否闭合
                    if (index < 0 || c != deque[index]) {
                        // 2. 左括号必须以正确的顺序闭合
                        // 剪枝：右括号与左括号的类型不相同，未闭合，直接结束
                        return false;
                    } else {
                        // 闭合出栈
                        index--;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + c);
            }
        }
        // 栈是否为空，表示所有括号对都是闭合的
        return index == -1;
    }

    /**
     * 测试用例
     */
    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()"));
        // true

        solution = new Solution();
        System.out.println(solution.isValid("()[]{}"));
        // true

        solution = new Solution();
        System.out.println(solution.isValid("{[]}"));
        // true

        solution = new Solution();
        System.out.println(solution.isValid(""));
        // true
        System.out.println(solution.isValid(null));
        // true


        // 左括号必须用相同类型的右括号闭合
        solution = new Solution();
        System.out.println(solution.isValid("(]"));
        // false

        // 左括号必须以正确的顺序闭合
        solution = new Solution();
        System.out.println(solution.isValid("([)]"));
        // false

        // 括号是奇数个
        solution = new Solution();
        System.out.println(solution.isValid("("));
        // false

        solution = new Solution();
        System.out.println(solution.isValid("(((((()["));
        // false
    }
}
