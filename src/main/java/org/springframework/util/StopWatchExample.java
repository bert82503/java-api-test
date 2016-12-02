package org.springframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.lang.ThreadUtil;

/**
 * Example of {@link StopWatch}.
 *
 * @author dannong
 * @since 2016年12月02日 20:54
 */
public class StopWatchExample {

    private static final Logger logger = LoggerFactory.getLogger(StopWatchExample.class);

    private static void performanceStatistic() {
        StopWatch stopWatch = new StopWatch("performanceStatistic");

        stopWatch.start("coreMethod1");
        // Core Method ...
        ThreadUtil.sleepSeconds(1L);
        stopWatch.stop();

        stopWatch.start("coreMethod2");
        // Core Method ...
        ThreadUtil.sleepSeconds(1L);
        stopWatch.stop();

        logger.debug("informative string describing all tasks performed:\n {}\n",
                stopWatch.toString()); // 描述所有执行的任务的耗时信息
        logger.debug("a string with a table describing all tasks performed:\n {}",
                stopWatch.prettyPrint());
    }

    public static void main(String[] args) {
        performanceStatistic();
    }

}
