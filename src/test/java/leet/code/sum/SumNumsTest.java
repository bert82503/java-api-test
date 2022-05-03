package leet.code.sum;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SumNums}.
 *
 * @author guangyi
 */
public class SumNumsTest {

    @Test(dataProvider = "sumNumsTestData")
    public void sumNums(int n, int expected) {
        int result = SumNums.sumNums(n);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "sumNumsTestData")
    private static Object[][] sumNumsTestData() {
        return new Object[][]{
                {1, 1},
                {2, 3},
                {3, 6},
                {9, 45},
        };
    }
}
