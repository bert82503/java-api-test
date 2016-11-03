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
        // 1. 只打印异常信息日志
        try {
            this.throwException2();
        } catch (Exception e) {
            logger.error("Error happens", e);
        }

        // 2. 同时打印参数和异常信息日志
        try {
            this.throwException1();
        } catch (Exception e) {
            // 异常实例：必须放在最后一个参数，这样就永远不会被占位符替换
            // 占位符 {} 只能用参数替换，不能用异常实例替换，SLF4J 做了特殊处理
            logger.error("call '{}' failed: {} {}", "error", e); // 第二个之后的占位符 未被替换
        }
    }

}
