package sun.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    public <T> void addAll(List<T> target, List<T> list, List<T> expected) {
        target.addAll(list);
        assertThat(target.size()).isEqualTo(expected.size());
        assertThat(target).isEqualTo(expected);
    }

    @DataProvider(name = "addAllTestData")
    public static Object[][] addAllTestData() {
        return new Object[][] {
                { Collections.emptyList(), Collections.emptyList(), Collections.emptyList() },
        };
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "addAllExceptionTestData")
    public <T> void addAllException(List<T> target, List<T> list) {
        target.addAll(list);
    }

    @DataProvider(name = "addAllExceptionTestData")
    public static Object[][] addAllExceptionTestData() {
        return new Object[][] {
                { Collections.emptyList(), null },
        };
    }

}
