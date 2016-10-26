package jdk8.tutorial;

import com.google.common.collect.Maps;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Maps. (映射)
 *
 * @author dannong
 * @since 2016年10月26日 11:40
 */
public class MapsTest {
    @Test
    public void tutorial() {
        Map<Integer, String> map = Maps.newHashMap();
        map.putIfAbsent(0, "val-0");
        map.forEach((key, value) -> assertThat(value).isEqualTo("val-0"));

        map = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        // how to compute code on the map by utilizing functions
        map.computeIfPresent(3, (num, val) -> val + num); // BiFunction
        assertThat(map.get(3)).isEqualTo("val33");

        map.computeIfPresent(9, (num, val) -> null);// remove
        assertThat(map.containsKey(9)).isFalse();

        map.computeIfAbsent(23, num -> "val" + num); // Function
        assertThat(map.containsKey(23)).isTrue();

        map.computeIfAbsent(3, num -> "bam");
        assertThat(map.get(3)).isEqualTo("val33");

        // how to remove entries for a given key, only if it's currently mapped to a given value
        map.remove(3, "val3");
        assertThat(map.get(3)).isEqualTo("val33");

        map.remove(3, "val33"); // 有条件删除
        assertThat(map.get(3)).isNull();

        assertThat(map.getOrDefault(42, "not found")).isEqualTo("not found");

        // Merging entries of a map is quite easy
//        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.merge(9, "val9", String::concat);
        assertThat(map.get(9)).isEqualTo("val9");

//        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.merge(9, "concat", String::concat);
        assertThat(map.get(9)).isEqualTo("val9concat");
    }
}
