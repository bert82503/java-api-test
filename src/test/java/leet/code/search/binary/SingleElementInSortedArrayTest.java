package leet.code.search.binary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SingleElementInSortedArray}.
 *
 * @author guangyi
 */
public class SingleElementInSortedArrayTest {

    @Test(dataProvider = "singleNonDuplicateTestData")
    public void singleNonDuplicate(int[] nums, int expectedNum) {
        int result = SingleElementInSortedArray.singleNonDuplicate(nums);
        assertThat(result).isEqualTo(expectedNum);
    }

    @DataProvider(name = "singleNonDuplicateTestData")
    private static Object[][] singleNonDuplicateTestData() {
        return new Object[][]{
                {new int[]{1,1,2,3,3,4,4,8,8}, 2},
                {new int[]{3,3,7,7,10,11,11}, 10},
                {new int[]{0,0,3,5,5}, 3},
                {new int[]{0,1,1,2,2,5,5}, 0},
                {new int[]{0,0,1}, 1},
                {new int[]{0,1,1}, 0},
                {new int[]{0}, 0},
        };
    }
}
