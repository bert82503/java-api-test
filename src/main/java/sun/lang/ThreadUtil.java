package sun.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 线程辅助类。
 *
 * @author dannong
 * @since 2016年12月02日 21:09
 * @see java.util.concurrent.TimeUnit
 * @see java.lang.Thread
 */
public class ThreadUtil {

    private static final Logger logger = LoggerFactory.getLogger(ThreadUtil.class);

    private ThreadUtil() {
        // prevent to instantiate
    }

    /**
     * 暂停线程。
     *
     * @param timeout 超时秒数
     * @see TimeUnit#sleep(long)
     * @see Thread#sleep(long, int)
     */
    public static void sleepSeconds(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            logger.error("Thread sleep error", e);
        }
    }

}
