package leet.code.bit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SingleNumber}.
 *
 * @author guangyi
 */
public class SingleNumberTest {

    @Test(dataProvider = "singleNumberTestData")
    public void singleNumber(int[] nums, int expected) {
        int result = SingleNumber.singleNumber(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "singleNumberTestData")
    private static Object[][] singleNumberTestData() {
        return new Object[][]{
                {new int[]{2,2,1},
                        1},
                {new int[]{4,1,2,1,2},
                        4},
                {new int[]{1},
                        1},
                {new int[]{0},
                        0},
        };
    }
}
