package leet.code.search.binary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link BinarySearchSolution}.
 *
 * @author guangyi
 * @since 2021-12-26
 */
public class BinarySearchSolutionTest {

    @Test(dataProvider = "searchTestData")
    public void search(int[] numbers, int target, int expectedIndex) {
        int index = BinarySearchSolution.search(numbers, target);
        assertThat(index).isEqualTo(expectedIndex);
    }

    @DataProvider(name = "searchTestData")
    private static Object[][] searchTestData() {
        return new Object[][]{
                // 偶数个
                {new int[]{-1, 0, 3, 5, 9, 12}, 9, 4},
                {new int[]{-1, 0, 3, 5, 9, 12}, 12, 5},
                {new int[]{-1, 0, 3, 5, 9, 12}, 5, 3},
                {new int[]{-1, 0, 3, 5, 9, 12}, 3, 2},
                {new int[]{-1, 0, 3, 5, 9, 12}, 0, 1},
                {new int[]{-1, 0, 3, 5, 9, 12}, -1, 0},
                // 未找到
                {new int[]{-1, 0, 3, 5, 9, 12}, 13, -1},
                {new int[]{-1, 0, 3, 5, 9, 12}, 10, -1},
                {new int[]{-1, 0, 3, 5, 9, 12}, 7, -1},
                {new int[]{-1, 0, 3, 5, 9, 12}, 2, -1},
                {new int[]{-1, 0, 3, 5, 9, 12}, -3, -1},
                // 奇数个
                {new int[]{3}, 3, 0},
                {new int[]{3}, 5, -1},
                {new int[]{3}, 1, -1},
                {new int[]{1, 5}, 1, 0},
                {new int[]{1, 5}, 5, 1},
                {new int[]{1, 5}, 0, -1},
                {new int[]{1, 5}, 3, -1},
                {new int[]{1, 5}, 7, -1},
                {new int[]{1, 3, 5}, 1, 0},
                {new int[]{1, 3, 5}, 3, 1},
                {new int[]{1, 3, 5}, 5, 2},
                {new int[]{1, 3, 5}, 0, -1},
                {new int[]{1, 3, 5}, 2, -1},
                {new int[]{1, 3, 5}, 4, -1},
                {new int[]{1, 3, 5}, 6, -1},
        };
    }
}
