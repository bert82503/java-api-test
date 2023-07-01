package leet.code.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ZigzagConversion}.
 *
 * @author guangyi
 */
public class ZigzagConversionTest {

    @Test(dataProvider = "convertTestData")
    public void convert(String str, int numRows, String expected) {
        String result = ZigzagConversion.convert(str, numRows);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "convertTestData")
    private static Object[][] convertTestData() {
        return new Object[][]{
                {"PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"},
                {"PAYPALISHIRING", 4, "PINALSIGYAHRPI"},
                {"A", 1, "A"},
//                {"", , ""},
        };
    }
}
