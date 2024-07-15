package io.dorum.screenplay.interactions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.BrowseTheWeb;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

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
        WebElement element = new WaitUtils(actor.abilityTo(BrowseTheWeb.class).getDriver()).waitForElementToBeVisible(locator);
        element.sendKeys(text);
    }

    public static EnterText in(By locator, String text) {
        log.info("Inserting text {}", text);
        return new EnterText(locator, text);
    }
}
