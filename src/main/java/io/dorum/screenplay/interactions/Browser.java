package io.dorum.screenplay.interactions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

@Log4j2
public class Browser implements Interaction<Void> {

    @Override
    public void performAs(Actor actor) {
        WebDriver driver = actor.abilityTo(CreateDriver.class).getWebdriver();
        if (Objects.nonNull(driver)) {
            driver.close();
            driver.quit();
            log.info("Driver {} was closed", Thread.currentThread().threadId());
        }
    }

    public static Browser close() {
        return new Browser();
    }
}
