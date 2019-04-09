package sun.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Test of {@link java.util.Set}.
 */
public class SetTest {

  @Test
  public void initialCapacity() {
    // HashMap threshold = 2
    Set<String> set = new HashSet<>(2);
    set.add("one");
    assertThat(set.size()).isEqualTo(1);

    // HashMap threshold = 4
    set = new HashSet<>(4);
    assertThat(set.size()).isEqualTo(0);
  }

  @Test
  public void sortedSet() {
    SortedSet<String> sortedSet = new TreeSet<>();
//    sortedSet.add(null);
    sortedSet.add("");
    sortedSet.add("set");

    assertThat(sortedSet.size()).isEqualTo(2);
  }

  @Test(expected = NullPointerException.class)
  public void sortedSetNPE() {
    SortedSet<String> sortedSet = new TreeSet<>();
    sortedSet.add(null);

    assertThat(sortedSet.isEmpty()).isTrue();
  }

}
