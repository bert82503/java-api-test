package org.openqa.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 基于Selenium的谷歌Chrome浏览器测试。
 *
 * @author dannong
 * @since 2016年12月05日 17:20
 * @see ChromeDriverService
 * @see org.openqa.selenium.chrome.ChromeDriver
 */
public class ChromeTest extends BrowserTest {

    @Override
    protected DriverService.Builder newDriverServiceBuilder() {
        return new ChromeDriverService.Builder();
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
        // UnreachableBrowserException: Error communicating with the remote browser. It may have died.
//        webDriver = new RemoteWebDriver(
//                driverService.getUrl(), DesiredCapabilities.chrome());

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        webDriver = new ChromeDriver(capabilities);
        webDriver = new ChromeDriver((ChromeDriverService) driverService, options);
    }

    @Override
    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit(); // UnreachableBrowserException: Error communicating with the remote browser. It may have died.
        }
    }

    @Test
    public void search() {
        webDriver.get("http://cn.bing.com");
        WebElement searchBox = webDriver.findElement(By.id("sb_form_q"));
        searchBox.sendKeys("浏览器驱动");
        WebElement searchSubmit = webDriver.findElement(By.id("sb_form_go"));
        searchSubmit.submit();
        assertThat(webDriver.getTitle()).isEqualTo("浏览器驱动 - 必应");

//        webDriver.get("https://www.baidu.com");
//        searchBox = webDriver.findElement(By.id("kw"));
//        searchBox.sendKeys("浏览器驱动");
//        searchSubmit = webDriver.findElement(By.id("su"));
//        searchSubmit.submit();
//        assertThat(webDriver.getTitle()).isEqualTo("浏览器驱动_百度搜索"); // 标题异步刷新
    }
}
