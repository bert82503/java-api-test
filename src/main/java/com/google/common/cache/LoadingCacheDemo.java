package com.google.common.cache;

import com.google.common.base.Ticker;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Caches Explained
 * https://github.com/google/guava/wiki/CachesExplained
 *
 * @author lihuagang
 * @date 2023/7/2
 */
@Slf4j
public class LoadingCacheDemo {

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setDaemon(true)
            .setNameFormat("loading-cache")
            .build();
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
            1, 4,
            5, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(100),
            THREAD_FACTORY,
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * @see LoadingCache
     * @see CacheBuilder
     * @see CacheLoader
     */
    public static void main(String[] args) {
        // 缓存加载器
        CacheLoader<String, Object> cacheLoader = new CacheLoader<>() {
            @Override
            public Object load(String key) throws Exception {
                // createObject(key);
                return null;
            }

            // Refresh，刷新
            @Override
            public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                ListenableFutureTask<@Nullable Object> futureTask = ListenableFutureTask.create(
                        () -> {
                            // createObject(key);
                            return null;
                        }
//                        new Callable<Object>() {
//                            @Override
//                            public Object call() throws Exception {
//                                // createObject(key);
//                                return null;
//                            }
//                        }
                );
                EXECUTOR_SERVICE.execute(futureTask);
                return futureTask;

//                return super.reload(key, oldValue);
            }

            @Override
            public Map<String, Object> loadAll(Iterable<? extends String> keys) throws Exception {
                return super.loadAll(keys);
            }
        };
        // Removal Listeners，移除监视器
        RemovalListener<String, Object> removalListener = new RemovalListener<String, Object>() {
            @Override
            public void onRemoval(RemovalNotification<String, Object> notification) {
                String key = notification.getKey();
                Object value = notification.getValue();
                RemovalCause cause = notification.getCause();
                log.info("removal key:{}, value:{}, cause:{}", key, value, cause);
            }
        };

        // Cache, ConcurrentMap
        LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder()
//                .initialCapacity(100) // 初始容量
//                .concurrencyLevel(16) // 并发等级

                // Eviction，驱逐机制
                // Size-based Eviction，基于大小的驱逐策略
                .maximumSize(1_000L)
                .maximumWeight(100_000L)
                .weigher(
                        (Weigher<String, Object>) (key, value) -> value.hashCode()
//                        new Weigher<String, Object>() {
//                            @Override
//                            public int weigh(String key, Object value) {
//                                return value.hashCode();
//                            }
//                        }
                )
                // Timed Eviction，基于时间的驱逐策略
                .expireAfterAccess(Duration.ofMinutes(10L))
                .expireAfterWrite(Duration.ofMinutes(10L))
                // Testing Timed Eviction
                .ticker(Ticker.systemTicker())
                // Reference-based Eviction，基于引用的驱逐策略
                .weakKeys()
                .weakValues()
                .softValues()

                // Removal Listeners，移除监视器
                .removalListener(removalListener)
                .removalListener(RemovalListeners.asynchronous(removalListener, EXECUTOR_SERVICE))

                // Refresh，刷新
                .refreshAfterWrite(Duration.ofMinutes(1L))

                // Statistics，统计
                .recordStats()

                // 构建缓存
                .build(cacheLoader);

        // Statistics，统计
        // 定时输出打印日志
        CacheStats cacheStats = loadingCache.stats();
        log.info("cache stats, {}", cacheStats.toString());
        log.info("cache stats, hitRate:{}, averageLoadPenalty:{}, evictionCount:{}",
                cacheStats.hitRate(), cacheStats.averageLoadPenalty(), cacheStats.evictionCount());

        // Population
        String key = "user:123";
        try {
            // From a CacheLoader
            Object value = loadingCache.get(key);
            // From a Callable
            value = loadingCache.get(key,
                    () -> {
                        // createObject(key);
                        return null;
                    }
//                    new Callable<>() {
//                        @Override
//                        public Object call() throws Exception {
//                            // createObject(key);
//                            return null;
//                        }
//                    }
            );
            // Inserted Directly
            loadingCache.put(key, new Object());
            // asMap
            ConcurrentMap<String, Object> concurrentMap = loadingCache.asMap();
            concurrentMap.putIfAbsent(key, new Object());

            // Explicit Removals
            loadingCache.invalidate(key);
            loadingCache.invalidateAll(Arrays.asList(args));
            loadingCache.invalidateAll();

            // When Does Cleanup Happen?
//        loadingCache.cleanUp();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
