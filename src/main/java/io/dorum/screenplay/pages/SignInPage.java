package io.dorum.screenplay.pages;

import org.openqa.selenium.By;

public class SignInPage {
    public static final By USE_GOOGLE_ACCOUNT_TEXT = By.xpath("//*[text() = 'Use your Google Account']");
    public static final By NEXT_BTN = By.xpath("//span[text() = 'Next']");
    public static final By WARNING_MESSAGE = By.xpath("//div[text() = 'Enter an email or phone number']");
}
