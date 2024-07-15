package io.dorum.screenplay.tasks;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class CloseBrowser implements Task<Void> {
    @Override
    public void performAs(Actor actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        if (Objects.nonNull(driver)) {
            driver.close();
            driver.quit();
        }
    }

    public static CloseBrowser permanently() {
        return new CloseBrowser();
    }
}
