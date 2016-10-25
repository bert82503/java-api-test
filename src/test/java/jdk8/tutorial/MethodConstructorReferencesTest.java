package jdk8.tutorial;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test of {@link MethodConstructorReferences}.
 *
 * @author dannong
 * @since 2016年10月24日 17:14
 */
public class MethodConstructorReferencesTest {
    @Test(dataProvider = "startsWithTestData")
    public void startsWith(Converter<String, String> converter, String from, String expected) {
        String result = converter.convert(from);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "startsWithTestData")
    private static Object[][] startsWithTestData() {
        MethodConstructorReferences references = new MethodConstructorReferences();
        Converter<String, String> converter = references::startsWith; // 实例方法引用

        return new Object[][]{
                {converter, "Java", "J"},
                {converter, "string", "s"},
                {converter, " blank", " "},
        };
    }
}
