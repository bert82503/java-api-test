package leet.code.search.depth.first;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SurroundedRegions}.
 *
 * @author guangyi
 */
public class SurroundedRegionsTest {

    @Test(dataProvider = "solveTestData")
    public void solve(char[][] board, char[][] expected) {
        SurroundedRegions.solve(board);
        assertThat(board).isDeepEqualTo(expected);
    }

    @DataProvider(name = "solveTestData")
    private static Object[][] solveTestData() {
        return new Object[][]{
                {new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}},
                        new char[][]{{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'}}},
                {new char[][]{{'X','O'},{'O','O'}},
                        new char[][]{{'X','O'},{'O','O'}}},
                {new char[][]{{'X','X'},{'O','X'}},
                        new char[][]{{'X','X'},{'O','X'}}},
                {new char[][]{{'X'}},
                        new char[][]{{'X'}}},
                {new char[][]{{'O'}},
                        new char[][]{{'O'}}},
        };
    }
}
