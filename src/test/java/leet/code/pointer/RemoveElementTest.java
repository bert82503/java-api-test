package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link RemoveElement}.
 *
 * @author guangyi
 */
public class RemoveElementTest {

    @Test(dataProvider = "removeElementTestData")
    public void removeElement(int[] nums, int val, int expected) {
        int result = RemoveElement.removeElement(nums, val);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "removeElementTestData")
    private static Object[][] removeElementTestData() {
        return new Object[][]{
                {new int[]{3,2,2,3}, 3, 2},
                {new int[]{3,2,3,2,3}, 3, 2},
                {new int[]{3,2,2,2,3}, 3, 3},
                {new int[]{3,2,3,2,3,2}, 3, 3},
                {new int[]{3,2,3,2,3,2}, 2, 3},
                {new int[]{3,3,3,2,2,2}, 3, 3},
                {new int[]{3,3,3,2,2,2}, 2, 3},
                {new int[]{3,3,3,3,2,2,2}, 3, 3},
                {new int[]{3,3,3,2,2,2,2}, 3, 4},
                {new int[]{0,1,2,2,3,0,4,2}, 2, 5},
                {new int[]{0,1,2,2,2,0,4,2}, 2, 4},
                {new int[]{1,2,3,4,5}, 0, 5},
                {new int[]{1}, 2, 1},
                // 未考虑到
                {new int[]{1}, 1, 0},
//                {new int[]{}, , },
        };
    }
}
