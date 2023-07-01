package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SortedSquares}.
 *
 * @author guangyi
 */
public class SortedSquaresTest {

    @Test(dataProvider = "sortedSquaresTestData")
    public void sortedSquares(int[] nums, int[] expected) {
        int[] result = SortedSquares.sortedSquares(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "sortedSquaresTestData")
    private static Object[][] sortedSquaresTestData() {
        return new Object[][]{
                {new int[]{-4,-1,0,3,10}, new int[]{0,1,9,16,100}},
                {new int[]{-7,-3,2,3,11}, new int[]{4,9,9,49,121}},
                {new int[]{0,1,2,3}, new int[]{0,1,4,9}},
                {new int[]{-3,-2,-1,0}, new int[]{0,1,4,9}},
                {new int[]{1}, new int[]{1}},
//                {new int[]{}, new int[]{}},
        };
    }
}
