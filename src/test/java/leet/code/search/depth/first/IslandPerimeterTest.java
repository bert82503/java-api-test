package leet.code.search.depth.first;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link IslandPerimeter}.
 *
 * @author guangyi
 */
public class IslandPerimeterTest {

    @Test(dataProvider = "islandPerimeterTestData")
    public void islandPerimeter(int[][] grid, int expected) {
        int result = IslandPerimeter.islandPerimeter(grid);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "islandPerimeterTestData")
    private static Object[][] islandPerimeterTestData() {
        return new Object[][]{
                {new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}},
                        16},
                {new int[][]{{1,1}},
                        6},
                {new int[][]{{1}},
                        4},
                {new int[][]{{1,0}},
                        4},
        };
    }
}
