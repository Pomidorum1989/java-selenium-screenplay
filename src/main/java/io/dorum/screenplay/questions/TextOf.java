package io.dorum.screenplay.questions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.BrowseTheWeb;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

@Log4j2
public class TextOf implements Question<String> {
    private By locator;

    public TextOf(By locator) {
        this.locator = locator;
    }

    @Override
    public String answeredBy(Actor actor) {
        WebElement element = new WaitUtils(actor.abilityTo(BrowseTheWeb.class).getDriver()).waitForElementToBeVisible(locator);
        String text = element.getText();
        log.info("{} text was returned", text);
        return text;
    }

    public static TextOf element(By locator) {
        return new TextOf(locator);
    }
}
