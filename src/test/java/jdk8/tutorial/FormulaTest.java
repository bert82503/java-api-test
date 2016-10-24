package jdk8.tutorial;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test of {@link Formula}.
 *
 * @author dannong
 * @since 2016年10月22日 15:19
 */
public class FormulaTest {

    @Test(dataProvider = "calculateTestData")
    public void calculate(Formula formula, int a, double expected) {
        assertThat(formula.calculate(a)).isEqualTo(expected);
    }

    @DataProvider(name = "calculateTestData")
    private static Object[][] calculateTestData() {
        Formula formula = new Formula() { // 匿名对象
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        return new Object[][]{
                {formula, 0, 0.0},
                {formula, 100, 100.0},
                {formula, 10000, 1000.0},
        };
    }


    @Test(dataProvider = "sqrtTestData")
    public void sqrt(Formula formula, int a, double expected) {
        assertThat(formula.sqrt(a)).isEqualTo(expected);
    }

    @DataProvider(name = "sqrtTestData")
    private static Object[][] sqrtTestData() {
        // Java 8中实现单个方法的对象有更好的方式
//        Formula formula = new Formula() {
//            @Override
//            public double calculate(int a) {
//                return 0;
//            }
//        };
        // 与上面声明等价
        Formula formula = a -> 0; // Lambda表达式

        Formula overrideDefaultMethod = new Formula() {
            @Override
            public double calculate(int a) {
                throw new UnsupportedOperationException();
            }

            // 覆盖接口的默认方法（强烈建议不要这么做！）
            @Override
            public double sqrt(int a) {
                return 0;
            }
        };

        return new Object[][]{
                {formula, 16, 4.0},
                {overrideDefaultMethod, 16, 0.0},
        };
    }
}
