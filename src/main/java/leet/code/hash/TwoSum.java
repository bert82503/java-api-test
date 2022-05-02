package leet.code.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * <p></p>
 * https://leetcode-cn.com/problems/two-sum/
 * <pre>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，
 * 并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 提示：
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 只会存在一个有效答案
 *
 * 进阶：你可以想出一个时间复杂度小于 O(n^2) 的算法吗？
 * </pre>
 * <pre>
 * 1. 认识题目
 * 一个整数数组和一个整数目标值
 * 数组下标
 * 每种输入只会对应一个答案，只会存在一个有效答案
 * 同一个元素在答案里不能重复出现
 * 可以按任意顺序返回答案
 * </pre>
 *
 * @author guangyi
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        // 注册表(<target-num, index>)
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 询问是否有人找过我
            Integer index = map.get(num);
            if (index != null) {
                // 发现目标
                return new int[]{index, i};
            }
            // 登记注册我要查找的人
            int operand = target - num;
            map.put(operand, i);
        }
        return new int[]{-1, -1};
    }
}
