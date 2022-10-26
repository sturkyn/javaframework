package qa.pageobject;

import org.openqa.selenium.By;
import qa.base.BasePage;

public class genericPageObject extends BasePage {

    protected final By chatBotPopUp = By.id("transparent-button");
    protected final By chatBotIFrame = By.xpath("//iframe[@id='designstudio-iframe']");
    protected final By chatBotCloseBtn = By.xpath("//div[@id='icon-close']");
    protected final By chatBotMinBtn = By.xpath("//div[@id='icon-minimize']");
    protected final By chatBotDrag = By.id("draggable");
    protected final By chatBotMsg = By.xpath("//div[@class='message-text']");

    public genericPageObject openChatBot() {
        dirtyWait(2000);
        waitElementToBeClickable(chatBotPopUp);
        clickOnElement("Opening chatBot", chatBotPopUp);
        switchToChatBotIFrame(chatBotIFrame);
        return this;
    }

    public genericPageObject closeChatBot() {
        clickOnElement("Closing chat bot", chatBotCloseBtn);
        return this;
    }

    public genericPageObject minimizeChatBot() {
        clickOnElement("Minimizing chatbot", chatBotMinBtn);
        return this;
    }

    public String chatBotMsgCheck() {
        waitForPresenceOfElement(chatBotMsg);
        return getElementText(chatBotMsg);
    }

}
