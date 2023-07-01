package leet.code.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MyAtoI}.
 *
 * @author guangyi
 */
public class MyAtoITest {

    @Test(dataProvider = "myAtoiTestData")
    public void myAtoi(String str, int expected) {
        int result = MyAtoI.myAtoi(str);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "myAtoiTestData")
    private static Object[][] myAtoiTestData() {
        return new Object[][]{
                {"", 0},
                {"42", 42},
                {"   -42", -42},
                {"4193 with words", 4193},
                {"0", 0},
                {"214748364", 214748364},
                {"-2147483648", -2147483648},
                {"214748365", 214748365},
                {"-2147483649", -2147483648},
                // 未考虑到
                {"2147483646", 2147483646},
        };
    }
}
