package org.slf4j;

import org.testng.annotations.Test;

/**
 * Test for {@link Logger}.
 *
 * @author xingle
 * @since 1.0
 */
public class LoggerTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    private void throwException1() {
        try {
            this.throwException2();
        } catch (Exception e) {
            throw new IllegalStateException("throwException1", e);
        }
    }

    private void throwException2() {
        throw new RuntimeException("throwException2");
    }

    @Test
    public void error() {
        try {
            this.throwException1();
        } catch (Exception e) {
            logger.error("call '{}' failed", "error", e);
        }
    }

}
