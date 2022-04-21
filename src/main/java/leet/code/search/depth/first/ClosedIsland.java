package leet.code.search.depth.first;

/**
 * 1254. 统计封闭岛屿的数目
 * <p></p>
 * https://leetcode-cn.com/problems/number-of-closed-islands/
 * <p></p>
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。
 * 岛是由最大的4个方向连通的 0 组成的群，
 * 封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 * <p>
 * 请返回 封闭岛屿 的数目。
 *
 * 规律/规则：
 * 1. 搜到岛屿到边界时，则说明这个岛屿不是封闭岛屿
 * <p></p>
 * 特性：
 * 1. 二维整数数组，只包含 0 或 1 元素
 * 2. 封闭岛，一个 完全 由1包围（左、上、右、下）的岛
 * 3. 岛屿数量的变体，计算封闭岛屿的数目
 *
 * @author guangyi
 */
public class ClosedIsland {
    /**
     * 1 表示水域
     */
    private static final int WATER = 1;
    /**
     * 0 表示陆地
     */
    private static final int LAND = 0;
    /**
     * 未知领域
     */
    private static final int UNKNOWN = -1;

    /**
     * 封闭岛屿的数目
     */
    private static int islandNum;

    public static int closedIsland(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        // 封闭岛屿的数目
        int closedIslandNum = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                // 搜索条件
                if (grid[x][y] == LAND) {
                    // 发现岛屿
                    islandNum = 1;
                    depthFirstSearch(grid, x, y);
                    closedIslandNum += islandNum;
                }
            }
        }
        return closedIslandNum;
    }

    /**
     * 基于深度遍历搜索，判断是否为封闭岛屿。
     */
    private static void depthFirstSearch(int[][] grid, int x, int y) {
        // 终止条件
        if ((x < 0 || x == grid.length || y < 0 || y == grid[x].length)) {
            // 边界保护
            // 不是封闭岛屿
            islandNum = 0;
            return;
        }
        int num = grid[x][y];
        if (num == WATER) {
            // 遇到水
            return;
        } else if (num == UNKNOWN) {
            // 避免重复搜索与计算
            return;
        }
        // 发现陆地
        // 将陆地标记为未知领域，避免重复搜索与计算
        grid[x][y] = UNKNOWN;
        // 继续向四周查找相邻连接的陆地
        depthFirstSearch(grid, x - 1, y);
        depthFirstSearch(grid, x + 1, y);
        depthFirstSearch(grid, x, y - 1);
        depthFirstSearch(grid, x, y + 1);
    }
}
