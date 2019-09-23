package leet.code.one;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 76. 最小覆盖子串。
 *
 * 题目描述
 * <pre>
 * 给你一个字符串 S、一个字符串 T，
 * 请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 说明：
 * 如果 S 中不存在这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * </pre>
 *
 * 1.认识题目
 * <pre>
 * 1.条件：给你一个字符串 S、一个字符串 T (字符可能重复)
 * 2.目标：请在字符串 S 里面找出：包含 T 所有字母的最小子串 (存在N个满足的字符串，但只取最短子串)
 * 3.结果：最小子串
 *
 * 4.说明1：如果 S 中不存在这样的子串，则返回空字符串 ""
 * 5.说明2：如果 S 中存在这样的子串，我们保证它是唯一的答案
 * </pre>
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/
 *
 * @since 2019-09-23
 */
public class MinimumWindowSubstring {

    /**
     * 滑动窗口算法。
     * <pre>
     * 对于window/needs计数器，使用包装类型的Map对象，存在基本类型与包装类型的自动包装与拆包装。
     * </pre>
     * 执行用时：21 ms，内存消耗：37.3 MB
     */
    private static String minWindow1(String s, String t) {
        if (t == null || t.isEmpty() || s == null || s.isEmpty() ||
                t.length() > s.length()) {
            return "";
        }
        // 记录最短子串的开始位置和长度
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        // 相当于两个计数器
        Map<Character, Integer> window = new HashMap<>(16);
        Map<Character, Integer> needs = new HashMap<>(16);

        int tlen = t.length();
        for (int i = 0; i < tlen; i++) {
            char ch = t.charAt(i);
            needs.put(ch, needs.getOrDefault(ch, 0) + 1);
        }
        int needsSize = needs.size();

        // 记录window中已经有多少个字符符合要求了
        int match = 0;

        int slen = s.length();
        while (right < slen) {
            char rc = s.charAt(right);
            Integer nr = needs.get(rc);
            if (nr != null) {
                // 加入window
                int wc = window.getOrDefault(rc, 0) + 1;
                window.put(rc, wc);
                if (wc == nr) {
                    // 字符rc的出现次数符合要求了
                    match++;
                }
            }
            right++;

            while (match == needsSize) {
                // window中的字符串已符合needs的要求了
                if (right - left < minLen) {
                    // 找到一个可行解，更新结果，更新最小子串的位置和长度
                    start = left;
                    minLen = right - left;
                }
                char lc = s.charAt(left);
                Integer nl = needs.get(lc);
                if (nl != null) {
                    // 移出window
                    int wc = window.get(lc) - 1;
                    window.put(lc, wc);
                    if (wc < nl) {
                        // 字符lc出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    /**
     * 滑动窗口算法。
     * <pre>
     * 优化：对于window/needs计数器，使用基本类型的数组替换Map对象，避免不必要的基本类型与包装类型的自动包装与拆包装。
     * ({@code int[]} => {@code Map})
     *
     * C语言代码风格
     * </pre>
     * 执行用时：12 ms，内存消耗：38.1 MB
     * <p>
     * 这个版本的执行用时比第一版减少了9ms，耗时降低了40%。
     * 在大数据量场景下，包装类型的自动装箱与拆箱还是挺耗时的。
     */
    private static String minWindow2(String s, String t) {
        if (t == null || t.isEmpty() || s == null || s.isEmpty() ||
                t.length() > s.length()) {
            return "";
        }
        // 记录最短子串的开始位置和长度
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        // 相当于两个计数器
        int[] window = new int[65536];
        int[] needs = new int[65536];
        int needsSize = 0;

        int tlen = t.length();
        for (int i = 0; i < tlen; i++) {
            char ch = t.charAt(i);
            needs[ch]++;
            if (needs[ch] == 1) {
                needsSize++;
            }
        }

        // 记录window中已经有多少字符符合要求了
        int match = 0;

        int slen = s.length();
        while (right < slen) {
            char rc = s.charAt(right);
            int nr = needs[rc];
            if (nr > 0) {
                // 加入window
                int wc = ++window[rc];
                if (wc == nr) {
                    // 字符rc的出现次数符合要求了
                    match++;
                }
            }
            right++;

            while (match == needsSize) {
                // window中的字符串已符合needs的要求了
                if (right - left < minLen) {
                    // 找到一个可行解，更新结果，更新最小子串的位置和长度
                    start = left;
                    minLen = right - left;
                }
                char lc = s.charAt(left);
                int nl = needs[lc];
                if (nl > 0) {
                    // 移出window
                    int wc = --window[lc];
                    if (wc < nl) {
                        // 字符lc出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    /**
     * 滑动窗口算法。
     * <pre>
     * 优化：在解法2的基础上，减少{@code int}临时变量的创建。
     *
     * 代码可读性差
     * </pre>
     * 执行用时：10 ms，内存消耗：37.4 MB
     *
     * @see #minWindow2(String, String)
     */
    private static String minWindow(String s, String t) {
        if (t == null || t.isEmpty() || s == null || s.isEmpty() ||
                t.length() > s.length()) {
            return "";
        }
        // 记录最短子串的开始位置和长度
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        // 相当于两个计数器
        int[] window = new int[65536];
        int[] needs = new int[65536];
        int needsSize = 0;

        int tlen = t.length();
        for (int i = 0; i < tlen; i++) {
            if ((needs[t.charAt(i)]++) == 0) {
                needsSize++;
            }
        }

        // 记录window中已经有多少字符符合要求了
        int match = 0;
        int slen = s.length();
        while (right < slen) {
            char rc = s.charAt(right);
            if (needs[rc] > 0) {
                // 加入window
                if (++window[rc] == needs[rc]) {
                    // 字符rc的出现次数符合要求了
                    match++;
                }
            }
            right++;

            while (match == needsSize) {
                // window中的字符串已符合needs的要求了
                if (right - left < minLen) {
                    // 找到一个可行解，更新结果，更新最小子串的位置和长度
                    start = left;
                    minLen = right - left;
                }
                char lc = s.charAt(left);
                if (needs[lc] > 0) {
                    // 移出window
                    if (--window[lc] < needs[lc]) {
                        // 字符lc出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    // 测试

    @Test(dataProvider = "minWindowTestData")
    public void minWindowTest(String s, String t, String expected) {
        assertThat(minWindow(s, t)).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "minWindowTestData")
    public static Object[][] minWindowTestData() {
        return new Object[][]{
                {"ADOBECODEBANC", "ABC", "BANC"},
                {"ADOCEBCODEBAC", "ABC", "BAC"},
                // 相同长度
                {"ABC", "ABC", "ABC"},
                {"BAC", "ABC", "BAC"},
                // 无效值
                {"A", "", ""},
                {"", "A", ""},
                {"A", "AA", ""},
        };
    }

}
