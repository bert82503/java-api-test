package leet.code.search.depth.first;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link NumberIsLands}.
 *
 * @author guangyi
 */
public class NumberIsLandsTest {

    @Test(dataProvider = "numIslandsTestData")
    public void numIslands(char[][] grid, int expected) {
        int result = NumberIsLands.numIslands(grid);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "numIslandsTestData")
    private static Object[][] numIslandsTestData() {
        return new Object[][]{
                {new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}},
                        1},
                {new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}},
                        3},
                {new char[][]{{'1','0'},{'0','1'}},
                        2},
                {new char[][]{{'0','1'},{'1','0'}},
                        2},
                {new char[][]{{'0','1'}},
                        1},
                {new char[][]{{'1','0'}},
                        1},
                {new char[][]{{'1'}},
                        1},
                {new char[][]{{'0'}},
                        0},
        };
    }
}
