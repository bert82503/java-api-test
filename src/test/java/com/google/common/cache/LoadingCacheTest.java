package com.google.common.cache;

import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Test for {@link LoadingCache}.
 *
 * @author Bert Lee
 * @date 2016年03月20日 0:32
 */
public class LoadingCacheTest {

    private static final Logger logger = LoggerFactory.getLogger(LoadingCacheTest.class);

    private static final LoadingCache<String, Object> loadNullCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) {
                    return null;
                }
            });

    @Test(description = "当 load() 方法返回 null 时，会抛出 InvalidCacheLoadException 异常",
            expectedExceptions = InvalidCacheLoadException.class)
    public void loadReturnNull() {
        String key = "anyKey";
        try {
            loadNullCache.getUnchecked(key);
        } catch (InvalidCacheLoadException invalidCacheLoadException) {
            logger.error("get value from LocalCache failed, key: {}", key, invalidCacheLoadException);
            throw invalidCacheLoadException;
        }
    }

}
