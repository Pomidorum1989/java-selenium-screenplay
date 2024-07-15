package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Log4j2
public class BrowserFactory {

    @Getter
    @AllArgsConstructor
    public enum BrowserType {
        CHROME("chrome"),
        FIREFOX("firefox"),
        EDGE("edge");

        private final String text;

        public static BrowserType fromString(String text) {
            for (BrowserType browserType : BrowserType.values()) {
                if (browserType.text.equalsIgnoreCase(text)) {
                    return browserType;
                }
            }
            throw new IllegalArgumentException("Unsupported browser type: " + text);
        }
    }

    public static WebDriver createDriver(String browserType) {
        WebDriver driver;
        WebDriverManager webDriverManager;
        BrowserType browser = BrowserType.fromString(browserType);
        long threadId = Thread.currentThread().threadId();
        switch (browser) {
            case CHROME:
                webDriverManager = WebDriverManager.chromedriver().cachePath("./build/chromedriver/" + threadId);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--lang=en-US");
//                    chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--incognito");
                driver = webDriverManager.capabilities(chromeOptions).create();
                break;
            case FIREFOX:
                webDriverManager = WebDriverManager.firefoxdriver().cachePath("./build/firefoxdriver/" + threadId);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = webDriverManager.capabilities(firefoxOptions).create();
                break;
            case EDGE:
                webDriverManager = WebDriverManager.edgedriver().cachePath("./build/edgedriver/" + threadId);
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = webDriverManager.capabilities(edgeOptions).create();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        log.info("{} driver {} was created", browserType, threadId);
        return driver;
    }
}
