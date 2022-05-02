package leet.code.sliding.window;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MinWindow}.
 *
 * @author guangyi
 */
public class MinWindowTest {

    @Test(dataProvider = "minWindowTestData")
    public void minWindow(String str, String target, String expected) {
        String result = MinWindow.minWindow(str, target);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "minWindowTestData")
    private static Object[][] minWindowTestData() {
        return new Object[][]{
                {"ADOBECODEBANC", "ABC", "BANC"},
                {"a", "a", "a"},
                {"a", "aa", ""},
//                {, , },
        };
    }
}
