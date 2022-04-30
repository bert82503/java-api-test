package leet.code.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 20. 有效的括号
 * <p></p>
 * https://leetcode-cn.com/problems/valid-parentheses/
 * <pre>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *    1. 左括号必须用相同类型的右括号闭合。
 *    2. 左括号必须以正确的顺序闭合。
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * 注意：空字符串可被认为是有效字符串。
 * </pre>
 * 特性：
 * 1. 栈，入栈出栈操作
 *
 * @author edwardlee03
 */
public class ValidParentheses {

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
     * 方法一：栈
     */
    public static boolean isValid_1(String str) {
        if (str == null || str.isEmpty()) {
            // 注意：空字符串可被认为是有效字符串
            return true;
        }
        int length = str.length();
        if ((length & 1) != 0) {
            // 剪枝：奇数个括号
            return false;
        }
        // 使用"双端队列"模拟"栈"（基本类型与包装类型存在自动解包装）
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            switch (ch) {
                // 入栈，左括号
                case '(':
                case '{':
                case '[':
                    // 对应的右括号值入栈
                    char right = CLOSURE_MAP[ch];
                    deque.addFirst(right);
                    break;
                // 出栈/闭合，右括号
                case ')':
                case '}':
                case ']':
                    // 判断括号对是否闭合
                    if (deque.isEmpty() || ch != deque.getFirst()) {
                        // 2. 左括号必须以正确的顺序闭合
                        // 剪枝：右括号与左括号的类型不相同，未闭合，直接结束
                        return false;
                    } else {
                        // 闭合出栈
                        deque.removeFirst();
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ch);
            }
        }
        // 栈是否为空，表示所有括号对都是闭合的
        return deque.isEmpty();
    }

    /**
     * 方法一：栈
     */
    public static boolean isValid(String str) {
        if (str == null || str.isEmpty()) {
            // 注意：空字符串可被认为是有效字符串
            return true;
        }
        int length = str.length();
        if ((length & 1) != 0) {
            // 剪枝：奇数个括号
            return false;
        }
        // 优化：使用"数组+下标"模拟"栈"
        char[] deque = new char[length];
        int index = -1;
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            switch (ch) {
                // 左括号：入栈
                case '(':
                case '{':
                case '[':
                    // 对应的右括号值入栈
                    char right = CLOSURE_MAP[ch];
                    deque[++index] = right;
                    break;
                // 右括号：出栈/闭合
                case ')':
                case '}':
                case ']':
                    // 判断括号对是否闭合
                    if (index < 0 || ch != deque[index]) {
                        // 2. 左括号必须以正确的顺序闭合
                        // 剪枝：右括号与左括号的类型不相同，未闭合，直接结束
                        return false;
                    } else {
                        // 闭合出栈
                        index--;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ch);
            }
        }
        // 栈是否为空，表示所有括号对都是闭合的
        return index == -1;
    }
}
