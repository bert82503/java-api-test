package leet.code.search.binary;

/**
 * 441. 排列硬币
 * <p></p>
 * https://leetcode-cn.com/problems/arranging-coins/
 * <p></p>
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。
 * 对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。
 * 阶梯的最后一行 可能 是不完整的。
 *
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 *
 * 注意：每个阶梯标记为1，完整阶梯行 的总行数即为完整阶梯行的总和。
 * <p></p>
 * 特性：
 * 1. 坐标系的x轴，类似有序单调递增的整数数组
 * 2. 变体：一个近似目标值[n*(n+1)/2]，目标值和下标一样
 *
 * @author guangyi
 */
public class ArrangeCoins {

    /**
     * 排列硬币
     *
     * 注意：每个阶梯标记为1，完整阶梯行 的总行数即为完整阶梯行的总和。
     * <p></p>
     * 特性：
     * 1. 坐标系的x轴，类似有序单调递增的整数数组
     * 2. 变体：一个近似目标值[n*(n+1)/2]，目标值和下标一样
     *
     * @param n 坐标系的x轴
     */
    public static int arrangeCoins(int n) {
        if (n <= 1) {
            return n;
        }

        int left = 0;
        int right = n;
        // 二分查找
        while (left <= right) {
            int mid = (left + right) / 2;
            // 每个阶梯标记为1，完整阶梯行 的总行数即为完整阶梯行的总和
            // n*(n+1)/2
            long square = (long) mid * (mid + 1) / 2;
            if (square == n) {
                // 发现目标
                return mid;
            } else if (square > n) {
                // 左半部分，向左低区间查找目标
                right = mid - 1;
            } else if (square < n) {
                // 右半部分，向右高区间查找目标
                left = mid + 1;
            }
        }
        return right;
    }
}
