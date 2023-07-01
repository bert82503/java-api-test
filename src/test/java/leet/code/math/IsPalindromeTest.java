package leet.code.math;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link IsPalindrome}.
 *
 * @author guangyi
 */
public class IsPalindromeTest {

    @Test(dataProvider = "isPalindromeTestData")
    public void isPalindrome(int x, boolean expected) {
        boolean result = IsPalindrome.isPalindrome(x);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "isPalindromeTestData")
    private static Object[][] isPalindromeTestData() {
        return new Object[][]{
                {121, true},
                {1, true},
                {0, true},
                {-121, false},
                {10, false},
        };
    }
}
