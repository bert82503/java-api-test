package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link RemoveDuplicates}.
 *
 * @author guangyi
 */
public class RemoveDuplicatesTest {

    @Test(dataProvider = "removeDuplicatesTestData")
    public void removeDuplicates(int[] nums, int expected) {
        int result = RemoveDuplicates.removeDuplicates(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "removeDuplicatesTestData")
    private static Object[][] removeDuplicatesTestData() {
        return new Object[][]{
                {new int[]{1,1,2}, 2},
                {new int[]{1,2}, 2},
                {new int[]{0,0,1,1,1,2,2,3,3,4}, 5},
                {new int[]{1,1,1}, 1},
                {new int[]{1,1}, 1},
                {new int[]{1}, 1},
//                {new int[]{}, },
        };
    }
}
