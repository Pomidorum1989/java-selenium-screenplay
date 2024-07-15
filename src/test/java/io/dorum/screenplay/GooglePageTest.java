package io.dorum.screenplay;

import io.dorum.screenplay.pages.MainPage;
import io.dorum.screenplay.pages.SignInPage;
import io.dorum.screenplay.interactions.ClickElement;
import io.dorum.screenplay.questions.VisibilityOf;
import io.dorum.screenplay.tasks.OpenBrowser;
import io.dorum.screenplay.tasks.PerformsInteraction;
import org.testng.annotations.Test;
import utils.Config;

import static org.testng.Assert.assertTrue;

public class GooglePageTest extends TestBase {

    @Test(description = "Google login test", priority = 1)
    public void googleLoginTest() {
        getActor().attemptsTo(OpenBrowser.at(Config.getUrl()))
                .attemptsTo(PerformsInteraction.interaction(ClickElement.on(MainPage.SIGH_IN_BTN)));
        Boolean isTextVisible = getActor().asksFor(VisibilityOf.element(SignInPage.USE_GOOGLE_ACCOUNT_TEXT));
        assertTrue(isTextVisible, "Expected text is not visible");
    }

    @Test(description = "Google negative login test", priority = 2)
    public void googleLoginNegativeTest() {
        getActor().attemptsTo(OpenBrowser.at(Config.getUrl()))
                .attemptsTo(PerformsInteraction.interaction(ClickElement.on(MainPage.SIGH_IN_BTN)))
                .attemptsTo(PerformsInteraction.interaction(ClickElement.on(SignInPage.NEXT_BTN)));
        Boolean isTextVisible = getActor().asksFor(VisibilityOf.element(SignInPage.WARNING_MESSAGE));
        assertTrue(isTextVisible, "Expected text is not visible");
    }
}
