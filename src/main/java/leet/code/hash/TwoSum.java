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
 * 1.认识题目
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

    /**
     * 两数之和的四种解法的六个实现(排序+折半查找/封装+排序+折半查找/哈希)
     * https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-de-si-chong-jie-fa-pai-6vatw/
     * <pre>
     * 解法四：哈希表3
     * 算法描述
     * 在哈希表2的基础上仍可改进，即每次在哈希表中搜索补数时，设置left和right两个指针，
     * 一次搜索nums中左右两端的元素的补数，可以得到耗时0ms的效果。
     * </pre>
     */
    public static int[] twoSum(int[] nums, int target) {
        // 注册表(<target-num, index>)
        Map<Integer, Integer> operandIndexMap = new HashMap<>(16);
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int leftNum = nums[left];
            int rightNum = nums[right];
            // 询问是否有人找过我
            Integer leftIndex = operandIndexMap.get(leftNum);
            if (leftIndex != null) {
                // 发现目标
                return new int[]{leftIndex, left};
            } else {
                // 登记注册我要查找的人
                int leftOperand = target - leftNum;
                operandIndexMap.put(leftOperand, left++);
            }
            Integer rightIndex = operandIndexMap.get(rightNum);
            if (rightIndex != null) {
                // 发现目标
                return new int[]{rightIndex, right};
            } else {
                // 登记注册我要查找的人
                int rightOperand = target - rightNum;
                operandIndexMap.put(rightOperand, right--);
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 两数之和
     * https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
     * <pre>
     * 方法二：查找表法
     * 在遍历的同时，记录一些信息，以省去一层循环，这是"以空间换时间"的想法
     * 需要记录已经遍历过的数值和它所对应的下标，可以借助查找表实现
     * 查找表有两个常用的实现：
     * * 哈希表
     * * 平衡二叉搜索树，元素有序
     *
     * 方法二：哈希表
     * 思路及算法
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。
     * 因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。
     * 如果存在，我们需要找出它的索引。
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N) 降低到 O(1)。
     * </pre>
     */
    public static int[] twoSum_Hash2(int[] nums, int target) {
        // 注册表(<target-num, index>)
        Map<Integer, Integer> operandIndexMap = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 询问是否有人找过我
            Integer index = operandIndexMap.get(num);
            if (index != null) {
                // 发现目标
                return new int[]{index, i};
            } else {
                // 登记注册我要查找的人
                int operand = target - num;
                operandIndexMap.put(operand, i);
            }
        }
        return new int[]{-1, -1};
//        throw new IllegalArgumentException("No two sum solution");
    }
}
