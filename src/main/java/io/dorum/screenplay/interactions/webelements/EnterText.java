package io.dorum.screenplay.interactions.webelements;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.Interaction;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class EnterText implements Interaction<Void> {
    private final By locator;
    private final String text;

    public EnterText(By locator, String text) {
        this.locator = locator;
        this.text = text;
    }

    @Override
    public void performAs(Actor actor) {
        actor.interactsWith(Wait.untilClickable(locator));
        actor.abilityTo(CreateDriver.class).getWebdriver().findElement(locator).sendKeys(text);
    }

    public static EnterText in(By locator, String text) {
        log.info("Inserting text {}", text);
        return new EnterText(locator, text);
    }
}
