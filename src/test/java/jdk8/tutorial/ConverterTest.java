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
        // 方法引用
        Converter<String, Integer> converter = Integer::valueOf; // 静态方法引用

        return new Object[][]{
                {converter, "123", 123},
        };
    }


    // Lambda Scopes (Lambda作用域)
    @Test(description = "从Lambda表达式的外部作用域读取final本地变量")
    public void accessLocalVariables() {
        final int num = 1; // 局部变量
//        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        assertThat(stringConverter.convert(2)).isEqualTo("3");
    }

    /**
     * 实例字段和静态变量
     */
    static class Lambda4 {
        static int outerStaticNum;
        int outerNum;

        /**
         * 变量作用域
         */
        void testScopes() {
            Converter<Integer, String> stringConverter1 = (from) -> {
                outerNum = 23;
                assertThat(outerNum).isEqualTo(23);
                return String.valueOf(from);
            };
            assertThat(outerNum).isEqualTo(0);

            Converter<Integer, String> stringConverter2 = from -> {
                outerStaticNum = 72;
                assertThat(Lambda4.outerStaticNum).isEqualTo(72);
                return String.valueOf(from);
            };
            assertThat(Lambda4.outerStaticNum).isEqualTo(0);
        }
    }

    @Test
    public void testScopes() {
        Lambda4 lambda4 = new Lambda4();
        lambda4.testScopes();
        assertThat(lambda4.outerNum).isEqualTo(0);
        assertThat(Lambda4.outerStaticNum).isEqualTo(0);
    }
}
