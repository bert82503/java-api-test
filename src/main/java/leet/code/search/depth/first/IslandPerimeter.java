package leet.code.search.depth.first;

/**
 * 463. 岛屿的周长
 * <p></p>
 * https://leetcode-cn.com/problems/island-perimeter/
 * <p></p>
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 *
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。
 * 整个网格被水完全包围，但其中恰好有一个岛屿（一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。
 * 计算这个岛屿的周长。
 * <p></p>
 * 认识问题
 * 1. 一个二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 2. 格子 水平和垂直 方向相连（对角线方向不相连）。上下左右格子为相连
 * 3. 恰好有一个岛屿（一个或多个表示陆地的格子相连组成的岛屿）。只有一个解
 * 4. 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 5. 格子是边长为 1 的正方形。
 * 6. 目标：计算这个岛屿的周长。
 *
 * 规律/规则：
 * 1. 岛屿的周长，可等价转换为陆地与水域相邻的数量
 * <p></p>
 * 特性：
 * 1. 二维整数数组，只包含 0 或 1 元素
 * 2. 岛屿，一个或多个表示陆地的格子相连组成
 * 3. 变体，计算岛屿的周长
 *
 * @author guangyi
 */
public class IslandPerimeter {
    /**
     * 0 表示水域
     */
    private static final int WATER = 0;
    /**
     * 1 表示陆地
     */
    private static final int LAND = 1;
    /**
     * 未知领域
     */
    private static final int UNKNOWN = -1;

    /**
     * 岛屿的周长。
     *
     * 规律/规则：
     * 1. 岛屿的周长，可等价转换为陆地与水域相邻的数量
     * <p></p>
     * 特性：
     * 1. 二维整数数组，只包含 0 或 1 元素
     * 2. 岛屿，一个或多个表示陆地的格子相连组成
     * 3. 变体，计算岛屿的周长
     *
     * @param grid 二维整数数组
     * @return 返回岛屿的周长
     */
    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                // 搜索条件
                if (grid[x][y] == LAND) {
                    // 发现岛屿
                    // 返回岛屿的周长
                    return depthFirstSearch(grid, x, y);
                }
            }
        }
        // 全是水域
        return 0;
    }

    /**
     * 基于深度遍历搜索，计算岛屿的周长。
     */
    private static int depthFirstSearch(int[][] grid, int x, int y) {
        // 终止条件
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length) {
            // 边界保护
            return 1;
        }
        int num = grid[x][y];
        if (num == WATER) {
            // 遇到水
            return 1;
        } else if (num == UNKNOWN) {
            // 避免重复搜索与计算
            return 0;
        }
        // 发现陆地
        // 将陆地标记为未知领域，避免重复搜索与计算
        grid[x][y] = UNKNOWN;
        // 继续向四周查找相邻连接的陆地
        return depthFirstSearch(grid, x - 1, y) +
                depthFirstSearch(grid, x + 1, y) +
                depthFirstSearch(grid, x, y - 1) +
                depthFirstSearch(grid, x, y + 1);
    }
}
