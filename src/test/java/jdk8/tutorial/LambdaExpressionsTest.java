package jdk8.tutorial;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test of {@link LambdaExpressions}.
 *
 * @author dannong
 * @since 2016年10月22日 17:05
 */
public class LambdaExpressionsTest {
    @Test(dataProvider = "sortStringTestData")
    public void sortString(List<String> names, List<String> expected) {
        LambdaExpressions.sortString(names);
        assertThat(names.size()).isEqualTo(expected.size());
        assertThat(names).isEqualTo(expected);
    }

    @DataProvider(name = "sortStringTestData")
    private static Object[][] sortStringTestData() {
        return new Object[][]{
                {Arrays.asList("peter", "anna", "mike", "xenia"), Arrays.asList("xenia", "peter", "mike", "anna")},
        };
    }

    @Test(dataProvider = "sortTestData")
    public <T extends Comparable<T>> void sort(List<T> names, List<T> expected) {
        LambdaExpressions.sort(names);
        assertThat(names.size()).isEqualTo(expected.size());
        assertThat(names).isEqualTo(expected);
    }

    @DataProvider(name = "sortTestData")
    private static Object[][] sortTestData() {
        return new Object[][]{
                {Arrays.asList("peter", "anna", "mike", "xenia"), Arrays.asList("xenia", "peter", "mike", "anna")},
        };
    }
}
