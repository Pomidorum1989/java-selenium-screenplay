package io.dorum.screenplay.interactions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.BrowseTheWeb;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

@Log4j2
@AllArgsConstructor
public class TakeScreenshot implements Interaction<Void> {

    private String fileName;

    @Override
    public void performAs(Actor actor) {
        try {
            String name = "/build/screenshots/" + fileName + ".png";
            File screenshotFile = ((TakesScreenshot) actor.abilityTo(BrowseTheWeb.class).getDriver()).
                    getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            FileHandler.copy(screenshotFile, new File(name));
            log.info("Screenshot was created {}", name);
        } catch (IOException e) {
            log.error("Unable to take screenshot");
        }
    }

    public static TakeScreenshot of(String fileName) {
        return new TakeScreenshot(fileName);
    }
}
