package io.dorum.screenplay.interactions.webelements;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.Interaction;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OpenBrowser implements Interaction<Void> {
    private final String url;

    public OpenBrowser(String url) {
        this.url = url;
    }

    @Override
    public void performAs(Actor actor) {
        actor.abilityTo(CreateDriver.class).getWebdriver().get(url);
    }

    public static OpenBrowser at(String url) {
        log.info("Website {} was opened", url);
        return new OpenBrowser(url);
    }
}
