package io.dorum.screenplay.interactions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.BrowseTheWeb;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import utils.WaitUtils;

@Log4j2
public class ClickElement implements Interaction<Void> {
    private final By locator;

    public ClickElement(By locator) {
        this.locator = locator;
    }

    @Override
    public void performAs(Actor actor) {
        new WaitUtils(actor.abilityTo(BrowseTheWeb.class).getDriver()).waitForElementToBeClickable(locator).click();
    }

    public static ClickElement on(By locator) {
        log.info("Clicking on locator {}", locator);
        return new ClickElement(locator);
    }
}