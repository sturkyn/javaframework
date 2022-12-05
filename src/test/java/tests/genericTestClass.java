package tests;

import qa.base.BaseListener;
import org.testng.annotations.Listeners;
import qa.base.BaseTest;
import qa.utils.Assert;
import qa.pageobject.genericPageObject;
import org.testng.annotations.Test;

import static qa.utils.PageObjectSupplier.*;

@Listeners(BaseListener.class)
public class genericTestClass extends BaseTest {

    @Test()
    public void simpleSearchTest() {
        loadSiteUrl("https://www.trendyol.com/")
                .allowCookies();
        $(genericPageObject.class)
                .typeToSearchBar()
                .clickOnSearchBtn();
        Assert.assertEquals($(genericPageObject.class).searchResultCountCheck(),
                "SCG", "Checking search result name");
    }

    @Test()
    public void simpleSearchAssertTest() {
        loadSiteUrl("https://www.google.com/")
                .allowCookies();
        $(genericPageObject.class)
                .typeToSearchBar()
                .clickOnSearchBtn();
        Assert.assertEquals($(genericPageObject.class).searchResultCountCheck(),
                "Ungefähr 33.000.000 Ergebnisse", "Checking search result count");
    }

}
