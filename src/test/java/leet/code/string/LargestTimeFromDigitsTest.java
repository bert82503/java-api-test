package leet.code.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link LargestTimeFromDigits}.
 *
 * @author guangyi
 */
public class LargestTimeFromDigitsTest {

    @Test(dataProvider = "largestTimeFromDigitsTestData")
    public void largestTimeFromDigits(int[] arr, String expected) {
        String result = LargestTimeFromDigits.largestTimeFromDigits(arr);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "largestTimeFromDigitsTestData")
    private static Object[][] largestTimeFromDigitsTestData() {
        return new Object[][]{
                {new int[]{1,2,3,4},
                        "23:41"},
                {new int[]{0,0,1,0},
                        "10:00"},
                {new int[]{0,0,0,0},
                        "00:00"},
                {new int[]{5,5,5,5},
                        ""},
        };
    }
}
