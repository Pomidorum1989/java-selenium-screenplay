package io.dorum.screenplay.interactions.webelements;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.Interaction;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import reportportal.RPLogUtils;

import java.io.File;
import java.io.IOException;

@Log4j2
@AllArgsConstructor
public class TakeScreenshot implements Interaction<Void> {

    private final String fileName;

    @Override
    public void performAs(Actor actor) {
        try {
            actor.abilityTo(CreateDriver.class);
            TakesScreenshot scrShot = ((TakesScreenshot) actor.abilityTo(CreateDriver.class).getWebdriver());
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(String.format(System.getProperty("user.dir") + "/build/screenshots/%s.png", fileName));
            FileUtils.copyFile(srcFile, destFile);
            RPLogUtils.log(srcFile, fileName);
            log.info("Screenshot was created {}", fileName);
        } catch (IOException e) {
            log.error("Unable to take screenshot {}\n{}", fileName, e.getMessage());
        }
    }

    public static TakeScreenshot of(String fileName) {
        return new TakeScreenshot(fileName);
    }
}
