package io.dorum.screenplay.tasks;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.BrowseTheWeb;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OpenBrowser implements Task<Void> {
    private final String url;

    public OpenBrowser(String url) {
        this.url = url;
    }

    @Override
    public void performAs(Actor actor) {
        actor.abilityTo(BrowseTheWeb.class).getDriver().get(url);
        log.info("Website {} was opened", url);
    }

    public static OpenBrowser at(String url) {
        return new OpenBrowser(url);
    }
}
