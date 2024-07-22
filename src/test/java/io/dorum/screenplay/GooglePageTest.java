package io.dorum.screenplay;

import io.dorum.screenplay.interactions.webelements.ClickElement;
import io.dorum.screenplay.interactions.webelements.OpenBrowser;
import io.dorum.screenplay.pages.MainPage;
import io.dorum.screenplay.pages.SignInPage;
import io.dorum.screenplay.questions.QueryDB;
import io.dorum.screenplay.questions.VisibilityOf;
import io.dorum.screenplay.tasks.SignInToGoogleAccount;
import org.testng.annotations.Test;
import utils.Config;

import static org.testng.Assert.assertTrue;

public class GooglePageTest extends TestBase {

    @Test(description = "Google login test", priority = 1)
    public void googleLoginTest() {
        Actor.getActor().interactsWith(OpenBrowser.at(Config.getUrl()))
                .interactsWith(ClickElement.on(MainPage.SIGH_IN_BTN));
        Boolean isTextVisible = Actor.getActor().asksFor(VisibilityOf.element(SignInPage.USE_GOOGLE_ACCOUNT_TEXT));
        assertTrue(isTextVisible, "Google account text is not visible");
    }

    @Test(description = "Google email login test", priority = 2)
    public void googleLoginNegativeTest() {
        String userName = Actor.getActor().asksFor(QueryDB.selectEmailByName("Valentine"));
        Actor.getActor().attemptsTo(SignInToGoogleAccount.using(userName));
        Boolean isTextVisible = Actor.getActor().asksFor(VisibilityOf.element(SignInPage.COULD_NOT_SIGH_IN_TEXT));
        assertTrue(isTextVisible, "Pass key message text is not visible");
    }
}
