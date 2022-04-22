package leet.code.search.binary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link FindPeakElement}.
 *
 * @author guangyi
 */
public class FindPeakElementTest {

    @Test(dataProvider = "findPeakElementTestData")
    public void findPeakElement(int[] nums, int expected) {
        int result = FindPeakElement.findPeakElement(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "findPeakElementTestData")
    private static Object[][] findPeakElementTestData() {
        return new Object[][]{
                {new int[]{1,2,3,1},
                        2},
                {new int[]{1,2,1,3,5,6,4},
                        5},
                {new int[]{1,3,2},
                        1},
                {new int[]{1,2,3},
                        2},
                {new int[]{1,2},
                        1},
                {new int[]{2,1},
                        0},
                {new int[]{1},
                        0},
        };
    }
}
