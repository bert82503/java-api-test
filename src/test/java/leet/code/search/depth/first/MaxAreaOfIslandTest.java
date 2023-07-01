package leet.code.search.depth.first;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MaxAreaOfIsland}.
 *
 * @author guangyi
 */
public class MaxAreaOfIslandTest {

    @Test(dataProvider = "maxAreaOfIslandTestData")
    public void maxAreaOfIsland(int[][] grid, int expected) {
        int result = MaxAreaOfIsland.maxAreaOfIsland(grid);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "maxAreaOfIslandTestData")
    private static Object[][] maxAreaOfIslandTestData() {
        return new Object[][]{
                {new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}},
                        6},
                {new int[][]{{1,1},{1,1}},
                        4},
                {new int[][]{{1,1},{0,1}},
                        3},
                {new int[][]{{0,1},{1,1}},
                        3},
                {new int[][]{{1,0},{0,1}},
                        1},
                {new int[][]{{0,1},{1,0}},
                        1},
                {new int[][]{{0,1}},
                        1},
                {new int[][]{{1,0}},
                        1},
                {new int[][]{{1,1,1}},
                        3},
                {new int[][]{{1}},
                        1},
                {new int[][]{{0,0,0,0,0,0,0,0}},
                        0},
                {new int[][]{{0}},
                        0},
        };
    }
}
