package io.dorum.screenplay.questions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.BrowseTheWeb;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

@Log4j2
public class VisibilityOf implements Question<Boolean> {
    private By locator;

    public VisibilityOf(By locator) {
        this.locator = locator;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebElement element = new WaitUtils(actor.abilityTo(BrowseTheWeb.class).getDriver()).waitForElementToBeVisible(locator);
        Boolean isDisplayed = element.isDisplayed();
        log.info("{} is visible: {}", locator, isDisplayed);
        return isDisplayed;
    }

    public static VisibilityOf element(By locator) {
        return new VisibilityOf(locator);
    }
}
