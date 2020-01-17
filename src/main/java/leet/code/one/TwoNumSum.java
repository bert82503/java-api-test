package leet.code.one;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 1. 两数之和。
 *
 * 题目描述
 * <pre>
 * 给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那 `两个` 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * </pre>
 *
 * 1.认识题目
 * <pre>
 * 1.条件：一个整数数组 `nums` 和一个目标值 `target`
 * 2.目标：在该数组中找出和为目标值的那 `两个` 整数
 * 3.结果：返回他们的数组下标
 *
 * 4.说明1：每种输入只会对应一个答案 (答案唯一，即对于一个测试用例找到一个立即结束查找)
 * 5.说明2：不能重复利用这个数组中同样的元素 (结果输出的数组下标唯一)
 * </pre>
 *
 * 2.分析题目
 * <pre>
 * 1.整数，正数 or 负数？多个相同重复的整数？
 * 2.num1 + num2 = target
 * 3.num1和num2所在的数组下标，整数和数组下标映射的哈希表({@code <num, index>})
 *
 * 4.找到一个答案立即结束查找
 * 5.两个整数结果的数组下标不能相同
 * </pre>
 *
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @since 2019-09-21
 */
public class TwoNumSum {

    /**
     * 解法一：暴力法
     * <pre>
     * 最简单的想法就是把每两个都拿出来加一下，看看结果是不是我们想要的。
     * 但是直觉告诉我们，这样子并不高效。举一个很实际的例子就能明白。
     *
     * 比如这个周末你去参加线下相亲会，全场有且只有两个人才是真爱。
     * 于是我们每个人都要去找其他所有人聊天，去寻找 ta 是不是自己要找的另一半。
     * 每个人都要和每个人说话，这样时间复杂度很高，翻译成计算机的表示就是 O(n2)。
     * </pre>
     * 时间复杂度是 O(n2)，空间复杂度是 O(n)
     *
     * <pre>
     * 执行用时：33 ms，内存消耗：38.2 MB
     * </pre>
     */
    private static int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("No two sum solution");
        }
        int len = nums.length;
        // 每个人
        for (int i = 0; i < len; i++) {
            // 都去问其他的人
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
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
    private static int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("No two sum solution");
        }
        int len = nums.length;
        Map<Integer, Integer> operandIndexMap = new HashMap<>();
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
    private static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("No two sum solution");
        }
        // <num, index>
        // 合适的初始容量，减少键冲突
        Map<Integer, Integer> numIndexMap = new HashMap<>(16384);
        // 每个人报出自己想要配对的人
        int length = nums.length;
        for (int i = 0; i < length; i++) {
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

    // 测试

    @Test(dataProvider = "twoSumTestData")
    public void twoSumTest(int[] nums, int target, int[] expected) {
        assertThat(twoSum(nums, target)).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "twoSumTestData")
    public static Object[][] twoSumTestData() {
        return new Object[][]{
                // 最坏情况
                {new int[]{2, 7, 11, 15}, 17, new int[]{0, 3}},
                {new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}},
                {new int[]{2, 7, 11, 15}, 26, new int[]{2, 3}},
                {new int[]{3, 2, 4}, 6, new int[]{1, 2}},
                {new int[]{3, 2, 4}, 7, new int[]{0, 2}},
                {new int[]{3, 3, 1, 2}, 6, new int[]{0, 1}},
                {new int[]{1, 4, 3, 3}, 6, new int[]{2, 3}},
                {new int[]{1, 2, 3, 4, 1}, 2, new int[]{0, 4}},
                {new int[]{0, 0}, 0, new int[]{0, 1}},
                // 两个答案，不符合题目要求
//                {new int[]{3, 2, 4, 3}, 6, new int[]{1, 2}},
        };
    }

}
