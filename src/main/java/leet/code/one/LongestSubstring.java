package leet.code.one;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 3. 无重复字符的最长子串。
 *
 * 题目描述
 * <pre>
 * 给定一个字符串 S，请你找出其中不含有重复字符的 最长子串 的长度。
 * </pre>
 *
 * 1.认识题目
 * <pre>
 * 1.条件：给定一个字符串 S (隐含条件：字符串 T 为所有字符组成的字符串序列，且字符不重复)
 * 2.目标：找出其中不含有重复字符的 最长子串
 * 3.结果：最长子串 的长度
 * </pre>
 *
 * 2.认识问题
 * <pre>
 *
 * </pre>
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @since 2019-09-24
 */
public class LongestSubstring {

    /**
     * 方法二：滑动窗口
     * <p>
     * 执行用时 : 12 ms, 在所有 Java 提交中击败了 59.55% 的用户
     * 内存消耗 : 36.5 MB, 在所有 Java 提交中击败了 80.94% 的用户
     */
    private static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // 最长子串的长度
        int maxLen = 0;
        // 集合，判断重复字符
        Set<Character> set = new HashSet<>(16);
        int strLen = s.length();
        int i = 0;
        int j = 0;
        while (i < strLen && j < strLen) {
            // try to extend the range [i, j]
            if (set.contains(s.charAt(j))) {
                // 出现重复字符，左滑1步
                set.remove(s.charAt(i++));
            } else {
                // 找到一个可行解，右滑1步
                set.add(s.charAt(j++));
                // 保存最优解
                maxLen = Math.max(maxLen, j - i);
            }
        }
        return maxLen;
    }

    /**
     * 方法三：优化的滑动窗口
     * <p>
     * 执行用时 : 10 ms, 在所有 Java 提交中击败了 75.32% 的用户
     * 内存消耗 : 36.1 MB, 在所有 Java 提交中击败了 94.64% 的用户
     */
    private static int lengthOfLongestSubstring3(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // 最长子串的长度
        int maxLen = 0;
        // 散列表(<char, index>)，判断重复字符
        Map<Character, Integer> map = new HashMap<>(16);
        int strLen = s.length();
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < strLen; j++) {
            if (map.containsKey(s.charAt(j))) {
                // 出现重复字符，左滑N步
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            // 找到一个可行解，右滑1步
            map.put(s.charAt(j), j);
            // 保存最优解
            maxLen = Math.max(j - i + 1, maxLen);
        }
        return maxLen;
    }

    // 测试

    @Test(dataProvider = "lengthOfLongestSubstringTestData")
    public void lengthOfLongestSubstringTest(String s, int expected) {
        assertThat(lengthOfLongestSubstring3(s)).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "lengthOfLongestSubstringTestData")
    public static Object[][] lengthOfLongestSubstringTestData() {
        return new Object[][]{
                {"abcabcbb", 3},
                {"bbbbb", 1},
                {"pwwkew", 3},
                {"abcdef", 6},
                {"aab", 2},
                {"cdd", 2},
                {"az", 2},
                {" ", 1},
                {"", 0},
        };
    }

}
