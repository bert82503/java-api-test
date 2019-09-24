package leet.code.one;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 567. 字符串的排列。
 *
 * 题目描述
 * <pre>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 注意：
 * 1. 输入的字符串只包含小写字母
 * 2. 两个字符串的长度都在 [1, 10,000] 之间
 *
 * 示例1：
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2：
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * </pre>
 * <p>
 * 1.认识题目
 * <pre>
 * 1.条件：给定两个字符串 s1 和 s2
 * 2.目标：判断 s2 是否包含 s1 的排列，即第一个字符串s1的排列之一是第二个字符串s2的子串
 * 3.结果：布尔值 true/false
 *
 * 4.注意1：输入的字符串只包含小写字母 (值范围['a', 'z'])
 * 5.注意2：两个字符串的长度都在 [1, 10,000] 之间
 * </pre>
 * <p>
 * 2.认识问题
 * <pre>
 * 结合第1点和第5点，两个字符串 s1 和 s2 都是非空
 * </pre>
 * <p>
 * https://leetcode-cn.com/problems/permutation-in-string/
 *
 * @since 2019-09-24
 */
public class PermutationInString {

    /**
     * 滑动窗口算法。
     * <pre>
     * 优化：在解法3的基础上，减少{@code int}临时变量的创建。
     *
     * 代码可读性差
     * </pre>
     * 执行用时：7 ms，内存消耗：36.8 MB
     */
    private static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        int left = 0;
        int right = 0;

        // 相当于两个计数器，小写字母的字符
        int[] window = new int[128];
        int[] needs = new int[128];
        int needsSize = 0;

        int tlen = s1.length();
        for (int i = 0; i < tlen; i++) {
            if ((needs[s1.charAt(i)]++) == 0) {
                needsSize++;
            }
        }

        // 记录window中已经有多少个字符符合要求了
        int match = 0;

        int slen = s2.length();
        while (right < slen) {
            char rc = s2.charAt(right);
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
                if (right - left == tlen) {
                    // 找到一个可行解，更新结果，立即返回结果
                    return true;
                }
                char lc = s2.charAt(left);
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
        return false;
    }

    // 测试

    @Test(dataProvider = "checkInclusionTestData")
    public void checkInclusionTest(String s1, String s2, boolean expected) {
        assertThat(checkInclusion(s1, s2)).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "checkInclusionTestData")
    public static Object[][] checkInclusionTestData() {
        return new Object[][]{
                {"ab", "eidbaooo", true},
                // 最前面匹配
                {"a", "a", true},
                {"aaa", "aaa", true},
                {"zzz", "zzz", true},
                // 最末尾匹配
                {"abc", "caeababac", true},
                // 未匹配
                {"ab", "eidboaoo", false},
                {"af", "be", false},
        };
    }

}
