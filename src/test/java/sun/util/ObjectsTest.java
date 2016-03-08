package sun.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Objects}.
 *
 * @author xingle
 * @since 1.0
 */
public class ObjectsTest {

    @Test(dataProvider = "equalsTestData")
    public void equals(Object a, Object b, boolean expected) {
        boolean result = Objects.equals(a, b);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "equalsTestData")
    private static Object[][] equalsTestData() {
        return new Object[][]{
                {null, null, true}, // 相等
                {null, "123", false},
                {"qwe", null, false},

                {"jklfd", "duire", false},
                {"difkle", "difkle", true},
                {"hello, " + "world", "hello, world", true},
        };
    }


    @Test(dataProvider = "hashCodeTestData")
    public void hashCode_(Object obj, int expected) {
        int hashCode = Objects.hashCode(obj);
        assertThat(hashCode).isEqualTo(expected);
    }

    @DataProvider(name = "hashCodeTestData")
    private static Object[][] hashCodeTestData() {
        return new Object[][]{
                {null, 0},
        };
    }


    /**
     * 建议使用 {@link java.util.Arrays#hashCode(Object[])}
     */
    @Test(dataProvider = "hashTestData")
    public void hash(Object[] values, int expected) {
        int hashCode = Objects.hash(values);
        assertThat(hashCode).isEqualTo(expected);
    }

    @DataProvider(name = "hashTestData")
    private static Object[][] hashTestData() {
        return new Object[][]{
                {null, 0},
        };
    }


    @Test(dataProvider = "isNullTestData")
    public void isNull(Object obj, boolean expected) {
        boolean result = Objects.isNull(obj);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "isNullTestData")
    private static Object[][] isNullTestData() {
        return new Object[][]{
                {null, true},
                {new Object(), false},
                {"", false},
        };
    }


    @Test(dataProvider = "nonNullTestData")
    public void nonNull(Object obj, boolean expected) {
        boolean result = Objects.nonNull(obj);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "nonNullTestData")
    private static Object[][] nonNullTestData() {
        return new Object[][]{
                {null, false},
                {new Object(), true},
                {"", true},
        };
    }

}
