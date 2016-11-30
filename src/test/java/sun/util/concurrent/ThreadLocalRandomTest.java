package sun.util.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link ThreadLocalRandom}.
 *
 * @author xingle
 * @since 1.0
 */
public class ThreadLocalRandomTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalRandomTest.class);

    @Test(dataProvider = "nextIntTestData")
    public void nextInt(int minBonus, int maxBonus, int size) {
        // 多线程设计：更高效的随机数生成器的并发实现
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int bound = maxBonus + 1;
        int initialCapacity = bound - minBonus;
        // 统计随机值分布：<randomValue, count>
        Map<Integer, Integer> randomValueCounter = new HashMap<>(initialCapacity);
        for (int i = 0; i < size; i++) {
            // 生成随机值
            int randomValue = random.nextInt(minBonus, maxBonus + 1); // 左闭右开：[,)

            Integer count = randomValueCounter.get(randomValue);
            if (count == null) {
                randomValueCounter.put(randomValue, 1);
            } else {
                randomValueCounter.put(randomValue, count + 1);
            }
        }
        logger.debug("randomValueCounter: {}", randomValueCounter);

        // 校验随机值分布及总个数的正确性
        int totalSum = 0;
        for (Entry<Integer, Integer> entry : randomValueCounter.entrySet()) {
            int randomValue = entry.getKey();
            assertThat(randomValue).isGreaterThanOrEqualTo(minBonus);
            assertThat(randomValue).isLessThanOrEqualTo(maxBonus);

            totalSum += entry.getValue();
        }
        assertThat(totalSum).isEqualTo(size);
    }

    private static int getRandomNumber(int size) {
        // 多线程设计：更高效的随机数生成器的并发实现
        return ThreadLocalRandom.current().nextInt(0, size);
    }

    @Test
    public void getRandomNumber() {
        int size = 10;
        for (int i = 0; i < 100000; i++) {
            int randomNumber = getRandomNumber(size);
            assertThat(randomNumber).isGreaterThanOrEqualTo(0);
            assertThat(randomNumber).isLessThan(10);
        }
    }

    @DataProvider(name = "nextIntTestData")
    private static Object[][] nextIntTestData() {
        return new Object[][]{
                {1, 5, 100},
        };
    }

}
