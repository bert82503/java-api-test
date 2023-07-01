package leet.code.pointer;

import java.util.Arrays;

/**
 * 345. 反转字符串中的元音字母
 * <p></p>
 * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
 * <pre>
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 * 示例 1：
 * 输入：s = "hello"
 * 输出："holle"
 *
 * 示例 2：
 * 输入：s = "leetcode"
 * 输出："leotcede"
 *
 * 提示：
 * 1 <= s.length <= 3 * 10^5
 * s 由 可打印的 ASCII 字符组成
 * </pre>
 *
 * @author guangyi
 */
public class ReverseVowels {

    /**
     * ASCII('z') = 122
     */
    private static final boolean[] CHAR_MAP = new boolean[128];
    static {
        Arrays.fill(CHAR_MAP, true);
        CHAR_MAP['a'] = false;
        CHAR_MAP['e'] = false;
        CHAR_MAP['i'] = false;
        CHAR_MAP['o'] = false;
        CHAR_MAP['u'] = false;
        CHAR_MAP['A'] = false;
        CHAR_MAP['E'] = false;
        CHAR_MAP['I'] = false;
        CHAR_MAP['O'] = false;
        CHAR_MAP['U'] = false;
    }

    /**
     * 左右指针
     */
    public static String reverseVowels(String str) {
        int length = str.length();
        if (length <= 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        // 左右指针
        int left = 0;
        int right = length - 1;
        while (left < right) {
            // 寻找最左侧的元音字母
            while (left < right && CHAR_MAP[chars[left]]) {
                left++;
            }
            // 寻找最右侧的元音字母
            while (left < right && CHAR_MAP[chars[right]]) {
                right--;
            }
            if (left < right) {
                swap(chars, left, right);
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    /**
     * 互换元素。
     */
    private static void swap(char[] chars, int left, int right) {
        char ch = chars[left];
        chars[left] = chars[right];
        chars[right] = ch;
    }
}
