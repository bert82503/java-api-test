package leet.code.problem.number;

import java.util.BitSet;

/**
 * <a href="https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/">
 *     面试题03. 数组中重复的数字</a>
 *
 * @author guangyi
 * @since 2020-06-13
 */
public class RepeatNumber {

    /**
     * 找出数组中重复的数字。
     * <pre>
     * 执行用时 : 1 ms, 在所有 Java 提交中击败了 91.70% 的用户
     * 内存消耗 : 47.7 MB, 在所有 Java 提交中击败了 100.00% 的用户
     * </pre>
     *
     * @param nums 数字数组
     * @return 数组中重复的数字
     */
    public static int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            throw new IllegalArgumentException("nums is empty");
        }
        int length = nums.length;

        // 注册表
        int[] numberMap = new int[length];
        for (int num : nums) {
            if ((++numberMap[num]) > 1) {
                return num;
            }
        }

        // 位图
        BitSet bitSet = new BitSet(length);
        for (int num : nums) {
            if (bitSet.get(num)) {
                return num;
            }
            bitSet.set(num);
        }

        throw new IllegalStateException("repeat number is not find");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
    }
}
