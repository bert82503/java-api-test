package leet.code.dynamic.programming;

/**
 * 509. 斐波那契数
 * <p></p>
 * https://leetcode-cn.com/problems/fibonacci-number/
 * <p></p>
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 * 也就是：
 * ```
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * ```
 * 给定 n ，请计算 F(n) 。
 *
 * 提示：
 * * 0 <= n <= 30
 * <p></p>
 *
 * @author guangyi
 */
public class Fib {

    // 方法一：动态规划

    /**
     * 根据状态转移方程和边界条件，可以得到时间复杂度和空间复杂度都是 O(n) 的实现。
     */
    public static int fib_DynamicProgramming(int n) {
        // 边界条件
        if (n < 2) {
            return n;
        }
        return fib_DynamicProgramming(n - 1) + fib_DynamicProgramming(n - 2);
    }

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        // 使用「滚动数组思想」把空间复杂度优化成 O(1)
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // 方法二：矩阵快速幂
    // 递推关系

    // 方法三：通项公式
    // 齐次线性递推
    // 特征方程
}
