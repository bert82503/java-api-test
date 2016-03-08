package sun.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Arrays}.
 *
 * @author xingle
 * @since 1.0
 */
public class ArraysTest {

    /**
     * {@link Arrays#asList(Object[])} 方法返回的列表不支持增加、删除操作。
     *
     * @param fixedSizeList 使用 Arrays.asList(Object[]) 方法返回的固定大小的列表
     * @param element       待添加的元素
     * @param <E>           元素类型
     */
    @Test(dataProvider = "asListTestData",
            expectedExceptions = UnsupportedOperationException.class)
    public <E> void addException(List<E> fixedSizeList, E element) {
        fixedSizeList.add(element);
    }

    @DataProvider(name = "asListTestData")
    private static Object[][] asListTestData() {
        return new Object[][]{
                {Arrays.asList(1, 2, 3), 4},
        };
    }


    @Test(dataProvider = "hashCodeTestData")
    public void hashCode_(Object[] values, int expected) {
        int hashCode = Arrays.hashCode(values);
        assertThat(hashCode).isEqualTo(expected);
    }

    @DataProvider(name = "hashCodeTestData")
    private static Object[][] hashCodeTestData() {
        return new Object[][]{
                {null, 0},
        };
    }

}
