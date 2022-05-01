package leet.code.sliding.window;

import java.util.Arrays;

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

    /**
     * 无重复字符的最长子串
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
     * <pre>
     * 方法一：滑动窗口及优化
     * 关键字：重复字符 --> 出现1次
     * 模式识别1：一旦涉及出现次数，需要用到散列表
     * 构造子串，散列表存下标
     * 模式识别2：涉及子串，考虑滑动窗口
     *
     * 方法一：滑动窗口
     * 思路和算法
     * 我们先用一个例子考虑如何在较优的时间复杂度内通过本题。
     * 我们不妨以示例一中的字符串 abcabcbb 为例，找出从每一个字符开始的，不包含重复字符的最长子串，那么其中最长的那个字符串即为答案。
     * 发现了什么？如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！
     *
     * 这样一来，我们就可以使用「滑动窗口」来解决这个问题了：
     * * 我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，
     *   其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 rk；
     * * 在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，
     *   然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。
     *   在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。
     *   我们记录下这个子串的长度；
     * * 在枚举结束后，我们找到的最长的子串的长度即为答案。
     *
     * 判断重复字符
     * 在上面的流程中，我们还需要使用一种数据结构来判断 是否有重复的字符，常用的数据结构为哈希集合。
     * 在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。
     * 至此，我们就完美解决了本题。
     * </pre>
     *
     * 滑动窗口
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
     * <pre>
     * 这道题主要用到思路是：滑动窗口
     *
     * 什么是滑动窗口？
     * 其实就是一个队列，比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，
     * 当再进入 a，队列变成了 abca，这时候不满足要求。
     * 所以，我们要移动这个队列！
     *
     * 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     *
     * 下面介绍关于滑动窗口的万能模板，可以解决相关问题，相信一定可以对滑动窗口有一定了解！
     * 模板虽好，还是少套为好！多思考！更重要！
     * </pre>
     */
    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        // 最长子串的长度
        int maxLength = Integer.MIN_VALUE;
        // 注册表(<char, index>)
        int[] charMap = new int[256];
        Arrays.fill(charMap, -1);

        int length = str.length();
        for (int right = 0, left = 0; right < length; right++) {
            char ch = str.charAt(right);
            if (charMap[ch] >= 0) {
                // 出现重复字符，右滑N步
                // 存在左滑回退N步的风险，故取最大值
                left = Math.max(charMap[ch] + 1, left);
            }
            // 找到一个可行解，右滑1步
            charMap[ch] = right;
            maxLength = Math.max(right - left + 1, maxLength);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring_Two(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        // 不含有重复字符的 最长子串 的长度
        int maxLength = Integer.MIN_VALUE;
        // 字符计数器，用于不含有重复字符判断
        int[] charMap = new int[256];
        int left = 0;
        int right = 0;
        while (right < str.length()) {
            char ch = str.charAt(right);
            charMap[ch] += 1;
            if (charMap[ch] > 1) {
                // 出现重复字符
                // 发现一个子串
                maxLength = Math.max(maxLength, right - left);
                // 左指针右移
                char lowChar;
                do {
                    lowChar = str.charAt(left++);
                    charMap[lowChar] -= 1;
                } while (lowChar != ch);
            }
            // 右指针右移
            right++;
        }
        // 字符全部不重复
        if (right > left) {
            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}
