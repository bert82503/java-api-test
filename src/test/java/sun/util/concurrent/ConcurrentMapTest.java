package sun.util.concurrent;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Unit test for {@link ConcurrentMap}.
 *
 * @author xingle
 * @since 2016年05月05日 14:14
 */
public class ConcurrentMapTest {

    @Test(description = "防多次设置出现值被覆盖")
    public void putIfAbsent() {
        String key = "key";
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<>();
        Object value = concurrentMap.putIfAbsent(key, "3"); // 首次设置OK
        assertThat(value).isNull();
        value = concurrentMap.putIfAbsent(key, "23"); // 接下来设置都KO
        assertThat(value).isEqualTo("3");
        value = concurrentMap.get(key); // 首次设置的值
        assertThat(value).isEqualTo("3");
    }

    @Test
    public void newMapByMap() {
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("ConcurrentHashMap", "CHM");

        Map<String, Object> map = new HashMap<>(concurrentMap); // new copy

        map.put("HashMap", "HM"); // 不影响 ConcurrentHashMap
        assertThat(map.size()).isEqualTo(2);
        assertThat(concurrentMap.size()).isEqualTo(1);
        assertThat(concurrentMap.get("HashMap")).isNull();

        concurrentMap.put("ConcurrentHashMap", "put"); // 不影响 HashMap
        concurrentMap.putIfAbsent("ConcurrentMap", "CM");
        assertThat(concurrentMap.size()).isEqualTo(2);
        assertThat(concurrentMap.get("ConcurrentHashMap")).isEqualTo("put");
        assertThat(map.size()).isEqualTo(2);
        assertThat(map.get("ConcurrentHashMap")).isEqualTo("CHM");
    }
}
