package qa.pageobject;

import org.openqa.selenium.By;
import qa.base.BasePage;

public class TeamSupportHomePage extends BasePage {

    protected final By cookieConsentAcceptBtn = By.xpath("//div[@id='hs-eu-cookie-confirmation-button-group']");
    protected final By cookieConsentBanner = By.xpath("//div[@id='hs-eu-cookie-confirmation']");

    public TeamSupportHomePage allowCookies() {
        waitForPresenceOfElement(cookieConsentBanner);
        clickOnElement("Click on accept cookies", cookieConsentAcceptBtn);
        return this;
    }

}
