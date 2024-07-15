package io.dorum.screenplay.tasks;

import io.dorum.screenplay.Actor;
import utils.ThreadLocalWebDriver;

public class CloseBrowser implements Task<Void> {
    @Override
    public void performAs(Actor actor) {
        ThreadLocalWebDriver.removeDriver();
    }

    public static CloseBrowser permanently() {
        return new CloseBrowser();
    }
}
