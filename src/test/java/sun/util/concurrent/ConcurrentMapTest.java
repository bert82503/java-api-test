package sun.util.concurrent;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link ConcurrentMap}.
 *
 * @author xingle
 * @since 2016年05月05日 14:14
 */
public class ConcurrentMapTest {

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
