package io.dorum.screenplay.abilities;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Getter
@Log4j2
public class BrowseTheWeb implements Ability {

    private final WebDriver driver;

    private BrowseTheWeb(WebDriver driver) {
        this.driver = driver;
    }

    public static BrowseTheWeb with(WebDriver driver) {
//        ThreadLocalWebDriver.setDriver(driver);
        return new BrowseTheWeb(driver);
    }
}
