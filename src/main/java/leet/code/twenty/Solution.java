package leet.code.twenty;

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
    private static final char[] CLOSURE_MAP;
    static {
        CLOSURE_MAP = new char[256];
        // 左括号入栈
        CLOSURE_MAP['('] = ')';
        CLOSURE_MAP['{'] = '}';
        CLOSURE_MAP['['] = ']';
        // 右括号出栈/闭合
//        CLOSURE_MAP[')'] = '(';
//        CLOSURE_MAP['}'] = '{';
//        CLOSURE_MAP[']'] = '[';
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
            // 注意空字符串可被认为是有效字符串
            return true;
        }
        int strLen = s.length();
        if ((strLen & 1) != 0) {
            // 剪枝：奇数个括号
            return false;
        }
        // 模拟栈的行为
        char[] deque = new char[strLen];
        int index = -1;
        for (int i = 0; i < strLen; i++) {
            // 解法2
            char c = s.charAt(i);
            switch (c) {
                // 左括号入栈
                case '(':
                case '{':
                case '[':
                    char right = CLOSURE_MAP[c];
                    deque[++index] = right;
                    break;
                // 右括号出栈/闭合
                case ')':
                case '}':
                case ']':
                    // 右括号，需要判断是否闭合
                    if (index < 0 || c != deque[index]) {
                        // 左括号必须以正确的顺序闭合
                        // 剪枝：右括号与左括号的类型不相同，未闭合，直接结束
                        return false;
                    } else {
                        index--;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + c);
            }
        }
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
