package leet.code.one;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 438. 找到字符串中所有字母异位词。
 *
 * 题目描述
 * <pre>
 * 给定一个字符串 s 和一个非空字符串 p，
 * 找到 s 中所有是 p 的*字母异位词*的子串，
 * 返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 `20100`。
 *
 * 说明：
 * *字母异位词*指`字母相同`，但`排列不同`的字符串。
 * 不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 * 示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * </pre>
 *
 * 1.认识题目
 * <pre>
 * 1.条件：给定一个字符串 s 和一个非空字符串 p (字符串 s 可能为空或长度小于 p)
 * 2.目标：找到 s 中所有是 p 的字母异位词的子串 (p 的字母异位词，关键点)
 * 3.结果：返回这些子串的起始索引
 *
 * 4.字符串只包含小写英文字母 (['a', 'z']，共26个字符，'a'=97，'z'=122)
 * 5.字符串 s 和 p 的长度都不超过 20100 (字符重复)
 *
 * 6.说明1：*字母异位词*是指`字母相同`，但`排列不同`的字符串
 *   (类似数学上的集合概念，字母异位词相当于字符集合，且长度相等，意味着字符之和肯定相等)
 * 7.说明2：不考虑答案输出的顺序 (无序，可排序)
 * </pre>
 *
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * @since 2019-09-22
 */
public class StringAnagram {

