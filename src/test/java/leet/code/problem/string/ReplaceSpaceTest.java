package leet.code.problem.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ReplaceSpace}.
 *
 * @author guangyi
 */
public class ReplaceSpaceTest {

    @Test(dataProvider = "replaceSpaceTestData")
    public void replaceSpace(String str, String expected) {
        String result = ReplaceSpace.replaceSpace(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "replaceSpaceTestData")
    private static Object[][] replaceSpaceTestData() {
        return new Object[][]{
                {"We are happy.", "We%20are%20happy."},
                {" dannong ", "%20dannong%20"},
                {"   ", "%20%20%20"},
                {" ", "%20"},
                {"", ""},
//                {, },
        };
    }
}
