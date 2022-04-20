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
 * 特征：
 * 1. 坐标系的x轴，类似有序单调递增的整数数组
 * 2. 变形：一个近似目标值，目标值和下标一样
 *
 * @author guangyi
 */
public class SqrtX {

    /**
     * x 的平方根
     * <p></p>
     * 特征：
     * 1. 坐标系的x轴，类似有序单调递增的整数数组
     * 2. 变形：一个近似目标值，目标值和下标一样
     *
     * @param x 坐标系的x轴
     */
    public static int mySqrt(int x) {
        int start = 0;
        int end = x;
        // 二分查找
        while (start <= end) {
            int mid = (start + end) / 2;
            // 两个整数相乘可能存在越界为长整数，未考虑到
            long square = (long) mid * mid;
            if (square == x) {
                // 发现目标
                return mid;
            } else if (square > x) {
                // 向左低区间查找目标
                end = mid - 1;
            } else {
                // 向右高区间查找目标
                start = mid + 1;
            }
        }
        // 结果只保留 整数部分 ，小数部分将被 舍去
        return start - 1;
    }
}
