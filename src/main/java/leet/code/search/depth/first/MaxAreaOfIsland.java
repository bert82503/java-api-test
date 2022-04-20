package leet.code.search.depth.first;

/**
 * 剑指 Offer II 105. 岛屿的最大面积
 * <p></p>
 * https://leetcode-cn.com/problems/ZL6zAn/
 * <p></p>
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * 提示：
 * * m == grid.length
 * * n == grid[i].length
 * * 1 <= m, n <= 50
 * * grid[i][j] is either 0 or 1
 * <p></p>
 * 特性：
 * 1. 求全局最优解
 * 2. 变体：统计岛屿陆地数量，返回最大的岛屿面积
 *
 * @author guangyi
 */
public class MaxAreaOfIsland {

    /**
     * 0（代表水）
     */
    private static final int WATER = 0;
    /**
     * 1 (代表土地)，陆地
     */
    private static final int LAND = 1;

    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 最大的岛屿面积
        // 如果没有岛屿，则返回面积为 0 。
        int maxIslandArea = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                // 搜索条件
                if (grid[x][y] == LAND) {
                    // 发现陆地
                    int islandArea = depthFirstSearch(grid, x, y);
                    if (islandArea > maxIslandArea) {
                        maxIslandArea = islandArea;
                    }
                }
            }
        }
        return maxIslandArea;
    }

    /**
     * 深度遍历搜索。
     *
     * @param grid       非空二维数组，用来表示海洋岛屿地图
     * @param x          x坐标维度
     * @param y          y坐标维度
     * @return 返回岛屿面积
     */
    private static int depthFirstSearch(int[][] grid, int x, int y) {
        // 终止条件
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length) {
            // 边界保护
            return 0;
        }
        if (grid[x][y] == WATER) {
            // 遇到水
            return 0;
        }
        // 发现陆地
        grid[x][y] = WATER;
        // 继续向四周查找连接的陆地
        return 1 +
                depthFirstSearch(grid, x, y - 1) +
                depthFirstSearch(grid, x, y + 1) +
                depthFirstSearch(grid, x - 1, y) +
                depthFirstSearch(grid, x + 1, y);
    }
}
