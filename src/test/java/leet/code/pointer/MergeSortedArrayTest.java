package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MergeSortedArray}.
 *
 * @author guangyi
 */
public class MergeSortedArrayTest {

    @Test(dataProvider = "mergeTestData")
    public void merge(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        MergeSortedArray.merge(nums1, m, nums2, n);
        assertThat(nums1).isEqualTo(expected);
    }

    @DataProvider(name = "mergeTestData")
    private static Object[][] mergeTestData() {
        return new Object[][]{
                // 升序
                {new int[]{1,2,3,0,0,0}, 3, new int[]{4,5,6}, 3,
                        new int[]{1,2,3,4,5,6}},
                // 降序
                {new int[]{4,5,6,0,0,0}, 3, new int[]{1,2,3}, 3,
                        new int[]{1,2,3,4,5,6}},
                // 交叉
                {new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3,
                        new int[]{1,2,2,3,5,6}},
                // 边界
                {new int[]{0}, 0, new int[]{1}, 1,
                        new int[]{1}},
                {new int[]{1}, 1, new int[]{}, 0,
                        new int[]{1}},
//                {new int[]{}, , new int[]{}, ,
//                        new int[]{}},
        };
    }
}
