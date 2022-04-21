package leet.code.search.depth.first;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ClosedIsland}.
 *
 * @author guangyi
 */
public class ClosedIslandTest {

    @Test(dataProvider = "closedIslandTestData")
    public void closedIsland(int[][] grid, int expected) {
        int result = ClosedIsland.closedIsland(grid);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "closedIslandTestData")
    private static Object[][] closedIslandTestData() {
        return new Object[][]{
                {new int[][]{{1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}},
                        2},
                {new int[][]{{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}},
                        1},
                {new int[][]{{1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 0, 0, 1}, {1, 0, 1, 1, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1}},
                        2},
                {new int[][]{{1,0},{0,1}},
                        0},
                {new int[][]{{1,1,1}},
                        0},
                {new int[][]{{1}},
                        0},
                {new int[][]{{0}},
                        0},
                // 未考虑到
                {new int[][]{{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}},
                        5},
        };
    }
}
