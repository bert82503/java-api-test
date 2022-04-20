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
 * 2. 变体：一个近似目标值，目标值和下标一样
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
     *
     * @param x 坐标系的x轴
     */
    public static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int left = 0;
        int right = x;
        // 二分查找
        while (left <= right) {
            int mid = (left + right) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                // 发现目标
                return mid;
            } else if (sqrt < mid) {
                // 向左低区间查找目标
                right = mid - 1;
            } else if (sqrt > mid) {
                // 向右高区间查找目标
                left = mid + 1;
            }
        }
        // 结果只保留 整数部分 ，小数部分将被 舍去
//        return left - 1;
        return right;
    }
}
