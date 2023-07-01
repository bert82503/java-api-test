package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link RemoveDuplicateLetters}.
 *
 * @author guangyi
 */
public class RemoveDuplicateLettersTest {

    @Test(dataProvider = "removeDuplicateLettersTestData")
    public void removeDuplicateLetters(String str, String expected) {
        String result = RemoveDuplicateLetters.removeDuplicateLetters(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "removeDuplicateLettersTestData")
    private static Object[][] removeDuplicateLettersTestData() {
        return new Object[][]{
                {"bcabc", "abc"},
                {"cbacdcbc", "acdb"},
                {"bcac", "bac"},
                {"a", "a"},
        };
    }
}
