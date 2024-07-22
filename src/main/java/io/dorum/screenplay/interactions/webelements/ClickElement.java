package io.dorum.screenplay.interactions.webelements;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.Interaction;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class ClickElement implements Interaction<Void> {
    private final By locator;

    public ClickElement(By locator) {
        this.locator = locator;
    }

    @Override
    public void performAs(Actor actor) {
        actor.interactsWith(Wait.untilClickable(locator));
        actor.abilityTo(CreateDriver.class).getWebdriver().findElement(locator).click();
    }

    public static ClickElement on(By locator) {
        log.info("Clicking on locator {}", locator);
        return new ClickElement(locator);
    }
}