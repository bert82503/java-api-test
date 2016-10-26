package sun.util.stream;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Parallel Streams. (并行流)
 *
 * @author dannong
 * @since 2016年10月26日 11:00
 */
public class ParallelStreamTest {
    private static final Logger logger = LoggerFactory.getLogger(ParallelStreamTest.class);

    private List<String> values;

    @BeforeMethod
    public void setUp() {
        // 1. create a large list of unique elements
        int max = 1000000;
        values = Lists.newArrayListWithExpectedSize(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
    }

    // measure the time it takes to sort a stream of this collection
    @Test(description = "顺序排序")
    public void sequentialSort() {
        long t0 = System.nanoTime();

        long count = values
                .stream()
                .sorted()
                .count();
        assertThat(count).isEqualTo(1000000);

        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        logger.info("sequential sort took: {} ms", millis);
    }

    @Test(description = "并行排序")
    public void parallelSort() {
        long t0 = System.nanoTime();

        long count = values
                .parallelStream()
                .sorted()
                .count();
        assertThat(count).isEqualTo(1000000);

        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        logger.info("parallel sort took: {} ms", millis);
    }
}
