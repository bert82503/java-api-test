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
 * 认识问题
 * 1. 一个非空二维数组，由 0 和 1 组成。
 * 2. 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。(由上下左右的陆地相连)
 * 3. 假设 grid 的四个边缘都被 0（代表水）包围着。
 * 3. 目标：计算最大的岛屿面积。如果没有岛屿，则返回面积为 0 。(岛屿面积等价于岛屿陆地的数量)
 * <p></p>
 * 特性：
 * 1. 二维整数数组，只包含 0 或 1 元素
 * 2. 岛屿，一个或多个表示陆地的格子相连组成
 * 3. 变体：求全局最优解
 * 4. 变体：统计岛屿陆地数量，返回最大的岛屿面积
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
    /**
     * 未知领域
     */
    private static final int UNKNOWN = -1;

    /**
     * 岛屿的最大面积。
     * <p></p>
     * 特性：
     * 1. 求全局最优解
     * 2. 变体：统计岛屿陆地数量，返回最大的岛屿面积
     *
     * @param grid 二维整数数组
     * @return 返回最大的岛屿面积
     */
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
                    // 发现岛屿
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
     * 基于深度遍历搜索，计算岛屿面积。
     */
    private static int depthFirstSearch(int[][] grid, int x, int y) {
        // 终止条件
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length) {
            // 边界保护
            return 0;
        }
        int num = grid[x][y];
        if (num == WATER) {
            // 遇到水
            return 0;
        } else if (num == UNKNOWN) {
            // 避免重复搜索与计算
            return 0;
        }
        // 发现陆地
        // 将陆地标记为未知领域，避免重复搜索与计算
        grid[x][y] = UNKNOWN;
        // 继续向四周查找相邻连接的陆地
        return 1 +
                depthFirstSearch(grid, x - 1, y) +
                depthFirstSearch(grid, x + 1, y) +
                depthFirstSearch(grid, x, y - 1) +
                depthFirstSearch(grid, x, y + 1)
                ;
    }
}
