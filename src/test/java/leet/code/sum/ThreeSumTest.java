package leet.code.sum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ThreeSum}.
 *
 * @author guangyi
 */
public class ThreeSumTest {

    @Test(dataProvider = "threeSumTestData")
    public void threeSum(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> result = ThreeSum.threeSum(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "threeSumTestData")
    private static Object[][] threeSumTestData() {
        return new Object[][]{
                {new int[]{-1,0,1,2,-1,-4}, Lists.newArrayList(
                        Arrays.asList(-1,-1,2), Arrays.asList(-1,0,1))},
                {new int[]{-1,0,0,0,1,2}, Lists.newArrayList(
                        Arrays.asList(-1,0,1), Arrays.asList(0,0,0))},
                {new int[]{-1,0,0,1,2}, Lists.<List<Integer>>newArrayList(
                        Arrays.asList(-1,0,1))},
                {new int[]{-1,0,0,0,2}, Lists.<List<Integer>>newArrayList(
                        Arrays.asList(0,0,0))},
                {new int[]{0,0,0}, Lists.<List<Integer>>newArrayList(
                        Arrays.asList(0,0,0))},
                // 无解
                {new int[]{1,2,-2,-1}, Collections.emptyList()},
                {new int[]{0,0}, Collections.emptyList()},
                {new int[]{0}, Collections.emptyList()},
                {new int[]{}, Collections.emptyList()},
        };
    }
}
