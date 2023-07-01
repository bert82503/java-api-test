package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MoveZeroes}.
 *
 * @author guangyi
 */
public class MoveZeroesTest {

    @Test(dataProvider = "moveZeroesTestData")
    public void moveZeroes(int[] nums, int[] expected) {
        MoveZeroes.moveZeroes(nums);
        assertThat(nums).isEqualTo(expected);
    }

    @DataProvider(name = "moveZeroesTestData")
    private static Object[][] moveZeroesTestData() {
        return new Object[][]{
                {new int[]{0,1,0,3,12}, new int[]{1,3,12,0,0}},
                {new int[]{1,0,0,3,12}, new int[]{1,3,12,0,0}},
                {new int[]{1,1,0}, new int[]{1,1,0}},
                {new int[]{1,0,1,0}, new int[]{1,1,0,0}},
                {new int[]{1,1,1}, new int[]{1,1,1}},
                {new int[]{0,0,1,2,3}, new int[]{1,2,3,0,0}},
                {new int[]{0,0,1,2}, new int[]{1,2,0,0}},
                {new int[]{0,0,1}, new int[]{1,0,0}},
                {new int[]{0,1}, new int[]{1,0}},
                {new int[]{0,1,0,1}, new int[]{1,1,0,0}},
                {new int[]{0}, new int[]{0}},
                {new int[]{1}, new int[]{1}},
//                {new int[]{}, new int[]{}},
        };
    }
}
