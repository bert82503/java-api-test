package leet.code.sum;

/**
 * 剑指 Offer 64. 求1+2+…+n
 * <p></p>
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 * <pre>
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 示例 1：
 * 输入: n = 3
 * 输出: 6
 *
 * 示例 2：
 * 输入: n = 9
 * 输出: 45
 *
 * 限制：
 * 1 <= n <= 10000
 * </pre>
 *
 * @author guangyi
 */
public class SumNums {

    /**
     * 求1+2+…+n
     * https://leetcode-cn.com/problems/qiu-12n-lcof/solution/qiu-12n-by-leetcode-solution/
     * <pre>
     * 前言
     * 首先我们梳理题目要求。题目要求我们不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句，
     * 因此我们手里能用的工具很少，列举出来发现只有加减法、赋值、位运算符以及逻辑运算符。
     *
     * 方法一：递归
     * 思路和算法
     * 通常实现递归的时候我们都会利用条件判断语句来决定递归的出口，
     * 但由于题目的限制我们不能使用条件判断语句，那么我们是否能使用别的办法来确定递归出口呢？
     * 答案就是逻辑运算符的短路性质。
     *
     * 方法二：快速乘
     * 思路和算法
     * 考虑 A 和 B 两数相乘的时候我们如何利用加法和位运算来模拟，其实就是将 B 二进制展开，
     * 如果 B 的二进制表示下第 i 位为 1，那么这一位对最后结果的贡献就是 A*(1<<i) ，即 A<<i。
     * 我们遍历 B 二进制展开下的每一位，将所有贡献累加起来就是最后的答案。
     * 这个方法也被称作「俄罗斯农民乘法」，感兴趣的读者可以自行网上搜索相关资料。
     * 这个方法经常被用于两数相乘取模的场景，如果两数相乘已经超过数据范围，但取模后不会超过，
     * 我们就可以利用这个方法来拆位取模计算贡献，保证每次运算都在数据范围内。
     * </pre>
     *
     * 面试题64. 求 1 + 2 + … + n（逻辑符短路，清晰图解）
     * https://leetcode-cn.com/problems/qiu-12n-lcof/solution/mian-shi-ti-64-qiu-1-2-nluo-ji-fu-duan-lu-qing-xi-/
     * <pre>
     * 逻辑运算符的短路效应：
     * 常见的逻辑运算符有三种，即 “与 && ”，“或 ∣∣ ”，“非 ! ” ；而其有重要的短路效应。
     * 本题需要实现 “当 n = 1n=1 时终止递归” 的需求，可通过短路效应实现。
     * > n > 1 && sumNums(n - 1) // 当 n = 1 时 n > 1 不成立 ，此时 “短路” ，终止后续递归
     * </pre>
     */
    public static int sumNums(int n) {
        // 逻辑运算符的短路效应
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
