package sun.util;

import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link List}.
 *
 * @author xingle
 * @since 1.0
 */
public class ListTest {

    @Test(dataProvider = "addAllTestData")
    public <E> void addAll(List<E> target, List<E> list, List<E> expected) {
        target.addAll(list);
        assertThat(target.size()).isEqualTo(expected.size());
        assertThat(target).isEqualTo(expected);
    }

    @DataProvider(name = "addAllTestData")
    public static Object[][] addAllTestData() {
        return new Object[][] {
                { Collections.emptyList(), Collections.emptyList(), Collections.emptyList() },
                // Arrays.asList 返回的列表不支持增加、删除操作
                { Lists.newArrayList(3, 7), Arrays.asList(10, 3), Arrays.asList(3, 7, 10, 3) },
        };
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "addAllExceptionTestData")
    public <E> void addAllException(List<E> target, List<E> list) {
        target.addAll(list);
    }

    @DataProvider(name = "addAllExceptionTestData")
    public static Object[][] addAllExceptionTestData() {
        return new Object[][] {
                { Collections.emptyList(), null },
        };
    }

}
