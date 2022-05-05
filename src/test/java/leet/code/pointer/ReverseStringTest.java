package leet.code.pointer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ReverseString}.
 *
 * @author guangyi
 */
public class ReverseStringTest {

    @Test(dataProvider = "reverseStringTestData")
    public void reverseString(char[] str, char[] expected) {
        ReverseString.reverseString(str);
        assertThat(str).isEqualTo(expected);
    }

    @DataProvider(name = "reverseStringTestData")
    private static Object[][] reverseStringTestData() {
        return new Object[][]{
                {new char[]{'h','e','l','l','o'},
                        new char[]{'o','l','l','e','h'}},
                {new char[]{'H','a','n','n','a','h'},
                        new char[]{'h','a','n','n','a','H'}},
                {new char[]{'a','b'},
                        new char[]{'b','a'}},
                {new char[]{'a'},
                        new char[]{'a'}},
//                {new char[]{}, new char[]{}},
        };
    }
}
