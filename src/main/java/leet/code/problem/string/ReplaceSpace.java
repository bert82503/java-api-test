package leet.code.problem.string;

/**
 * 剑指 Offer 05. 替换空格
 * <p></p>
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * <pre>
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 限制：0 <= s 的长度 <= 10000
 * </pre>
 * <pre>
 * 一、认识题目：
 * 最坏场景：字符串 s 全是空格，长度翻 3 倍。
 * </pre>
 *
 * @author guangyi
 */
public class ReplaceSpace {

    private static final char WHITESPACE = ' ';

    private static final String REPLACEMENT = "%20";

    /**
     * 面试题05. 替换空格
     * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/solution/mian-shi-ti-05-ti-huan-kong-ge-by-leetcode-solutio/
     * <pre></pre>
     *
     * 面试题05. 替换空格 （字符串修改，清晰图解）
     * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/solution/mian-shi-ti-05-ti-huan-kong-ge-ji-jian-qing-xi-tu-/
     */
    public static String replaceSpace(String str) {
        if (str == null || str.length() <= 0) {
            return str;
        }
        int length = str.length();
        // 本题关键：预先分配富足的容量，以空间换时间
        StringBuilder sb = new StringBuilder(length * 3);
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            if (ch == WHITESPACE) {
                sb.append(REPLACEMENT);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
