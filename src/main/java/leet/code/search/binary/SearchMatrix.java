package leet.code.search.binary;

/**
 * 240. 搜索二维矩阵 II
 * <p></p>
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * <pre>
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。
 * 该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 * </pre>
 *
 * @author guangyi
 */
public class SearchMatrix {
    /**
     * 在二维整数矩阵中查找目标值。
     *
     * @param matrix 二维整数数组
     * @param target 目标值
     * @return 返回目标值是否存在数组中
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法二：二分查找
     * <pre>
     * 思路与算法
     * 由于矩阵 matrix 中每一行的元素都是升序排列的，因此我们可以对每一行都使用一次二分查找，
     * 判断 target 是否在该行中，从而判断 target 是否出现。
     * </pre>
     * <pre>
     * 复杂度分析
     * 时间复杂度：O(m log n)。对一行使用二分查找的时间复杂度为 O(log n)，最多需要进行 mm 次二分查找。
     * 空间复杂度：O(1)。
     * </pre>
     *
     * @param row    整数数组
     * @param target 目标值
     * @return 目标值所在的数组下标
     */
    private static int search(int[] row, int target) {
        int left = 0;
        int right = row.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int num = row[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                right = mid - 1;
            } else {
                // num < target
                left = mid + 1;
            }
        }
        return -1;
    }
}
