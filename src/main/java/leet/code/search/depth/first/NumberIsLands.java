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

    /**
     * '0'（水）
     */
    private static final char WATER = '0';
    /**
     * '1'（陆地）
     */
    private static final char LAND = '1';

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 岛屿的数量
        int islandNum = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                // 搜索条件
                if (grid[x][y] == LAND) {
                    // 发现陆地
                    islandNum += 1;
                    depthFirstSearch(grid, x, y);
                }
            }
        }
        return islandNum;
    }

    /**
     * 深度遍历搜索。
     */
    private static void depthFirstSearch(char[][] grid, int x, int y) {
        // 终止条件
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length) {
            // 边界保护
            return;
        }
        if (grid[x][y] == WATER) {
            // 遇到水
            return;
        }
        // 发现陆地
        grid[x][y] = WATER;
        // 继续向四周查找相邻连接的陆地
        depthFirstSearch(grid, x, y - 1);
        depthFirstSearch(grid, x, y + 1);
        depthFirstSearch(grid, x - 1, y);
        depthFirstSearch(grid, x + 1, y);
    }
}
