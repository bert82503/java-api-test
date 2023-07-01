package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ReverseVowels}.
 *
 * @author guangyi
 */
public class ReverseVowelsTest {

    @Test(dataProvider = "reverseVowelsTestData")
    public void reverseVowels(String str, String expected) {
        String result = ReverseVowels.reverseVowels(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "reverseVowelsTestData")
    private static Object[][] reverseVowelsTestData() {
        return new Object[][]{
                {"hello", "holle"},
                {"leetcode", "leotcede"},
                {"xyz", "xyz"},
                {"abi", "iba"},
                {"aei", "iea"},
                {"ae", "ea"},
                {"a", "a"},
                {"h", "h"},
//                {"", ""},
        };
    }
}
