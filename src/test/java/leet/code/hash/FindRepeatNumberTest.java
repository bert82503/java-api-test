package leet.code.hash;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link FindRepeatNumber}.
 *
 * @author guangyi
 */
public class FindRepeatNumberTest {

    @Test(dataProvider = "findRepeatNumberTestData")
    public void findRepeatNumber(int[] nums, int expected) {
        int result = FindRepeatNumber.findRepeatNumber(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "findRepeatNumberTestData")
    private static Object[][] findRepeatNumberTestData() {
        return new Object[][]{
                {new int[]{2, 3, 1, 0, 2, 5, 3},
                        2},
                {new int[]{0, 1, 2, 3, 4, 5, 6, 3},
                        3},
                {new int[]{0, 1, 2, 3, 4, 5, 6, 4, 0},
                        4},
                {new int[]{1, 1},
                        1},
                {new int[]{0, 1, 2, 3, 4, 5, 6, 0}, 0},
                {new int[]{0, 0},
                        0},
//                {new int[]{}, },
        };
    }
}
