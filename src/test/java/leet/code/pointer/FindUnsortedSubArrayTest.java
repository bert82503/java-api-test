package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link FindUnsortedSubArray}.
 *
 * @author guangyi
 */
public class FindUnsortedSubArrayTest {

    @Test(dataProvider = "findUnsortedSubarrayTestData")
    public void findUnsortedSubarray(int[] nums, int expected) {
        int result = FindUnsortedSubArray.findUnsortedSubarray(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "findUnsortedSubarrayTestData")
    private static Object[][] findUnsortedSubarrayTestData() {
        return new Object[][]{
                {new int[]{2,1},
                        2},
                {new int[]{1,2,3,3,3},
                        0},
                {new int[]{2,6,4,8,10,9,15},
                        5},
                {new int[]{1,2,3,4},
                        0},
                {new int[]{1},
                        0},
        };
    }
}
