package leet.code.search.binary;

/**
 * 367. 有效的完全平方数
 * <p></p>
 * https://leetcode-cn.com/problems/valid-perfect-square/
 * <pre>
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 * 提示：
 * 1 <= num <= 2^31 - 1
 * </pre>
 *
 * @author guangyi
 */
public class PerfectSquare {

    /**
     * 有效的完全平方数
     * https://leetcode-cn.com/problems/valid-perfect-square/solution/you-xiao-de-wan-quan-ping-fang-shu-by-le-wkee/
     * <pre>
     * 方法三：二分查找
     *
     * 方法四：牛顿迭代法
     * 前置知识
     * 牛顿迭代法是一种近似求解方程（近似求解函数零点）的方法。
     * 其本质是借助泰勒级数，从初始值开始快速向函数零点逼近。
     * </pre>
     *
     * 一题双解 :「二分」&「数学」
     * https://leetcode-cn.com/problems/valid-perfect-square/solution/gong-shui-san-xie-yi-ti-shuang-jie-er-fe-g5el/
     * <pre>
     * 二分
     * 假如答案为 ans，那么以 ans 为分割点的数轴上具有二段性：
     * * 小于 ans 的一段 x 必然不满足 x * x ≥ num；
     * * 大于等于 ans 的一段 xx 必然满足 x * x ≥ num。
     * 因此可以通过「二分」来找到分割点 ans。
     *
     * 数学
     * 我们知道对于一个完全平方数而言，可以写成如下形式：
     *     num = n^2 = 1+3+5+...+(2*n-1)
     * 因此另外一种做法是对 num 进行不断的奇数试减，如果最终能够减到 0，
     * 说明 num 可展开成如 1+3+5+...+(2*n-1) 的形式，num 为完全平方数。
     * </pre>
     */
    public static boolean isPerfectSquare_DoublePointer(int num) {
        // 二分查找
        int left = 0;
        int right = num;
        while (left <= right) {
            int mid = (left + right) >> 1;
            // 完全平方数
            long square = (long) mid * mid;
            if (square == num) {
                return true;
            } else if (square > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * 一题双解 :「二分」&「数学」
     * https://leetcode-cn.com/problems/valid-perfect-square/solution/gong-shui-san-xie-yi-ti-shuang-jie-er-fe-g5el/
     * <pre>
     * 数学
     * 我们知道对于一个完全平方数而言，可以写成如下形式：
     *     num = n^2 = 1+3+5+...+(2*n-1)
     * 因此另外一种做法是对 num 进行不断的奇数试减，如果最终能够减到 0，
     * 说明 num 可展开成如 1+3+5+...+(2*n-1) 的形式，num 为完全平方数。
     * </pre>
     */
    public static boolean isPerfectSquare(int num) {
        int x = 1;
        while (num > 0) {
            num -= x;
            x += 2;
        }
        return num == 0;
    }
}
