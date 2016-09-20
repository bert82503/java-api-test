package sun.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedTransferQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Collections}.
 *
 * @author xingle
 * @since 1.0
 */
public class CollectionsTest {

    @Test(dataProvider = "sortTestData")
    public <E extends Comparable<? super E>> void sort(List<E> list, List<E> expected) {
        assertThat(list).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(list.size()).isEqualTo(expected.size());

        Collections.sort(list);
        assertThat(list).isEqualTo(expected);
    }

    @DataProvider(name = "sortTestData")
    private static Object[][] sortTestData() {
        return new Object[][]{
                {Lists.newArrayList(7, 23, 10, 3), Arrays.asList(3, 7, 10, 23)},
        };
    }

    // Unmodifiable Wrappers (不可变包装器)

    @Test(expectedExceptions = UnsupportedOperationException.class,
            dataProvider = "unmodifiableCollectionTestData")
    public <E> void unmodifiableCollection(Collection<E> c, E newElement) {
        assertThat(c).isNotNull();
        assertThat(c).isEmpty();

        // modify - 抛出 UnsupportedOperationException
        c.add(newElement);
    }

    @DataProvider(name = "unmodifiableCollectionTestData")
    private static Object[][] unmodifiableCollectionTestData() {
        return new Object[][]{
                // Collection
                // Queue
                {Collections.unmodifiableCollection(Queues.<String>newPriorityQueue()), StringUtils.EMPTY},
                {Collections.unmodifiableCollection(Queues.<String>newConcurrentLinkedQueue()), StringUtils.EMPTY},
                // Deque
                {Collections.unmodifiableCollection(Lists.<String>newLinkedList()), StringUtils.EMPTY},
                // BlockingQueue
                {Collections.unmodifiableCollection(Queues.<String>newArrayBlockingQueue(1)), StringUtils.EMPTY},
                {Collections.unmodifiableCollection(Queues.<String>newLinkedBlockingQueue(1)), StringUtils.EMPTY},
                {Collections.unmodifiableCollection(Queues.<String>newLinkedBlockingDeque(1)), StringUtils.EMPTY},
                {Collections.unmodifiableCollection(Queues.<String>newArrayDeque()), StringUtils.EMPTY},
                {Collections.unmodifiableCollection(Queues.<String>newConcurrentLinkedQueue()), StringUtils.EMPTY},
                {Collections.unmodifiableCollection(Queues.<String>newSynchronousQueue()), StringUtils.EMPTY},
                // TransferQueue
                {Collections.unmodifiableCollection(new LinkedTransferQueue<String>()), StringUtils.EMPTY},

                // Set
                {Collections.unmodifiableSet(Sets.<String>newHashSet()), StringUtils.EMPTY},
                {Collections.unmodifiableSet(Sets.<String>newLinkedHashSet()), StringUtils.EMPTY},
                {Collections.unmodifiableSet(
                        Sets.newEnumSet(EnumSet.noneOf(MapEnum.class), MapEnum.class)), MapEnum.NONE},
                // SortedSet
                {Collections.unmodifiableSortedSet(Sets.<String>newTreeSet()), StringUtils.EMPTY},
                // NavigableSet
                {Collections.unmodifiableSet(Sets.<String>newConcurrentHashSet()), StringUtils.EMPTY}, // ConcurrentHashMap
                {Collections.unmodifiableSet(new ConcurrentSkipListSet<String>()), StringUtils.EMPTY}, // ConcurrentSkipListMap

                // List
                {Collections.unmodifiableList(Lists.<String>newArrayList()), StringUtils.EMPTY},
                {Collections.unmodifiableList(Lists.<String>newCopyOnWriteArrayList()), StringUtils.EMPTY},
        };
    }

    @Test(expectedExceptions = UnsupportedOperationException.class,
            dataProvider = "unmodifiableMapTestData")
    public <E> void unmodifiableMap(Map<String, E> map, E newElement) {
        assertThat(map).isNotNull();
        assertThat(map).isEmpty();
        // modify - 抛出 UnsupportedOperationException
        map.put("", newElement);
    }

    @DataProvider(name = "unmodifiableMapTestData")
    private static Object[][] unmodifiableMapTestData() {
        return new Object[][]{
                // Map
                {Collections.unmodifiableMap(Maps.<String, String>newHashMap()), StringUtils.EMPTY},
                {Collections.unmodifiableMap(Maps.<String, String>newLinkedHashMap()), StringUtils.EMPTY},
                {Collections.unmodifiableMap(new WeakHashMap<String, String>(1)), StringUtils.EMPTY},
                {Collections.unmodifiableMap(Maps.<String, String>newIdentityHashMap()), StringUtils.EMPTY},
                {Collections.unmodifiableMap(Maps.newEnumMap(MapEnum.class)), MapEnum.NONE},
                // ConcurrentMap
                {Collections.unmodifiableMap(Maps.<String, String>newConcurrentMap()), StringUtils.EMPTY}, // ConcurrentHashMap
                {Collections.unmodifiableMap(new ConcurrentHashMap<String, String>(1)), StringUtils.EMPTY},
                // SortedMap
                // NavigableMap
                {Collections.unmodifiableNavigableMap(new ConcurrentSkipListMap<String, String>()), StringUtils.EMPTY},
                {Collections.unmodifiableNavigableMap(Maps.<String, String>newTreeMap()), StringUtils.EMPTY},
        };
    }

    private enum MapEnum {
        NONE
    }


    // Synchronization Wrappers (同步包装器)

}
