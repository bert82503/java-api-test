package leet.code.dynamic.programming;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link Fib}.
 *
 * @author guangyi
 */
public class FibTest {

    @Test(dataProvider = "fibTestData")
    public void fib(int n, int expected) {
        int result = Fib.fib(n);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "fibTestData")
    private static Object[][] fibTestData() {
        return new Object[][]{
                {2, 1},
                {3, 2},
                {4, 3},
        };
    }
}
