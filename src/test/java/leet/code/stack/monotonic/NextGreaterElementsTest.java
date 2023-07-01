package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link NextGreaterElements}.
 *
 * @author guangyi
 */
public class NextGreaterElementsTest {

    @Test(dataProvider = "nextGreaterElementsTestData")
    public void nextGreaterElements(int[] nums, int[] expected) {
        int[] result = NextGreaterElements.nextGreaterElements(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "nextGreaterElementsTestData")
    private static Object[][] nextGreaterElementsTestData() {
        return new Object[][]{
                {new int[]{1,2,1},
                        new int[]{2,-1,2}},
                {new int[]{1,2,3,4,3},
                        new int[]{2,3,4,-1,4}},
                {new int[]{4,3,2,1},
                        new int[]{-1,4,4,4}},
                {new int[]{1},
                        new int[]{-1}},
        };
    }
}
