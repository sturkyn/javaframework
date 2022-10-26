package qa.pageobject;

import org.openqa.selenium.By;
import qa.base.BasePage;

public class genericPageObject extends BasePage {

    protected final By searchBar = By.xpath("//div[@class='YacQv gsfi']");
    protected final By searchBtn = By.xpath("//div[@class='gNO89b']");
    protected final By searchResultInfo = By.xpath("//div[@id='result-stats']");

    public genericPageObject typeToSearchBar() {
        clickOnElement("Click on search bar", searchBar);
        typeText("Searching for SCG","SCG",searchBar);
        return this;
    }

    public genericPageObject clickOnSearchBtn() {
        clickOutsideThePage();
        clickOnElement("Click on search button", searchBtn);
        return this;
    }

    public String searchResultCountCheck() {
        waitForPresenceOfElement(searchResultInfo);
        return getElementText(searchResultInfo);
    }

}
