package leet.code.twenty;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <a href="有效的括号">https://leetcode-cn.com/problems/valid-parentheses/</a>
 *
 * 压栈出栈，栈的特性。
 *
 * @author EdwardLee03
 * @since 2020-03-26
 */
public class Solution {
    /**
     * 括号闭合关系对映射
     */
    private static final Map<Character, Character> CLOSURE_MAP;
    static {
        CLOSURE_MAP = new HashMap<>(8);
        CLOSURE_MAP.put(')', '(');
        CLOSURE_MAP.put('}', '{');
        CLOSURE_MAP.put(']', '[');
    }

    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            // 注意空字符串可被认为是有效字符串
            return true;
        }
        if ((s.length() & 1) != 0) {
            // 剪枝：奇数个括号
            return false;
        }
        // 模拟栈的行为
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            // 解法2
            Character v = CLOSURE_MAP.get(c);
            if (v == null) {
                // 左括号
                deque.offerFirst(c);
            } else {
                // 右括号，需要判断是否闭合
                if (v.equals(deque.peekFirst())) {
                    // 相同类型的左右括号闭合
                    deque.pollFirst();
                } else {
                    // 左括号必须以正确的顺序闭合
                    // 剪枝：右括号与左括号的类型不相同，未闭合，直接结束
                    return false;
                }
            }

            // 解法1
//            if (deque.isEmpty() || !deque.peekFirst().equals(CLOSURE_MAP.get(c))) {
//                deque.offerFirst(c);
//            } else {
//                // 括号闭合
//                deque.pollFirst();
//            }
        }
        return deque.isEmpty();
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
