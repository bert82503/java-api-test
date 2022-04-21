package leet.code.search.depth.first;

/**
 * 130. 被围绕的区域
 * <p></p>
 * https://leetcode-cn.com/problems/surrounded-regions/
 * <p></p>
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 提示：
 * * m == board.length
 * * n == board[i].length
 * * 1 <= m, n <= 200
 * * board[i][j] 为 'X' 或 'O'
 * <p></p>
 * 特性：
 * 1. 逆向思维
 * 2. 对所有矩阵的边框格子，寻找岛屿'O'，并标记为未知领域。等完成后，所有非边框的格子'O'都满足条件
 * 3. 将所有的内部陆地标记为水域，回溯将所有的未知领域为陆地
 * 4. 封闭岛屿的变体
 *
 * @author guangyi
 */
public class SurroundedRegions {
    /**
     * 陆地
     */
    private static final char LAND = 'O';
    /**
     * 水域
     */
    private static final char WATER = 'X';
    /**
     * 未知领域
     */
    private static final char UNKNOWN = '#';

    public static void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        // 1.对所有矩阵的边框格子，寻找岛屿，并标记为未知领域
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                // 搜索条件
                if (x == 0 || x == (board.length - 1) ||
                        y == 0 || y == (board[x].length - 1)) {
                    // 矩阵边框
                    if (board[x][y] == LAND) {
                        // 发现岛屿
                        depthFirstSearch(board, x, y);
                    }
                }
            }
        }
        // 2.将所有的内部陆地标记为水域
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == LAND) {
                    board[x][y] = WATER;
                }
            }
        }
        // 3.回溯将所有的未知领域为陆地
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == UNKNOWN) {
                    board[x][y] = LAND;
                }
            }
        }
    }

    private static void depthFirstSearch(char[][] grid, int x, int y) {
        // 终止条件
        if (x < 0 || x == grid.length || y < 0 || y == grid[x].length) {
            // 边界保护
            return;
        }
        char ch = grid[x][y];
        if (ch == WATER) {
            // 遇到水
            return;
        } else if (ch == UNKNOWN) {
            // 避免重复搜索与计算
            return;
        }
        // 发现陆地
        // 将陆地标记为未知领域，避免重复搜索与计算
        grid[x][y] = UNKNOWN;
        depthFirstSearch(grid, x - 1, y);
        depthFirstSearch(grid, x + 1, y);
        depthFirstSearch(grid, x, y - 1);
        depthFirstSearch(grid, x, y + 1);
    }
}
