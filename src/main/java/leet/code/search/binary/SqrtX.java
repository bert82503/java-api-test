package leet.code.search.binary;

/**
 * 69. x 的平方根
 * <p></p>
 * https://leetcode-cn.com/problems/sqrtx/
 * <p></p>
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根。
 * 由于返回类型是整数，结果只保留 整数部分，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p></p>
 * 特性：
 * 1. 坐标系的x轴，类似有序单调递增的整数数组
 * 2. 变体：一个近似目标值[n*n]，目标值和下标一样
 *
 * @author guangyi
 */
public class SqrtX {

    /**
     * x 的平方根
     * <p></p>
     * 特性：
     * 1. 坐标系的x轴，类似有序单调递增的整数数组
     * 2. 变体：一个近似目标值，目标值和下标一样
     */
    public static int mySqrt_DoublePointer(int x) {
        if (x <= 1) {
            return x;
        }

        int left = 0;
        int right = x;
        // 二分查找
        while (left <= right) {
            int mid = (left + right) / 2;
            // 两个整数相乘可能存在越界为长整数，未考虑到
            long square = (long) mid * mid;
            if (square == x) {
                // 发现目标
                return mid;
            } else if (square > x) {
                // 向左低区间查找目标
                right = mid - 1;
            } else if (square < x) {
                // 向右高区间查找目标
                left = mid + 1;
            }
        }
        // 结果只保留 整数部分 ，小数部分将被 舍去
        return right;
    }

    /**
     * x 的平方根
     * https://leetcode-cn.com/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/
     * <pre>
     * 方法二：二分查找
     * 由于 x 平方根的整数部分 ans 是满足 k^2 ≤ x 的最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。
     *
     * 方法三：牛顿迭代
     * 思路
     * 牛顿迭代法是一种可以用来快速求解函数零点的方法。
     * 为了叙述方便，我们用 C 表示待求出平方根的那个整数。显然，C 的平方根就是函数
     *     y = f(x) = x^2−C
     * 牛顿迭代法的本质是借助泰勒级数，从初始值开始快速向零点逼近。
     * 细节
     * * 为什么选择 x0 = C 作为初始值？
     * * 迭代到何时才算结束？
     * * 如何通过迭代得到的近似零点得出最终的答案？
     * </pre>
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        double C = x;
        double x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }
}
