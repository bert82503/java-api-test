package leet.code.stack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ValidParentheses}.
 *
 * @author guangyi
 */
public class ValidParenthesesTest {

    @Test(dataProvider = "isValidTestData")
    public void isValid(String str, boolean expected) {
        boolean result = ValidParentheses.isValid(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "isValidTestData")
    private static Object[][] isValidTestData() {
        return new Object[][]{
                {"()[]{}", true},
                {"{[]}", true},
                {"()", true},
                {"(([[{({{[[]]}})}]]))", true},
                {"", true},
                {null, true},
                // 不满足
                {"(]", false},
                {"([)]", false},
                {"(", false},
                {"(((((()[", false},
        };
    }
}
