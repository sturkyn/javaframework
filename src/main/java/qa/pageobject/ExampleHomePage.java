package qa.pageobject;

import org.openqa.selenium.By;
import qa.base.BasePage;

public class ExampleHomePage extends BasePage {

    protected final By cookieConsentAcceptBtn = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    protected final By cookieConsentBanner = By.xpath("//div[@id='onetrust-button-group']");

    public ExampleHomePage allowCookies() {
        waitForPresenceOfElement(cookieConsentBanner);
        clickOnElement("Click on accept cookies", cookieConsentAcceptBtn);
        return this;
    }

}
