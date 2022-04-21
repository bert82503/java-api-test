package leet.code.string;

import java.util.Stack;

/**
 * 394. 字符串解码
 * <p></p>
 * https://leetcode-cn.com/problems/decode-string/
 * <p></p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 提示：
 * * 1 <= s.length <= 30
 * * s 由小写英文字母、数字和方括号 '[]' 组成
 * * s 保证是一个 有效 的输入
 * * s 中所有整数的取值范围为 [1, 300]
 *
 * @author guangyi
 */
public class DecodeString {
    /**
     * 右括号
     */
    private static final char RIGHT_PARENTHESIS = ']';
    /**
     * 左括号
     */
    private static final char LEFT_PARENTHESIS = '[';

    public static String decodeString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        // 堆栈
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            // 1.右括号
            if (ch == RIGHT_PARENTHESIS) {
                // 出栈
                ch = stack.pop();
                // 2.大小写英文字母
                StringBuilder letterStringBuilder = new StringBuilder(16);
                while (Character.isLowerCase(ch) || Character.isUpperCase(ch)) {
                    letterStringBuilder.append(ch);
                    // 出栈
                    ch = stack.pop();
                }
                // 逆向反转
                letterStringBuilder = letterStringBuilder.reverse();
                // 3.左括号
                if (ch == LEFT_PARENTHESIS) {
                    // 出栈
                    ch = stack.pop();
                }
                // 4.数字
                StringBuilder numStringBuilder = new StringBuilder(16);
                while (Character.isDigit(ch)) {
                    numStringBuilder.append(ch);
                    if (stack.isEmpty()) {
                        break;
                    }
                    // 出栈
                    ch = stack.pop();
                }
                if (!stack.isEmpty()) {
                    // 入栈
                    stack.push(ch);
                }
                // 逆向反转
                numStringBuilder = numStringBuilder.reverse();
                int num = Integer.parseInt(numStringBuilder.toString());
                // 5.解码
                for (int i = 0; i < num; i++) {
                    int length = letterStringBuilder.length();
                    for (int j = 0; j < length; j++) {
                        ch = letterStringBuilder.charAt(j);
                        // 入栈
                        stack.push(ch);
                    }
                }
            } else {
                // 入栈
                stack.push(ch);
            }
        }
        // 出栈的结果
        StringBuilder result = new StringBuilder(16);
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        // 逆向反转
        return result.reverse().toString();
    }
}
