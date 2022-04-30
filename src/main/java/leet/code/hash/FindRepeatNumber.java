package leet.code.hash;

import java.util.BitSet;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * <p></p>
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * <pre>
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 限制：
 * 2 <= n <= 100000
 * </pre>
 * <pre>
 * 一、认识题目：
 * "所有数字都在 0～n-1 的范围内"，转换为"数字可以作为数组的下标"
 * "找出数组中任意一个重复的数字"，找到一个即可结束查找
 * </pre>
 *
 * @author guangyi
 */
public class FindRepeatNumber {

    /**
     * 剑指 Offer 03. 数组中重复的数字（哈希表 / 原地交换，清晰图解）
     * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/mian-shi-ti-03-shu-zu-zhong-zhong-fu-de-shu-zi-yua/
     * <pre>
     * 方法一：哈希表 / Set
     *
     * 方法二：原地交换
     * 题目说明尚未被充分使用，即 在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内 。
     * 此说明含义：数组元素的 索引 和 值 是 一对多 的关系。
     * 因此，可遍历数组并通过交换操作，使元素的 索引 与 值 一一对应（即 nums[i] = i）。
     * 因而，就能通过索引映射对应的值，起到与字典等价的作用。
     * </pre>
     */
    public static int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        return findRepeatNumber_IndexSwap(nums);
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字（哈希表 / 原地交换，清晰图解）
     * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/mian-shi-ti-03-shu-zu-zhong-zhong-fu-de-shu-zi-yua/
     * <pre>
     * 方法二：原地交换
     * 题目说明尚未被充分使用，即 在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内 。
     * 此说明含义：数组元素的 索引 和 值 是 一对多 的关系。
     * 因此，可遍历数组并通过交换操作，使元素的 索引 与 值 一一对应（即 nums[i] = i）。
     * 因而，就能通过索引映射对应的值，起到与字典等价的作用。
     * </pre>
     */
    private static int findRepeatNumber_IndexSwap(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int num = nums[i];
            if (num == i) {
                // 已交换
                i++;
                continue;
            }
            // 原地交换
            if (nums[num] == num) {
                return num;
            }
            nums[i] = nums[num];
            nums[num] = num;
        }
        return -1;
    }

    private static int findRepeatNumber_HashMap(int[] nums) {
        int length = nums.length;
        // 注册表
        int[] numberMap = new int[length];
        for (int num : nums) {
            if ((++numberMap[num]) > 1) {
                return num;
            }
        }

//        Map<Integer, Boolean> map = new HashMap<>(length * 4 / 3 + 1);
//        for (int num : nums) {
//            if (map.put(num, Boolean.TRUE) != null) {
//                return num;
//            }
//        }

        return -1;
    }

    private static int findRepeatNumber_BitSet(int[] nums) {
        int length = nums.length;
        // 位图
        BitSet bitSet = new BitSet(length);
        for (int num : nums) {
            if (bitSet.get(num)) {
                return num;
            }
            bitSet.set(num);
        }
        return -1;
    }
}
