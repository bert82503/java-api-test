package leet.code.sum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link FourSum}.
 *
 * @author guangyi
 */
public class FourSumTest {

    @Test(dataProvider = "fourSumTestData")
    public void fourSum(int[] nums, int target, List<List<Integer>> expected) {
        List<List<Integer>> result = FourSum.fourSum(nums, target);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "fourSumTestData")
    private static Object[][] fourSumTestData() {
        return new Object[][]{
                {new int[]{2,2,2,2,2}, 8, Lists.<List<Integer>>newArrayList(
                        Arrays.asList(2,2,2,2))},
                {new int[]{1,2,-2,-1}, 0, Lists.<List<Integer>>newArrayList(
                        Arrays.asList(-2,-1,1,2))},
                {new int[]{1,2,-2,-1}, 1, Collections.emptyList()},
                // 无解
                {new int[]{0,0,0}, 0, Collections.emptyList()},
                {new int[]{0,0}, 0, Collections.emptyList()},
                {new int[]{0}, 0, Collections.emptyList()},
                {new int[]{}, 0, Collections.emptyList()},
        };
    }
}
