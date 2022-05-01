package leet.code.sliding.window;

/**
 * 3. 无重复字符的最长子串
 * <p></p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * <pre>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 提示：
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 * </pre>
 * <pre>
 * 1. 认识题目
 * 一个字符串 s，s 由英文字母、数字、符号和空格组成
 * 子串，连续字符的子串
 * 找出其中不含有重复字符的 最长子串 的长度，全局最优解
 * 不含有重复字符的 子串，可能存在多个
 * </pre>
 * <pre>
 * 特性：
 * 1. 重复，哈希表
 * 2. 全局最优解
 * 3. 滑动窗口
 * </pre>
 *
 * @author guangyi
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        // 不含有重复字符的 最长子串 的长度
        int maxLength = Integer.MIN_VALUE;
        // 字符计数器，用于不含有重复字符判断
        int[] charMap = new int[256];
        int low = 0;
        int high = 0;
        while (high < str.length()) {
            char ch = str.charAt(high);
            charMap[ch] += 1;
            if (charMap[ch] > 1) {
                // 出现重复字符
                // 发现一个子串
                maxLength = Math.max(maxLength, high - low);
                // 左指针右移
                char lowChar;
                do {
                    lowChar = str.charAt(low++);
                    charMap[lowChar] -= 1;
                } while (lowChar != ch);
            }
            // 右指针右移
            high++;
        }
        // 字符全部不重复
        if (high > low) {
            maxLength = Math.max(maxLength, high - low);
        }
        return maxLength;
    }
}
