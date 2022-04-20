package leet.code.search.depth.first;

/**
 * 200. 岛屿数量
 * <p></p>
 * https://leetcode-cn.com/problems/number-of-islands/
 * <p></p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 提示：
 * * m == grid.length
 * * n == grid[i].length
 * * 1 <= m, n <= 300
 * * grid[i][j] 的值为 '0' 或 '1'
 *
 * @author guangyi
 */
public class NumberIsLands {

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int num = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                // 搜索条件
                if (grid[x][y] == '1') {
                    // 发现岛屿陆地
                    num++;
                    dfs(grid, x, y);
                }
            }
        }
        return num;
    }

    /**
     * 深度遍历搜索。
     */
    private static void dfs(char[][] grid, int x, int y) {
        // 终止条件
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            // 边界保护
            return;
        }
        if (grid[x][y] == '0') {
            // 遇到水
            return;
        }
        // 发现岛屿陆地
        grid[x][y] = '0';
        // 继续向四周查找连接的岛屿陆地
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
    }
}
