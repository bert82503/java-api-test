package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link NextGreaterElement}.
 *
 * @author guangyi
 */
public class NextGreaterElementTest {

    @Test(dataProvider = "nextGreaterElementTestData")
    public void nextGreaterElement(int[] nums1, int[] nums2, int[] expected) {
        int[] result = NextGreaterElement.nextGreaterElement(nums1, nums2);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "nextGreaterElementTestData")
    private static Object[][] nextGreaterElementTestData() {
        return new Object[][]{
                {new int[]{1,3,5,2,4}, new int[]{6,5,4,3,2,1,7},
                        new int[]{7,7,7,7,7}},
                {new int[]{4,1,2}, new int[]{1,3,4,2},
                        new int[]{-1,3,-1}},
                {new int[]{2,4}, new int[]{1,2,3,4},
                        new int[]{3,-1}},
        };
    }
}
