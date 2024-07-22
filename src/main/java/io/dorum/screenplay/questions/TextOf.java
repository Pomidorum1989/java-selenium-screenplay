package io.dorum.screenplay.questions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.webelements.Wait;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class TextOf implements Question<String> {
    private final By locator;

    public TextOf(By locator) {
        this.locator = locator;
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.interactsWith(Wait.untilVisible(locator));
        String text = actor.abilityTo(CreateDriver.class).getWebdriver().findElement(locator).getText();
        log.info("{} text was returned", text);
        return text;
    }

    public static TextOf element(By locator) {
        return new TextOf(locator);
    }
}
