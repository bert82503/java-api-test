package jdk8.tutorial;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test of {@link Converter}.
 *
 * @author dannong
 * @since 2016年10月24日 16:50
 */
public class ConverterTest {
    @Test(dataProvider = "convertTestData")
    public <F, T> void convert(Converter<F, T> converter, F from, T expected) {
        T result = converter.convert(from);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "convertTestData")
    private static Object[][] convertTestData() {
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Converter<String, Integer> converter = Integer::valueOf;

        return new Object[][]{
                {converter, "123", 123},
        };
    }
}
