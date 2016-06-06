package com.google.common.collect;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link Lists}.
 *
 * @author xingle
 * @since 2016年06月03日 14:10
 */
public class ListsTest {

    @Test(dataProvider = "newArrayListWithXXXTestData")
    public <T> void newArrayListWithXXX(List<T> list) {
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(0);
        for (T e : list) {
            assertThat(e).isNotNull();
        }
    }

    @DataProvider(name = "newArrayListWithXXXTestData")
    private static Object[][] newArrayListWithXXXTestData() {
        return new Object[][]{
                {Lists.<Integer>newArrayListWithCapacity(3)}, // 初始数组大小
                {Lists.<String>newArrayListWithExpectedSize(3)}, // 预估大小

                {Collections.<Long>emptyList()},
        };
    }
}
