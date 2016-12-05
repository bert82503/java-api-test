package org.openqa.selenium;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

/**
 * 浏览器测试基类。
 *
 * @author dannong
 * @since 2016年12月05日 17:21
 */
public abstract class BrowserTest {

    /**
     * 驱动服务
     */
    protected static DriverService driverService;

    /**
     * Web驱动
     */
    protected WebDriver webDriver;

    protected abstract DriverService.Builder newDriverServiceBuilder();

    protected abstract String getWebDriverPath();

    protected abstract String getLogFilePath();

    protected abstract int getPort();

    protected abstract void createDriver();

    protected abstract void quitDriver();


    @BeforeClass(alwaysRun = true)
    public void createAndStartService() throws IOException {
        DriverService.Builder builder = newDriverServiceBuilder();
        if (StringUtils.isNotEmpty(getWebDriverPath())) {
            builder.usingDriverExecutable(new File(getWebDriverPath()));
        }
        if (StringUtils.isNotEmpty(getLogFilePath())) {
            builder.withLogFile(new File(getLogFilePath()));
        }
        if (NumberUtils.compare(getPort(), 0) > 0) {
            builder.usingPort(getPort());
        } else {
            builder.usingAnyFreePort();
        }

        driverService = builder.build();
        driverService.start();

//        createDriver();
    }

    @AfterClass(alwaysRun = true)
    public void createAndStopService() {
//        quitDriver();

        if (driverService != null) {
            driverService.stop();
        }
    }

    @BeforeMethod
    public void setUp() {
        createDriver();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        quitDriver();
    }

}
