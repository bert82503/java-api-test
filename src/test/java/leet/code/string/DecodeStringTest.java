package leet.code.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link DecodeString}.
 *
 * @author guangyi
 */
public class DecodeStringTest {

    @Test(dataProvider = "decodeStringTestData")
    public void decodeString(String str, String expected) {
        String result = DecodeString.decodeString(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "decodeStringTestData")
    private static Object[][] decodeStringTestData() {
        return new Object[][]{
                {"3[a]2[bc]", "aaabcbc"},
                {"3[a2[c]]", "accaccacc"},
                {"2[abc]3[cd]ef", "abcabccdcdcdef"},
                {"abc3[cd]xyz", "abccdcdcdxyz"},
                {"1[a]", "a"},
                {"11[a]", "aaaaaaaaaaa"},
        };
    }
}
