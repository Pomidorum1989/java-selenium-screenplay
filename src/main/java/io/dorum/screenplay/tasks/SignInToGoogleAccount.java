package io.dorum.screenplay.tasks;

import com.epam.reportportal.annotations.Step;
import io.dorum.screenplay.Actor;
import io.dorum.screenplay.interactions.webelements.ClickElement;
import io.dorum.screenplay.interactions.webelements.EnterText;
import io.dorum.screenplay.interactions.webelements.OpenBrowser;
import io.dorum.screenplay.pages.MainPage;
import io.dorum.screenplay.pages.SignInPage;
import lombok.AllArgsConstructor;
import utils.Config;

@AllArgsConstructor
public class SignInToGoogleAccount implements Task<Void> {

    private String email;

    @Override
    @Step("Signing in to google")
    public void performAs(Actor actor) {
        Actor.getActor().interactsWith(OpenBrowser.at(Config.getUrl()))
                .interactsWith(ClickElement.on(MainPage.SIGH_IN_BTN))
                .interactsWith(EnterText.in(SignInPage.EMAIL_INPUT_FIELD, email))
                .interactsWith(ClickElement.on(SignInPage.NEXT_BTN));
    }

    public static SignInToGoogleAccount using(String email) {
        return new SignInToGoogleAccount(email);
    }

}
