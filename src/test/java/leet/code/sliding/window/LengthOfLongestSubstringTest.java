package leet.code.sliding.window;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link LengthOfLongestSubstring}.
 *
 * @author guangyi
 */
public class LengthOfLongestSubstringTest {

    @Test(dataProvider = "lengthOfLongestSubstringTestData")
    public void lengthOfLongestSubstring(String str, int expected) {
        int result = LengthOfLongestSubstring.lengthOfLongestSubstring(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "lengthOfLongestSubstringTestData")
    private static Object[][] lengthOfLongestSubstringTestData() {
        return new Object[][]{
                {"abcabcbb", 3},
                {"bbbbb", 1},
                {"pwwkew", 3},
                {"abcddcba", 4},
                {" abcd ", 5},
                {"#abcd@", 6},
                {"aa", 1},
                {"a", 1},
                {"", 0},
        };
    }
}
