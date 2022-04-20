package leet.code.search.binary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link FindFirstAndLastPositionOfElementInSortedArray}.
 *
 * @author guangyi
 */
public class FindFirstAndLastPositionOfElementInSortedArrayTest {

    @Test(dataProvider = "searchRangeTestData")
    public void searchRange(int[] nums, int target, int[] expected) {
        int[] result = FindFirstAndLastPositionOfElementInSortedArray.searchRange(nums, target);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "searchRangeTestData")
    private static Object[][] searchRangeTestData() {
        return new Object[][]{
                // 发现目标
                {new int[]{5,7,7,8,8,10}, 8, new int[]{3,4}},
                {new int[]{7,7,8,8}, 8, new int[]{2,3}},
                {new int[]{7,7,8,8}, 7, new int[]{0,1}},
                {new int[]{5,7,7,8,9,10}, 8, new int[]{3,3}},
                // 边界，未考虑到
                {new int[]{1}, 1, new int[]{0,0}},
                // 未发现目标
                {new int[]{5,7,7,8,8,10}, 6, new int[]{-1,-1}},
                // 边界
                {new int[]{}, 0, new int[]{-1,-1}},
        };
    }
}
