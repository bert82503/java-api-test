package leet.code.bit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SingleNumbers}.
 *
 * @author guangyi
 */
public class SingleNumbersTest {

    @Test(dataProvider = "singleNumbersTestData")
    public void singleNumbers(int[] nums, int[] expected) {
        int[] result = SingleNumbers.singleNumbers(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "singleNumbersTestData")
    private static Object[][] singleNumbersTestData() {
        return new Object[][]{
                {new int[]{4,1,4,6},
                        new int[]{1,6}},
                {new int[]{1,2,10,4,1,4,3,3},
                        new int[]{10,2}},
                {new int[]{1,2},
                        new int[]{1,2}},
        };
    }
}
