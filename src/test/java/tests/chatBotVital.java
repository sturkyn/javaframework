package tests;

import qa.base.BaseTest;
import qa.utils.Assert;
import qa.pageobject.chatBot;
import qa.pageobject.TeamSupportHomePage;
import org.testng.annotations.Test;

import static qa.utils.PageObjectSupplier.*;

public class chatBotVital extends BaseTest {

    @Test()
    public void chatBotOpenCloseTest() {
        loadSiteUrl("https://www.teamsupport.com/")
                .allowCookies();
        $(chatBot.class)
                .openChatBot();
        Assert.assertEquals($(chatBot.class).chatBotMsgCheck(),
                "Looking to grow customer revenue with better customer experiences? We can help!", "Checking chat bot greeting message!");
    }

}
