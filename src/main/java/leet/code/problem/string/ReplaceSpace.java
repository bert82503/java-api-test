package leet.code.problem.string;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/">
 *     面试题05. 替换空格</a>
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 限制：0 <= s 的长度 <= 10000
 *
 * <pre>
 * 一、认识题目：
 * 最坏场景：字符串 s 全是空格，长度翻 3 倍。
 * </pre>
 *
 * @author guangyi
 * @since 2020-06-13
 */
public class ReplaceSpace {

    private static final char WHITESPACE = ' ';

    private static final String REPLACEMENT = "%20";

    /**
     * 把字符串 s 中的每个空格替换成"%20"。
     *
     * <pre>
     * 执行用时 : 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗 : 37.6 MB, 在所有 Java 提交中击败了 100.00% 的用户
     * </pre>
     */
    public static String replaceSpace(String s) {
        if (s == null || s.length() <= 0) {
            return s;
        }
        int length = s.length();
        StringBuilder sb = new StringBuilder(length * 3);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == WHITESPACE) {
                sb.append(REPLACEMENT);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Test cases.
     */
    public static void main(String[] args) {
        assertThat(replaceSpace("We are happy."))
                .isEqualTo("We%20are%20happy.");
        assertThat(replaceSpace(""))
                .isEqualTo("");
        assertThat(replaceSpace(" "))
                .isEqualTo("%20");
        assertThat(replaceSpace("   "))
                .isEqualTo("%20%20%20");
        assertThat(replaceSpace("xingle "))
                .isEqualTo("xingle%20");
        assertThat(replaceSpace(" dannong "))
                .isEqualTo("%20dannong%20");
        assertThat(replaceSpace(" gangyi"))
                .isEqualTo("%20gangyi");
        assertThat(replaceSpace("Hello, Java!"))
                .isEqualTo("Hello,%20Java!");
    }
}
