package com.google.common.base;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of {@link Optional}.
 *
 * @author xingle
 * @since 2016年09月20日 10:00
 */
public class OptionalTest {

    @Test(expectedExceptions = IllegalStateException.class)
    public void absent() {
        Optional<String> noReference = Optional.absent();
        assertThat(noReference.isPresent()).isFalse();
        noReference.get(); // IllegalStateException
    }


    @Test(description = "使用非空值替换为可null的T引用的一种方式",
            dataProvider = "presentTestData")
    public <T> void present(Optional<T> possible, T expected) {
        assertThat(possible.isPresent()).isTrue();
        T reference = possible.get();
        assertThat(reference).isEqualTo(expected);
    }

    @DataProvider(name = "presentTestData")
    private static Object[][] presentTestData() {
        return new Object[][]{
                {Optional.of("Edward Lee"), "Edward Lee"},
                {Optional.of(24), 24},
                {Optional.of(23L), 23L},
                {Optional.of(Collections.emptyList()), Collections.emptyList()},
        };
    }


    @Test
    public void fromNullable() {
        Optional<Integer> nullReference = Optional.fromNullable(null);
        assertThat(nullReference.isPresent()).isFalse();

        Optional<Long> reference = Optional.fromNullable(3L);
        assertThat(reference.isPresent()).isTrue();
        assertThat(reference.get()).isEqualTo(3L);
    }

}
