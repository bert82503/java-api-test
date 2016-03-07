package sun.lang;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Math}.
 *
 * @author xingle
 * @since 1.0
 */
public class MathTest {

    @Test(dataProvider = "maxTestData")
    public void max(int a, int b, int expected) {
        int result = Math.max(a, b);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "maxTestData")
    public static Object[][] maxTestData() {
        return new Object[][]{
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {10, 0, 10},
                {7, 0, 7},
                {1, 0, 1},
                {0, 0, 0},
                {-1, 0, 0},
                {-17, 0, 0},
                {Integer.MIN_VALUE, 0, 0},
        };
    }

}
