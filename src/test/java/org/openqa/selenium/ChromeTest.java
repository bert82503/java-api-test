package org.openqa.selenium;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.util.StopWatch;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.*;

/**
 * 基于Selenium的谷歌Chrome浏览器测试。
 *
 * StopWatch 'ChromeTest': running time (millis) = 4706;
 * [newDriverService] took 45 = 1%;
 * [createDriver] took 1760 = 37%;
 * [startPage-1] took 1313 = 28%; [startPage-2] took 816 = 17%; [startPage-3] took 692 = 15%;
 * [quitDriver] took 80 = 2%
 *
 * @author dannong
 * @since 2016年12月05日 17:20
 * @see ChromeDriverService
 * @see org.openqa.selenium.chrome.ChromeDriver
 */
public class ChromeTest extends BrowserTest {

    @Override
    protected StopWatch newStopWatch() {
        return new StopWatch("ChromeTest");
    }

    @Override
    protected ChromeDriverService newDriverService() {
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
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
        return builder.build();
    }

    @Override
    protected String getWebDriverPath() {
        return "browserdriver/chrome/chromedriver";
    }

    @Override
    protected String getLogFilePath() {
        return "log/chrome.log";
    }

    @Override
    protected int getPort() {
        return 0;
    }

    @Override
    public void createDriver() {
        stopWatch.start("newDriverService");
        ChromeDriverService driverService = newDriverService();
        stopWatch.stop();

        // UnreachableBrowserException: Error communicating with the remote browser. It may have died.
//        webDriver = new RemoteWebDriver(
//                driverService.getUrl(), DesiredCapabilities.chrome());

        stopWatch.start("createDriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        webDriver = new ChromeDriver(capabilities);
        webDriver = new ChromeDriver(driverService, options);
        stopWatch.stop();
    }

    @Test
    public void search() {
        stopWatch.start("startPage-1");
        webDriver.get("http://cn.bing.com");
        WebElement searchBox = webDriver.findElement(By.id("sb_form_q"));
        searchBox.sendKeys("浏览器驱动");
        WebElement searchSubmit = webDriver.findElement(By.id("sb_form_go"));
        searchSubmit.submit();
        assertThat(webDriver.getTitle()).isEqualTo("浏览器驱动 - 必应");
        stopWatch.stop();

        stopWatch.start("startPage-2");
        webDriver.get("http://cn.bing.com");
        searchBox = webDriver.findElement(By.id("sb_form_q"));
        searchBox.sendKeys("自动化浏览器");
        searchSubmit = webDriver.findElement(By.id("sb_form_go"));
        searchSubmit.submit();
        assertThat(webDriver.getTitle()).isEqualTo("自动化浏览器 - 必应");
        stopWatch.stop();
    }

    @Test
    public void search2() {
        stopWatch.start("startPage-3");
        webDriver.get("http://cn.bing.com");
        WebElement searchBox = webDriver.findElement(By.id("sb_form_q"));
        searchBox.sendKeys("自动化浏览器");
        WebElement searchSubmit = webDriver.findElement(By.id("sb_form_go"));
        searchSubmit.submit();
        assertThat(webDriver.getTitle()).isEqualTo("自动化浏览器 - 必应");
        stopWatch.stop();
    }

}
