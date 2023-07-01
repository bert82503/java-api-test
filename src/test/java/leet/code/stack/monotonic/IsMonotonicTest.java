package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link IsMonotonic}.
 *
 * @author guangyi
 */
public class IsMonotonicTest {

    @Test(dataProvider = "isMonotonicTestData")
    public void isMonotonic(int[] nums, boolean expected) {
        boolean result = IsMonotonic.isMonotonic(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "isMonotonicTestData")
    private static Object[][] isMonotonicTestData() {
        return new Object[][]{
                {new int[]{1,2,2,3}, true},
                {new int[]{6,5,4,4}, true},
                {new int[]{2,1}, true},
                {new int[]{1,1}, true},
                {new int[]{1}, true},
                {new int[]{1,3,2}, false},
        };
    }
}
