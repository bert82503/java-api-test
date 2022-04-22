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
     * 1.构建辅助栈 stack， 遍历字符串 s 中每个字符 c；
     * * 当 c 为数字时，将数字字符转化为数字 multi，用于后续倍数计算；
     * * 当 c 为字母时，在 res 尾部添加 c；
     * * 当 c 为 [ 时，将当前 multi 和 res 入栈，并分别置空置 0：
     *   * 记录此 [ 前的临时结果 res 至栈，用于发现对应 ] 后的拼接操作；
     *   * 记录此 [ 前的倍数 multi 至栈，用于发现对应 ] 后，获取 multi × [...] 字符串。
     *   * 进入到新 [ 后，res 和 multi 重新记录。
     * * 当 c 为 ] 时，stack 出栈，拼接字符串 res = last_res + cur_multi * res：
     *   * last_res是上个 [ 到当前 [ 的字符串，例如 "3[a2[c]]" 中的 a；
     *   * cur_multi是当前 [ 到 ] 内字符串的重复倍数，例如 "3[a2[c]]" 中的 2；
     * 2. 返回字符串 res。
     * <p></p>
     * 复杂度分析：
     * * 时间复杂度 O(N)，一次遍历 s；
     * * 空间复杂度 O(N)，辅助栈在极端情况下需要线性空间。
     *
     * @param str 编码的字符串
     * @return 解码后的字符串
     */
    public static String decodeString_Stack(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int multi = 0;
        StringBuilder result = new StringBuilder(16);
        // 堆栈
        Deque<Integer> multiStack = new LinkedList<>();
        Deque<String> resultStack = new LinkedList<>();
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                // 数字
                multi = multi * 10 + (ch - '0');
            } else if (Character.isLowerCase(ch) || Character.isUpperCase(ch)) {
                // 字母
                result.append(ch);
            } else if (ch == LEFT_PARENTHESIS) {
                // 左括号
                // 入栈，递归的开启条件
                multiStack.addLast(multi);
                resultStack.addLast(result.toString());
                multi = 0;
                result = new StringBuilder(16);
            } else if (ch == RIGHT_PARENTHESIS) {
                // 右括号
                // 出栈，递归的终止条件
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

    /**
     * 解法二：递归法
     *
     * 总体思路与辅助栈法一致，不同点在于将 [ 和 ] 分别作为递归的开启与终止条件：
     * * 当 s[i] == ']' 时，返回当前括号内记录的 res 字符串与 ] 的索引 i （更新上层递归指针位置）；
     * * 当 s[i] == '[' 时，开启新一层递归，记录此 [...] 内字符串 tmp 和递归后的最新索引 i，并执行 res + multi * tmp 拼接字符串。
     * * 遍历完毕后返回 res。
     * <p></p>
     * 复杂度分析：
     * * 时间复杂度 O(N)，递归会更新索引，因此实际上还是一次遍历 s；
     * * 空间复杂度 O(N)，极端情况下递归深度将会达到线性级别。
     *
     * @param str 编码的字符串
     * @return 解码后的字符串
     */
    public static String decodeString(String str) {
        return depthFirstSearch(str, 0)[0];
    }

    /**
     * 基于递归的深度优先搜索。
     *
     * @param str 编码的字符串
     * @param i   当前处理的下标
     * @return 解码后的字符串
     */
    private static String[] depthFirstSearch(String str, int i) {
        StringBuilder res = new StringBuilder(16);
        int multi = 0;
        while (i < str.length()) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                // 数字
                multi = multi * 10 + (ch - '0');
            } else if (ch == LEFT_PARENTHESIS) {
                // 左括号
                // 递归的开启条件，入栈
                String[] tmp = depthFirstSearch(str, i + 1);
                // 拼接字符串
                i = Integer.parseInt(tmp[0]);
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (Character.isLowerCase(ch) || Character.isUpperCase(ch)) {
                // 字母
                res.append(ch);
            } else if (ch == RIGHT_PARENTHESIS) {
                // 右括号
                // 递归的终止条件，出栈
                return new String[]{String.valueOf(i), res.toString()};
            }
            i++;
        }
        return new String[]{res.toString()};
    }
}
