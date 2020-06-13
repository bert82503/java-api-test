package leet.code.problem.number;

import java.util.BitSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/">
 *     面试题03. 数组中重复的数字</a>
 *
 * <pre>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * </pre>
 *
 * <pre>
 * 一、认识题目：
 * "所有数字都在 0～n-1 的范围内"，转换为"数字可以作为数组的下标"
 * "找出数组中任意一个重复的数字"，找到一个即可结束查找
 * </pre>
 *
 * @author guangyi
 * @since 2020-06-13
 */
public class RepeatNumber {

    /**
     * 找出数组中重复的数字。
     *
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

    /**
     * Test cases.
     */
    public static void main(String[] args) {
        assertThat(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}))
                .isEqualTo(2);
        assertThat(findRepeatNumber(new int[]{0, 0}))
                .isEqualTo(0);
        assertThat(findRepeatNumber(new int[]{1, 1}))
                .isEqualTo(1);
        assertThat(findRepeatNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 0}))
                .isEqualTo(0);
        assertThat(findRepeatNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 3}))
                .isEqualTo(3);
        assertThat(findRepeatNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 4, 0}))
                .isEqualTo(4);
    }
}
