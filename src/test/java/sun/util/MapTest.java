package sun.util;

import com.google.common.collect.Maps;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test of {@link Map}.
 *
 * @since 2017年07月16日 21:21
 */
public class MapTest {

  /// ((HashMap) map).capacity()

  @Test
  public void initialCapacity() {
    HashMap<String, Object> map = new HashMap<>(); // 16
    map = Maps.newHashMapWithExpectedSize(1); // 2
    map = Maps.newHashMapWithExpectedSize(2); // 4
    map = new HashMap<>(2); // 2
    map.put("key", "value");
  }

}
