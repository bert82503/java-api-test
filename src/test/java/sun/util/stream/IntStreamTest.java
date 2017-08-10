package sun.util.stream;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Test of {@link IntStream}。
 *
 * @author dannong
 * @since 2017年06月06日 11:31
 */
public class IntStreamTest {

  @Test
  public void mergeListArrayToMap() {
    String[] keys = {"k1", "k2", "k3"};
    List<String> values = Lists.newArrayList("v1", "v2", "v3");

    // merge List & Array to Map
    Map<String, String> map = IntStream.range(0, keys.length).boxed()
        .collect(Collectors.toMap(i -> keys[i], values::get)); // HashMap
    assertThat(map).isNotNull();
    assertThat(map.size()).isEqualTo(3);
    assertThat(map.get("k1")).isEqualTo("v1");
    assertThat(map.get("k2")).isEqualTo("v2");
    assertThat(map.get("k3")).isEqualTo("v3");
    assertThat(map.toString()).isEqualTo("{k1=v1, k2=v2, k3=v3}");
  }

  @Test(expectedExceptions = {NullPointerException.class})
  public void mergeEmptyListArrayToMap() {
    String[] keys = {"k1", "k2", "k3"};
    List<String> values = Lists.newArrayList(null, null, null);

    // merge List & Array to Map
    IntStream.range(0, keys.length).boxed()
        .collect(Collectors.toMap(i -> keys[i], values::get)); // HashMap
  }
}
