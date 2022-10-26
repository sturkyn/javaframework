package tests;

import qa.base.BaseTest;
import qa.utils.Assert;
import qa.pageobject.genericPageObject;
import org.testng.annotations.Test;

import static qa.utils.PageObjectSupplier.*;

public class genericTestClass extends BaseTest {

    @Test()
    public void chatBotOpenCloseTest() {
        loadSiteUrl("https://www.teamsupport.com/")
                .allowCookies();
        $(genericPageObject.class)
                .openChatBot();
        Assert.assertEquals($(genericPageObject.class).chatBotMsgCheck(),
                "Looking to grow customer revenue with better customer experiences? We can help!", "Checking chat bot greeting message!");
    }

}
