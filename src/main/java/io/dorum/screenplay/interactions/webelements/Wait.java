package io.dorum.screenplay.interactions.webelements;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.Interaction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
public class Wait implements Interaction<Void> {

    private final By locator;
    private final String conditionType;

    private Wait(By locator, String conditionType) {
        this.locator = locator;
        this.conditionType = conditionType;
    }
    @Override
    public void performAs(Actor actor) {
        WebDriver driver = actor.abilityTo(CreateDriver.class).getWebdriver();
        FluentWait<WebDriver> wait = createFluentWait(driver);
        switch (conditionType) {
            case "clickable":
                wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            case "visible":
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            case "invisible":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
                break;
            case "present":
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;
            default:
                throw new IllegalArgumentException("Invalid condition type");
        }
    }

    public static Wait untilClickable(By locator) {
        return new Wait(locator, "clickable");
    }

    public static Wait untilVisible(By locator) {
        return new Wait(locator, "visible");
    }

    private FluentWait<WebDriver> createFluentWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);
    }
}