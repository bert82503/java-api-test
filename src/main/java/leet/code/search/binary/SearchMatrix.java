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
     * <p></p>
     * 题解：搜索二维矩阵 II
     * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-so-9hcx/
     * <p></p>
     * 方法三：Z 字形查找
     * <pre>
     * 思路与算法
     * 我们可以从矩阵 matrix 的右上角 (0,n-1) 进行搜索。
     * 在每一步的搜索过程中，如果我们位于位置 (x,y)，
     * 那么我们希望在以 matrix 的左下角为左下角、以 (x,y) 为右上角的矩阵中进行搜索，
     * 即行的范围为 [x,m-1]，列的范围为 [0,y]：
     * * 如果 matrix[x,y]=target，说明搜索完成；
     * * 如果 matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，
     *   所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1；
     * * 如果 matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中，
     *   所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
     * 在搜索的过程中，如果我们超出了矩阵的边界，那么说明矩阵中不存在 target。
     * </pre>
     *
     * 题解：详细通俗的思路分析，多解法
     * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-4/
     * <pre>
     * 解法二
     * 参考 这里，需要很敏锐的观察力了。
     * 数组从左到右和从上到下都是升序的，如果从右上角出发开始遍历呢？
     * 会发现每次都是向左数字会变小，向下数字会变大，有点和二分查找树相似。
     * 二分查找树的话，是向左数字变小，向右数字变大。
     * 所以我们可以把 target 和当前值比较：
     * 如果 target 的值大于当前值，那么就向下走。
     * 如果 target 的值小于当前值，那么就向左走。
     * 如果相等的话，直接返回 true 。
     *
     * 也可以换个角度思考。
     * 如果 target 的值小于当前值，也就意味着当前值所在的列肯定不会存在 target 了，可以把当前列去掉，从新的右上角的值开始遍历。
     * 同理，如果 target 的值大于当前值，也就意味着当前值所在的行肯定不会存在 target 了，可以把当前行去掉，从新的右上角的值开始遍历。
     *
     * 时间复杂度就是每个节点最多遍历一遍了，O(m + n)。
     *
     * 总结
     * 看到有序数组第一反应就是二分了，也就是解法一。
     * 解法二的话，从右上角开始遍历的想法很妙。
     *
     * </pre>
     *
     * @param matrix 二维整数数组
     * @param target 目标值
     * @return 返回目标值是否存在数组中
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // 行数
        int m = matrix.length;
        // 列数
        int n = matrix[0].length;
        int row = 0;
        int col = n - 1;
        while (row < m && col >= 0) {
            int num = matrix[row][col];
            if (num == target) {
                // 发现目标
                return true;
            } else if (target > num) {
                // target > num
                // 其下方元素较大
                // 向下走
                row++;
            } else {
                // 其左方元素较小
                // 向左走
                col--;
            }
        }
        return false;
    }

    /**
     * 在二维整数矩阵中查找目标值。
     *
     * @param matrix 二维整数数组
     * @param target 目标值
     * @return 返回目标值是否存在数组中
     */
    public static boolean searchMatrix_BinarySearch(int[][] matrix, int target) {
        for (int[] row : matrix) {
            // 二分查找
            int index = binarySearch(row, target);
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
     * @param nums   整数数组
     * @param target 目标值
     * @return 目标值所在的数组下标
     */
    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
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
