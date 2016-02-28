package sun.util;

import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Collections}.
 *
 * @author xingle
 * @since 1.0
 */
public class CollectionsTest {

    @Test(dataProvider = "sortTestData")
    public <T extends Comparable<? super T>> void sort(List<T> list, List<T> expected) {
        assertThat(list).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(list.size()).isEqualTo(expected.size());

        Collections.sort(list);
        assertThat(list).isEqualTo(expected);
    }

    @DataProvider(name = "sortTestData")
    public static Object[][] sortTestData() {
        return new Object[][] {
                { Lists.newArrayList(7, 23, 10, 3), Arrays.asList(3, 7, 10, 23) },
        };
    }

}
