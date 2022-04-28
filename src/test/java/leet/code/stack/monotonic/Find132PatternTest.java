package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link Find132Pattern}.
 *
 * @author guangyi
 */
public class Find132PatternTest {

    @Test(dataProvider = "find132patternTestData")
    public void find132pattern(int[] nums, boolean expected) {
        boolean result = Find132Pattern.find132pattern(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "find132patternTestData")
    private static Object[][] find132patternTestData() {
        return new Object[][]{
                {new int[]{3,5,0,3,4}, true},
                {new int[]{3,1,4,2}, true},
                {new int[]{-1,3,2,0}, true},
                {new int[]{1,2,3,4}, false},
                // 未考虑到
                {new int[]{-2,1,1}, false},
        };
    }
}
