package com.google.common.base;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Unit test of {@link Joiner}.
 *
 * @author xingle
 * @since 2016年06月24日 18:09
 */
public class JoinerTest {

    /**
     * @see com.google.common.cache.CacheBuilderSpec
     */
    @Test(dataProvider = "joinTestData")
    public void join(List<String> list, String expected) {
        String result = Joiner.on(',').join(list);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "joinTestData")
    private static Object[][] joinTestData() {
        return new Object[][]{
                {Collections.emptyList(), ""},
                {Collections.singletonList("initialCapacity=16"), "initialCapacity=16"},
                {Arrays.asList(
                        "initialCapacity=16", // 初始容量
                        "maximumSize=1024", // 容量最大大小，防止缓存被撑爆！
                        "concurrencyLevel=4", // 并发级别
                        "expireAfterWrite=0m", // 写入过期时间
                        "expireAfterAccess=0h", // 访问过期时间
                        "refreshAfterWrite=1m", // 写入过期时间
                        "recordStats" // 性能统计
                ), "initialCapacity=16,maximumSize=1024,concurrencyLevel=4,expireAfterWrite=0m,expireAfterAccess=0h,refreshAfterWrite=1m,recordStats"},
        };
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void join_Exception() {
        Joiner.on(',').join(Lists.newArrayList((String) null));
    }
}
