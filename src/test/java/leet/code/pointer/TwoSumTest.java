package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link TwoSum}.
 *
 * @author guangyi
 */
public class TwoSumTest {

    @Test(dataProvider = "twoSumTestData")
    public void twoSum(int[] numbers, int target, int[] expected) {
        int[] result = TwoSum.twoSum(numbers, target);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "twoSumTestData")
    private static Object[][] twoSumTestData() {
        return new Object[][]{
                {new int[]{2,7,11,15}, 9, new int[]{1,2}},
                {new int[]{2,3,4}, 6, new int[]{1,3}},
                {new int[]{-1,0}, -1, new int[]{1,2}},
        };
    }
}
