package sun.lang;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link String}.
 *
 * @author xingle
 * @since 1.0
 */
public class StringTest {

    @Test(dataProvider = "substringTestData")
    public void substring(String str, int beginIndex, int endIndex, String expected) {
        String subString = str.substring(beginIndex, endIndex);
        assertThat(subString).isEqualTo(expected);
    }

    @DataProvider(name = "substringTestData")
    private static Object[][] substringTestData() {
        return new Object[][]{
                {"1.65", 0, 3, "1.6"},
        };
    }

    @Test(dataProvider = "valueOfTestData")
    public void valueOf(Object obj, String expected) {
        String str = String.valueOf(obj);
        assertThat(str).isEqualTo(expected);
    }

    @DataProvider(name = "valueOfTestData")
    private static Object[][] valueOfTestData() {
        return new Object[][]{
                {0L, "0"},
        };
    }

}
