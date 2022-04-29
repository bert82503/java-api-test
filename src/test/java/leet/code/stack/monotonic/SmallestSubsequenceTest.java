package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link SmallestSubsequence}.
 *
 * @author guangyi
 */
public class SmallestSubsequenceTest {

    @Test(dataProvider = "smallestSubsequenceTestData")
    public void smallestSubsequence(String str, String expected) {
        String result = SmallestSubsequence.smallestSubsequence(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "smallestSubsequenceTestData")
    private static Object[][] smallestSubsequenceTestData() {
        return new Object[][]{
                {"bcabc", "abc"},
                {"cbacdcbc", "acdb"},
                {"bcac", "bac"},
                {"a", "a"},
        };
    }
}