    /**
     * 分治算法思路：拆分字符串s中与字符串p相同长度的所有子串，总共len(s)-len(p)个；
     * 对于每个子串，判断所有字符之和是否相同，且子串中的所有字符都在字符串p的字符集合中。
     * <pre>
     *
     * </pre>
     * 执行用时：897 ms，内存消耗：38.9 MB
     */
    private static List<Integer> findAnagrams1(String s, String p) {
        if (s == null || s.length() < p.length()) {
            return Collections.emptyList();
        }
        int plen = p.length();
        // 小写字符共26个，集合容量：26 * 4 / 3 + 1 = 36
        Set<Character> pset = new HashSet<>(16);
        // 剪枝优化
        int psum = 0;
        for (int i = 0; i < plen; i++) {
            char ch = p.charAt(i);
            psum += ch;
            pset.add(ch);
        }
        // 在 s 中查找与 p 长度相同的字母异位词
        List<Integer> result = new ArrayList<>();
        int firstLen = s.length() - plen;
        for (int i = 0; i <= firstLen; i++) {
            int secondLen = i + plen;
            int sum = psum;
            for (int j = i; j < secondLen; j++) {
                char ch = s.charAt(j);
                sum -= ch;
                // 剪枝
                if (sum < 0 || !pset.contains(ch)) {
                    break;
                }
            }
            if (sum == 0) {
                // 可能存在哈希冲突问题，内容不一样完全一样
                // 找到 p 的字母异位词了
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 滑动窗口算法。
     * <pre>
     * 对于window/needs计数器，使用包装类型的Map对象，存在基本类型与包装类型的自动包装与拆包装。
     * </pre>
     * 时间复杂度是 O(m + n)，空间复杂度是 O(26)
     * <p>
     * 执行用时：41 ms，内存消耗：38.7 MB
     */
    private static List<Integer> findAnagrams2(String s, String p) {
        if (s == null || s.length() < p.length()) {
            return Collections.emptyList();
        }
        int left = 0;
        int right = 0;
        // 记录s中所有与p长度相同的「字母异位词的起始索引」
        List<Integer> startIndexes = new ArrayList<>();

        // 相当于两个计数器
        Map<Character, Integer> window = new HashMap<>(16);
        Map<Character, Integer> needs = new HashMap<>(16);

        int plen = p.length();
        for (int i = 0; i < plen; i++) {
            char ch = p.charAt(i);
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
                    // 字符ch的出现次数符合要求了
                    match++;
                }
            }
            right++;

            // window中的字符串已符合needs的要求了
            while (match == needsSize) {
                if (right - left == plen) {
                    // 找到一个可行解，更新结果
                    startIndexes.add(left);
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
        return startIndexes;
    }

    /**
     * 滑动窗口算法。
     * <pre>
     * 优化：对于window/needs计数器，使用基本类型的数组替换Map对象，避免不必要的基本类型与包装类型的自动包装与拆包装。
     * ({@code int[]} => {@code Map})
     *
     * C语言代码风格
     * </pre>
     * 执行用时：8 ms，内存消耗：38.1 MB
     * <p>
     * 这个版本的执行用时比第一版减少了33ms，耗时降低了80%。
     * 在大数据量场景下，包装类型的自动装箱与拆箱还是挺耗时的。
     */
    private static List<Integer> findAnagrams3(String s, String p) {
        if (s == null || s.length() < p.length()) {
            return Collections.emptyList();
        }
        int left = 0;
        int right = 0;
        // 记录s中所有与p长度相同的「字母异位词的起始索引」
        List<Integer> startIndexes = new ArrayList<>();

        // 相当于两个计数器
        int[] window = new int[128];
        int[] needs = new int[128];
        int needsSize = 0;

        int plen = p.length();
        for (int i = 0; i < plen; i++) {
            char ch = p.charAt(i);
            needs[ch]++;
            if (needs[ch] == 1) {
                needsSize++;
            }
        }

        // 记录window中已经有多少个字符符合要求了
        int match = 0;

        int slen = s.length();
        while (right < slen) {
            char rc = s.charAt(right);
            int nr = needs[rc];
            if (nr > 0) {
                // 加入window
                int wc = ++window[rc];
                if (wc == nr) {
                    // 字符ch的出现次数符合要求了
                    match++;
                }
            }
            right++;

            // window中的字符串已符合needs的要求了
            while (match == needsSize) {
                if (right - left == plen) {
                    // 找到一个可行解，更新结果
                    startIndexes.add(left);
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
        return startIndexes;
    }

    /**
     * 滑动窗口算法。
     * <pre>
     * 优化：在解法3的基础上，减少{@code int}临时变量的创建。
     *
     * 代码可读性差
     * </pre>
     * 执行用时：7 ms，内存消耗：37.5 MB
     *
     * @see #findAnagrams3(String, String)
     */
    private static List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() < p.length()) {
            return Collections.emptyList();
        }
        int left = 0;
        int right = 0;
        // 记录s中所有与p长度相同的「字母异位词的起始索引」
        List<Integer> startIndexes = new ArrayList<>();

        // 相当于两个计数器，小写字母的字符
        int[] window = new int[128];
        int[] needs = new int[128];
        int needsSize = 0;

        int plen = p.length();
        for (int i = 0; i < plen; i++) {
            if ((needs[p.charAt(i)]++) == 0) {
                needsSize++;
            }
        }

        // 记录window中已经有多少个字符符合要求了
        int match = 0;

        int slen = s.length();
        while (right < slen) {
            char rc = s.charAt(right);
            if (needs[rc] > 0) {
                // 加入window
                if (++window[rc] == needs[rc]) {
                    // 字符ch的出现次数符合要求了
                    match++;
                }
            }
            right++;

            // window中的字符串已符合needs的要求了
            while (match == needsSize) {
                if (right - left == plen) {
                    // 找到一个可行解，更新结果
                    startIndexes.add(left);
                }
                char lc = s.charAt(left);
                if (needs[lc] > 0) {
                    // 移出window
                    if (--window[lc] < needs[lc]) {
                        // 字符lc的出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }
        return startIndexes;
    }

    // 测试

    @Test(dataProvider = "findAnagramsTestData")
    public void findAnagramsTest(String s, String p, List<Integer> expected) {
        assertThat(findAnagrams(s, p)).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "findAnagramsTestData")
    public static Object[][] findAnagramsTestData() {
        return new Object[][]{
                {"cbaebabacd", "abc", Arrays.asList(0, 6)},
                // 最前面匹配
                {"a", "a", Collections.singletonList(0)},
                {"aaa", "aaa", Collections.singletonList(0)},
                {"zzz", "zzz", Collections.singletonList(0)},
                // 对称型
                {"aaa", "a", Arrays.asList(0, 1, 2)},
                {"aaa", "aa", Arrays.asList(0, 1)},
                {"abab", "ab", Arrays.asList(0, 1, 2)},
                // 最末尾匹配
                {"caeababac", "abc", Collections.singletonList(6)},
                // 未匹配
                // 字符之和冲突
                {"af", "be", Collections.emptyList()},
        };
    }

}
