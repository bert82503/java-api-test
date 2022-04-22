package leet.code.sum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * <p></p>
 * https://leetcode-cn.com/problems/two-sum/
 * <p></p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，
 * 并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 提示：
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 只会存在一个有效答案
 *
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 * <p></p>
 * 1.认识题目
 * <pre>
 * 1.条件：一个整数数组 nums 和一个整数目标值 target
 * 2.目标：在该数组中找出 和为目标值 target  的那 两个 整数
 * 3.结果：返回它们的数组下标
 * 4.每种输入只会对应一个答案 (答案唯一，即对于一个测试用例找到一个立即结束查找)
 * 5.数组中同一个元素在答案里不能重复出现，不能重复利用这个数组中同样的元素 (结果输出的数组下标唯一)
 * </pre>
 *
 * 2.分析题目
 * <pre>
 * 1.整数，正数 or 负数？多个相同重复的整数？
 * 2.num1 + num2 = target
 * 3.num1和num2所在的数组下标，整数和数组下标映射的哈希表({@code <num, index>})
 * 4.找到一个答案立即结束查找
 * 5.两个整数结果的数组下标不能相同
 * </pre>
 */
public class TwoSum {
    /**
     * 解法三：一遍哈希表
     * <pre>
     * 很容易看出来，上面的方案仍然可以优化。
     * 就是每个人都来问一下主持人，自己要找的人有没有来登记过。
     * 如果没有的话，就把自己的要求写下来，等着别人来找自己。
     *
     * 剪枝思想
     * </pre>
     * 时间复杂度是 O(n)，空间复杂度是 O(n)
     *
     * <pre>
     * 执行用时：3 ms，内存消耗：36.7 MB
     * </pre>
     *
     * <pre>
     * 2sum 问题最坏的情况是，第一个人和最后一个人配对，每个人都发了一次言。
     * 时间复杂度是 O(n)，空间复杂度也是 O(n)，
     * 因为主持人要用小本本记录下每个人的发言，最坏的时候，要把所有人的诉求都记一遍。
     *
     * 从生活经验中我们能感觉到什么方案是最好的，而且也能够知道，什么算法已经到达了优化的极限。
     * 这就是所谓的”只使用人类的聪明才智“就可以做出来。
     *
     * 当然，如果没有编程的基础，可能不会想到`哈希表-登记册`这样的类比，但这应该只影响把解决方案转化为代码表达。
     * 想到解决方案这件事，其实是完全不依赖编码能力的。
     *
     * 面试很多年轻同学的时候，他们会一上来就直接开始写代码，然后把草纸写得一团糟。
     * 我都会给他们讲，要把算法和编码分开来看，你可以用伪代码，画图，甚至摆弄小道具的方法去寻求解法。
     * 有了明确的解法，再转化成可执行的代码，就是水到渠成的事情了。
     * </pre>
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("No two sum solution");
        }
        // <num, index>
        // 合适的初始容量，减少键冲突
        Map<Integer, Integer> numIndexMap = new HashMap<>(16384);
        // 每个人报出自己想要配对的人
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 配对的数字
            int operand = target - num;
            Integer index = numIndexMap.get(operand);
            if (index != null) {
                // 发现有人登记过，就是他
                return new int[]{index, i};
            } else {
                // 否则，主持人登记他的需求
                numIndexMap.put(num, i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 解法二：两遍哈希表
     * <pre>
     * 对于"暴力搜索"解法，怎么样可以更高效一点？时间复杂度：O(n2)，还有优化空间么？
     * 这时候要引入`哈希表`，其实就是一个登记册，写上你的名字和你的要求。
     * 如果每个人都提前在主持人那里登记一遍，然后只要大家依次再报出自己名字，主持人就能够识别到，ta 就是你要找的人。
     *
     * 哈希表-登记册/注册表的类比
     *
     * 考察点：待找的操作数和数组下标映射的哈希表(HashMap: <num, index>)
     * </pre>
     * 时间复杂度是 O(n)，空间复杂度是 O(n)
     *
     * <pre>
     * 执行用时：4 ms，内存消耗：38.3 MB
     * </pre>
     */
    public static int[] twoSum_HashMap_2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("No two sum solution");
        }
        int len = nums.length;
        Map<Integer, Integer> operandIndexMap = new HashMap<>(16);
        // 每个人登记自己想要配对的人，让主持人登记
        for (int i = 0; i < len; i++) {
            // 每个人报出自己想要配对的人
            int operand = target - nums[i];
            operandIndexMap.put(operand, i);
        }
        // 每个人再次报数的时候，主持人看一下名单里有没有他
        for (int j = 0; j < len; j++) {
            int num = nums[j];
            Integer operandIndex = operandIndexMap.get(num);
            if (operandIndex != null && j != operandIndex) {
                return new int[]{j, operandIndex};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // 解法一：暴力枚举法
}
