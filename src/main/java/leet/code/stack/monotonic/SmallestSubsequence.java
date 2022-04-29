package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1081. 不同字符的最小子序列
 * <p></p>
 * https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/
 * <pre>
 * 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 * </pre>
 *
 * @author guangyi
 * @see RemoveDuplicateLetters
 */
public class SmallestSubsequence {

    public static String smallestSubsequence(String str) {
        // 递增栈
        // 存放去重的结果
        Deque<Character> monoStack = new LinkedList<>();
        // 字符计数器
        int[] count = new int[256];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i)]++;
        }
        // 字符去重
        boolean[] inStack = new boolean[256];

        for (char ch : str.toCharArray()) {
            count[ch]--;

            if (inStack[ch]) {
                continue;
            }

            // 出栈判定条件
            while (!monoStack.isEmpty() && ch < monoStack.getFirst()) {
                if (count[monoStack.getFirst()] == 0) {
                    break;
                }
                // 出栈
                // 若之后还有，则可以 pop
                // 弹出栈顶元素，并把该元素标记为不在栈中
                inStack[monoStack.removeFirst()] = false;
            }

            // 入栈
            monoStack.addFirst(ch);
            inStack[ch] = true;
        }
        // 结果计算
        StringBuilder sb = new StringBuilder(monoStack.size());
        while (!monoStack.isEmpty()) {
            sb.append(monoStack.removeFirst());
        }
        // 栈中元素插入顺序是反的，需要 reverse 一下
        return sb.reverse().toString();
    }
}
