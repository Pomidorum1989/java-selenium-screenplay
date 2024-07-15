package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

@Log4j2
public class ThreadLocalWebDriver {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (Objects.isNull(threadLocalDriver.get())) {
            long threadId = Thread.currentThread().threadId();
            WebDriverManager webDriverManager = WebDriverManager.chromedriver().cachePath("./build/driver/" + threadId);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--lang=en-US");
//                    chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--incognito");
            threadLocalDriver.set(webDriverManager.capabilities(chromeOptions).create());
            log.info("Driver {} was created", threadId);
        }
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static void removeDriver() {
        if (Objects.nonNull(threadLocalDriver.get())) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
            log.info("Driver {} was closed", Thread.currentThread().threadId());
        }
    }
}