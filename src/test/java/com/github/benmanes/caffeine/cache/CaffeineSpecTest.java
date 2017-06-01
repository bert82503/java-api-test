package com.github.benmanes.caffeine.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.benmanes.caffeine.cache.Caffeine.Strength;

/**
 * Test of {@link CaffeineSpec}.
 *
 * @author dannong
 * @since 2017年06月01日 19:29
 */
public class CaffeineSpecTest {

    @Test(dataProvider = "parseTestData")
    public void parse(String specification) {
        CaffeineSpec caffeineSpec = CaffeineSpec.parse(specification);

        assertThat(caffeineSpec).isNotNull();
        assertThat(caffeineSpec.initialCapacity).isEqualTo(16);
        assertThat(caffeineSpec.maximumSize).isEqualTo(50L);
        assertThat(caffeineSpec.expireAfterAccessDuration).isEqualTo(5L);
        assertThat(caffeineSpec.expireAfterAccessTimeUnit).isEqualTo(TimeUnit.MINUTES);
        assertThat(caffeineSpec.expireAfterWriteDuration).isEqualTo(5L);
        assertThat(caffeineSpec.expireAfterWriteTimeUnit).isEqualTo(TimeUnit.MINUTES);
        assertThat(caffeineSpec.refreshAfterWriteDuration).isEqualTo(5L);
        assertThat(caffeineSpec.refreshAfterWriteTimeUnit).isEqualTo(TimeUnit.MINUTES);
        assertThat(caffeineSpec.keyStrength).isEqualTo(Strength.WEAK);
        assertThat(caffeineSpec.valueStrength).isEqualTo(Strength.SOFT);
        assertThat(caffeineSpec.recordStats).isEqualTo(true);
    }

    @DataProvider(name = "parseTestData")
    private static Object[][] parseTestData() {
        return new Object[][]{
                {"initialCapacity=16,maximumSize=50," +
                        "expireAfterAccess=5m,expireAfterWrite=5m," +
                        "refreshAfterWrite=5m," +
                        "weakKeys,softValues," +
                        "recordStats"},
        };
    }


    @Test(dataProvider = "toBuilderTestData")
    public void toBuilder(String specification) {
        CaffeineSpec caffeineSpec = CaffeineSpec.parse(specification);
        assertThat(caffeineSpec).isNotNull();

        Caffeine<Object, Object> caffeine = caffeineSpec.toBuilder();
        assertThat(caffeine.getInitialCapacity()).isEqualTo(16);
        assertThat(caffeine.getMaximum()).isEqualTo(50L);
        assertThat(caffeine.getExpiresAfterAccessNanos())
                .isEqualTo(TimeUnit.MINUTES.toNanos(5L));
        assertThat(caffeine.getExpiresAfterWriteNanos())
                .isEqualTo(TimeUnit.MINUTES.toNanos(5L));
        assertThat(caffeine.getRefreshAfterWriteNanos())
                .isEqualTo(TimeUnit.MINUTES.toNanos(5L));
        assertThat(caffeine.isWeakKeys()).isEqualTo(true);
        assertThat(caffeine.isSoftValues()).isEqualTo(true);
        assertThat(caffeine.isRecordingStats()).isEqualTo(true);
    }

    @DataProvider(name = "toBuilderTestData")
    private static Object[][] toBuilderTestData() {
        return new Object[][]{
                {"initialCapacity=16,maximumSize=50," +
                        "expireAfterAccess=5m,expireAfterWrite=5m," +
                        "refreshAfterWrite=5m," +
                        "weakKeys,softValues," +
                        "recordStats"},
        };
    }
}
