package io.dorum.screenplay.questions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.webelements.Wait;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class VisibilityOf implements Question<Boolean> {
    private final By locator;

    public VisibilityOf(By locator) {
        this.locator = locator;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.interactsWith(Wait.untilVisible(locator));
        boolean isDisplayed = actor.abilityTo(CreateDriver.class).getWebdriver().findElement(locator).isDisplayed();
        log.info("{} is visible: {}", locator, isDisplayed);
        return isDisplayed;
    }

    public static VisibilityOf element(By locator) {
        return new VisibilityOf(locator);
    }
}
