package leet.code.sliding.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * <p></p>
 * https://leetcode-cn.com/problems/minimum-window-substring/
 * <pre>
 * 给你一个字符串 s 、一个字符串 t 。
 * 返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 * 示例 3：
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * 提示：
 * 1 <= s.length, t.length <= 10^5
 * s 和 t 由英文字母组成
 *
 * 进阶：你能设计一个在 O(n) 时间内解决此问题的算法吗？
 * </pre>
 * <pre>
 * 1.认识问题
 * 一个字符串 s 、一个字符串 t
 * s 中涵盖 t 所有字符的最小子串
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * s 和 t 由英文字母组成
 *
 * 特性：
 * 全局最优解
 * 重复字符计数器，哈希表
 * 在 O(n) 时间内解决此问题的算法，滑动窗口
 * </pre>
 *
 * @author guangyi
 */
public class MinWindow {

    /**
     * 最小覆盖子串
     * https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
     * <pre>
     * 方法一：滑动窗口
     * 思路和算法
     * 本问题要求我们返回字符串 ss 中包含字符串 tt 的全部字符的最小窗口。
     * 我们称包含 tt 的全部字母的窗口为「可行」窗口。
     *
     * 我们可以用滑动窗口的思想解决这个问题。
     * 在滑动窗口类型的问题中都会有两个指针，一个用于「延伸」现有窗口的 r 指针，和一个用于「收缩」窗口的 l 指针。
     * 在任意时刻，只有一个指针运动，而另一个保持静止。
     * 我们在 s 上滑动窗口，通过移动 r 指针不断扩张窗口。
     * 当窗口包含 t 全部所需的字符后，如果能收缩，我们就收缩窗口直到得到最小窗口。
     *
     * 如何判断当前的窗口包含所有 t 所需的字符呢？
     * 我们可以用一个哈希表表示 t 中所有的字符以及它们的个数，用一个哈希表动态维护窗口中所有的字符以及它们的个数，
     * 如果这个动态表中包含 t 的哈希表中的所有字符，并且对应的个数都不小于 t 的哈希表中各个字符的个数，那么当前的窗口是「可行」的。
     *
     * 注意：这里 t 中可能出现重复的字符，所以我们要记录字符的个数。
     * </pre>
     * <pre>
     * 对于window/needs计数器，使用包装类型的Map对象，存在基本类型与包装类型的自动包装与拆包装。
     *
     * 时间复杂度是 O(m + n)，空间复杂度是 O(52)
     * </pre>
     */
    public static String minWindow_One(String str, String target) {
        if (target.length() > str.length()) {
            return "";
        }
        // 记录最小子串的开始位置和长度
        int start = 0;
        int minLength = Integer.MAX_VALUE;

        // 滑动窗口计数器
        // 大小写英文字母共52个
        Map<Character, Integer> window = new HashMap<>(128);
        // 目标字符串
        Map<Character, Integer> needs = new HashMap<>(128);

        int targetLength = target.length();
        for (int i = 0; i < targetLength; i++) {
            char ch = target.charAt(i);
            needs.put(ch, needs.getOrDefault(ch, 0) + 1);
        }
        // 汉明距离
        int distance = needs.size();
        // 记录window中已经有多少个字符符合要求了
        int match = 0;

        int left = 0;
        int right = 0;
        int strLength = str.length();
        while (right < strLength) {
            char rightChar = str.charAt(right);
            Integer needCount = needs.get(rightChar);
            if (needCount != null) {
                // 加入window
                int windowCount = window.getOrDefault(rightChar, 0) + 1;
                window.put(rightChar, windowCount);
                // 巧妙：等于判断规避了重复字符
                if (windowCount == needCount) {
                    // 这个字符的出现次数符合要求了
                    match++;
                }
            }
            right++;

            while (match == distance) {
                // window中的字符串已符合needs的要求了
                // 找到一个可行解
                int subLength = right - left;
                if (subLength < minLength) {
                    // 找到一个更优解，更新最小子串的开始位置和长度
                    start = left;
                    minLength = subLength;
                }
                char leftChar = str.charAt(left);
                needCount = needs.get(leftChar);
                if (needCount != null) {
                    // 移出window
                    int windowCount = window.get(leftChar) - 1;
                    window.put(leftChar, windowCount);
                    // 巧妙：小于判断规避了重复字符
                    if (windowCount < needCount) {
                        // 这个字符出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" :
                str.substring(start, start + minLength);
    }

    /**
     * 最小覆盖子串 | 图解滑动窗口 | 最通俗易懂的讲解
     * https://leetcode-cn.com/problems/minimum-window-substring/solution/leetcode-76-zui-xiao-fu-gai-zi-chuan-cja-lmqz/
     * <pre>
     * 方法一：滑动窗口
     * 1、思路
     * (滑动窗口) O(n)
     * 这道题要求我们返回字符串 s中包含字符串 t 的全部字符的最小窗口，我们利用滑动窗口的思想解决这个问题。
     * 因此我们需要两个哈希表，hs哈希表维护的是s字符串中滑动窗口中各个字符出现多少次，ht哈希表维护的是t字符串各个字符出现多少次。
     * 如果hs哈希表中包含ht哈希表中的所有字符，并且对应的个数都不小于ht哈希表中各个字符的个数，那么说明当前的窗口是可行的，可行中的长度最短的滑动窗口就是答案。
     * </pre>
     * <pre>
     * 优化：对于window/needs计数器，使用基本类型的数组替换Map对象，避免不必要的基本类型与包装类型的自动包装与拆包装。
     * ({@code int[]} => {@code Map})
     *
     * C语言代码风格
     *
     * 时间复杂度是 O(m + n)，空间复杂度是 O(123)
     *
     * 这个版本的执行用时比第一版减少了6ms，耗时降低了75%。
     * 在大数据量场景下，包装类型的自动装箱与拆箱还是挺耗时的。
     * </pre>
     */
    public static String minWindow(String str, String target) {
        if (target.length() > str.length()) {
            return "";
        }
        // 记录最小子串的开始位置和长度
        int start = 0;
        int minLength = Integer.MAX_VALUE;

        // 滑动窗口计数器
        // ascii('z') = 122
        int[] window = new int[128];
        // 目标字符串
        int[] needs = new int[128];
        // 汉明距离
        int distance = 0;

        int targetLength = target.length();
        for (int i = 0; i < targetLength; i++) {
            char ch = target.charAt(i);
            needs[ch]++;
            if (needs[ch] == 1) {
                distance++;
            }
        }
        // 记录window中已经有多少个字符符合要求了
        int match = 0;

        int left = 0;
        int right = 0;
        int strLength = str.length();
        while (right < strLength) {
            char rightChar = str.charAt(right);
            int needCount = needs[rightChar];
            if (needCount > 0) {
                // 加入window
                int windowCount = ++window[rightChar];
                // 巧妙：等于判断规避了重复字符
                if (windowCount == needCount) {
                    // 这个字符的出现次数符合要求了
                    match++;
                }
            }
            right++;

            while (match == distance) {
                // window中的字符串已符合needs的要求了
                // 找到一个可行解
                int subLength = right - left;
                if (subLength < minLength) {
                    // 找到一个更优解，更新最小子串的开始位置和长度
                    start = left;
                    minLength = subLength;
                }
                char leftChar = str.charAt(left);
                needCount = needs[leftChar];
                if (needCount > 0) {
                    // 移出window
                    int windowCount = --window[leftChar];
                    // 巧妙：小于判断规避了重复字符
                    if (windowCount < needCount) {
                        // 这个字符出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" :
                str.substring(start, start + minLength);
    }

    /**
     * 非常容易理解的滑动窗口思想
     * https://leetcode-cn.com/problems/minimum-window-substring/solution/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/
     * <pre>
     * 滑动窗口的思想：
     * 用i,j表示滑动窗口的左边界和右边界，通过改变i,j来扩展和收缩滑动窗口，可以想象成一个窗口在字符串上游走，
     * 当这个窗口包含的元素满足条件，即包含字符串T的所有元素，记录下这个滑动窗口的长度j-i+1，这些长度中的最小值就是要求的结果。
     *
     * 步骤一
     * 不断增加j使滑动窗口增大，直到窗口包含了T的所有元素。
     *
     * 步骤二
     * 不断增加i使滑动窗口缩小，因为是要求最小字串，所以将不必要的元素排除在外，使长度减小，
     * 直到碰到一个必须包含的元素，这个时候不能再扔了，再扔就不满足条件了，
     * 记录此时滑动窗口的长度，并保存最小值。
     *
     * 步骤三
     * 让i再增加一个位置，这个时候滑动窗口肯定不满足条件了，那么继续从步骤一开始执行，寻找新的满足条件的滑动窗口。
     * 如此反复，直到j超出了字符串S范围。
     *
     * 面临的问题：
     * 如何判断滑动窗口包含了T的所有元素？
     * 我们用一个字典need来表示当前滑动窗口中需要的各元素的数量，一开始滑动窗口为空，
     * 用T中各元素来初始化这个need，当滑动窗口扩展或者收缩的时候，去维护这个need字典。
     * 记住一点：need始终记录着当前滑动窗口下，我们还需要的元素数量，我们在改变i,j时，需同步维护need。
     * 值得注意的是，只要某个元素包含在滑动窗口中，我们就会在need中存储这个元素的数量，
     * 如果某个元素存储的是负数代表这个元素是多余的。
     *
     * 回到问题中来，那么如何判断滑动窗口包含了T的所有元素？
     * 结论就是当need中所有元素的数量都小于等于0时，表示当前滑动窗口不再需要任何元素。
     *
     * 优化
     * 如果每次判断滑动窗口是否包含了T的所有元素，都去遍历need看是否所有元素数量都小于等于0，
     * 这个会耗费O(k)O(k)的时间复杂度，k代表字典长度，最坏情况下，k可能等于len(S)。
     * 其实这个是可以避免的，我们可以维护一个额外的变量needCnt来记录所需元素的总数量。
     * 当我们碰到一个所需元素c，不仅need[c]的数量减少1，同时needCnt也要减少1，
     * 这样我们通过needCnt就可以知道是否满足条件，而无需遍历字典了。
     * </pre>
     * <pre>
     * 方法一：滑动窗口
     * 优化：在解法2的基础上，减少{@code int}临时变量的创建。
     *
     * 代码可读性差
     * </pre>
     *
     * @see #minWindow(String, String)
     */
    public static String minWindow_Three(String str, String target) {
        if (target.length() > str.length()) {
            return "";
        }
        // 记录最小子串的开始位置和长度
        int start = 0;
        int minLength = Integer.MAX_VALUE;

        // 滑动窗口计数器
        // ascii('z') = 122
        int[] window = new int[128];
        int[] needs = new int[128];
        // 汉明距离
        int distance = 0;

        int targetLength = target.length();
        for (int i = 0; i < targetLength; i++) {
            if ((needs[target.charAt(i)]++) == 0) {
                distance++;
            }
        }

        // 记录window中已经有多少字符符合要求了
        int match = 0;

        int left = 0;
        int right = 0;
        int strLength = str.length();
        while (right < strLength) {
            char rightChar = str.charAt(right);
            if (needs[rightChar] > 0) {
                // 加入window
                if (++window[rightChar] == needs[rightChar]) {
                    // 这个字符的出现次数符合要求了
                    match++;
                }
            }
            right++;

            while (match == distance) {
                // window中的字符串已符合needs的要求了
                // 找到一个可行解
                if (right - left < minLength) {
                    // 找到一个更优解，更新最小子串的开始位置和长度
                    start = left;
                    minLength = right - left;
                }
                char leftChar = str.charAt(left);
                if (needs[leftChar] > 0) {
                    // 移出window
                    if (--window[leftChar] < needs[leftChar]) {
                        // 字符lc的出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" :
                str.substring(start, start + minLength);
    }
}
