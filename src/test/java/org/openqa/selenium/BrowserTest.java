package org.openqa.selenium;

import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

/**
 * 浏览器测试基类。
 *
 * @author dannong
 * @since 2016年12月05日 17:21
 */
public abstract class BrowserTest {

    private static final Logger logger = LoggerFactory.getLogger(BrowserTest.class);

    /**
     * Web驱动
     */
    protected WebDriver webDriver;

    protected abstract DriverService newDriverService();

    protected abstract String getWebDriverPath();

    protected abstract String getLogFilePath();

    protected abstract int getPort();

    protected abstract void createDriver();

    // 性能剖析
    protected StopWatch stopWatch;

    protected abstract StopWatch newStopWatch();

    private void quitDriver() {
        if (webDriver != null) {
            webDriver.quit(); // UnreachableBrowserException: Error communicating with the remote browser. It may have died.
        }
    }


    @BeforeClass(alwaysRun = true)
    public void init() throws IOException {
        stopWatch = newStopWatch();

        createDriver();
    }

    @AfterClass(alwaysRun = true)
    public void destroy() {
        stopWatch.start("quitDriver");
        this.quitDriver();
        stopWatch.stop();

        logger.debug("{}", stopWatch);
    }

//    @BeforeMethod
//    public void setUp() {
//        createDriver();
//    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown() throws Exception {
//        quitDriver();
//    }

}
