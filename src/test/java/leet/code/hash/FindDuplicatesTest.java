package leet.code.hash;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link FindDuplicates}.
 *
 * @author guangyi
 */
public class FindDuplicatesTest {

    @Test(dataProvider = "findDuplicatesTestData")
    public void findDuplicates(int[] nums, List<Integer> expected) {
        List<Integer> result = FindDuplicates.findDuplicates(nums);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "findDuplicatesTestData")
    private static Object[][] findDuplicatesTestData() {
        return new Object[][]{
                {new int[]{4,3,2,7,8,2,3,1},
                        Arrays.asList(2,3)},
                {new int[]{1,3,1,2,3},
                        Arrays.asList(1,3)},
                {new int[]{1,1,2},
                        Collections.singletonList(1)},
                {new int[]{1},
                        Collections.emptyList()},
//                {new int[]{},
//                        Arrays.asList()},
        };
    }
}
