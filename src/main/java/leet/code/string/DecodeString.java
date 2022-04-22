package leet.code.string;

import java.util.Deque;
import java.util.LinkedList;

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
 * <p></p>
 * 特性：
 * 1. 本题难点在于括号内嵌套括号，需要从内向外生成与拼接字符串，这与栈的先入后出特性对应。
 * 2. 编码的字符串中的关键词是连续的
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

    /**
     * 解法一：辅助栈法
     *
     * @param str 编码的字符串
     * @return 解码后的字符串
     */
    public static String decodeString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int multi = 0;
        StringBuilder result = new StringBuilder(16);
        // 堆栈
        Deque<Integer> multiStack = new LinkedList<>();
        Deque<String> resultStack = new LinkedList<>();
        // 1.构建辅助栈 stack， 遍历字符串 s 中每个字符 c
        for (char ch : str.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                // 数字
                // 当 c 为数字时，将数字字符转化为数字 multi，用于后续倍数计算
                multi = multi * 10 + (ch - '0');
            } else if (Character.isLowerCase(ch) || Character.isUpperCase(ch)) {
                // 字母
                // 当 c 为字母时，在 res 尾部添加 c
                result.append(ch);
            } else if (ch == LEFT_PARENTHESIS) {
                // 左括号
                // 入栈
                // 当 c 为 [ 时，将当前 multi 和 res 入栈，并分别置空置 0
                //   记录此 [ 前的临时结果 res 至栈，用于发现对应 ] 后的拼接操作
                //   记录此 [ 前的倍数 multi 至栈，用于发现对应 ] 后，获取 multi × [...] 字符串
                //   进入到新 [ 后，res 和 multi 重新记录
                multiStack.addLast(multi);
                resultStack.addLast(result.toString());
                multi = 0;
                result = new StringBuilder(16);
            } else if (ch == RIGHT_PARENTHESIS) {
                // 右括号
                // 出栈
                // 当 c 为 ] 时，stack 出栈，拼接字符串 res = last_res + cur_multi * res
                // last_res是上个 [ 到当前 [ 的字符串，例如 "3[a2[c]]" 中的 a
                // cur_multi是当前 [ 到 ] 内字符串的重复倍数，例如 "3[a2[c]]" 中的 2
                int curMulti = multiStack.removeLast();
                StringBuilder tmp = new StringBuilder(curMulti * result.length() + 1);
                for (int i = 0; i < curMulti; i++) {
                    tmp.append(result);
                }
                result = new StringBuilder(resultStack.removeLast() + tmp);
            }
        }
        return result.toString();
    }
}
