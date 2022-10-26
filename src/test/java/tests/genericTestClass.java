package tests;

import qa.base.BaseTest;
import qa.utils.Assert;
import qa.pageobject.genericPageObject;
import org.testng.annotations.Test;

import static qa.utils.PageObjectSupplier.*;

public class genericTestClass extends BaseTest {

    @Test()
    public void simpleSearchTest() {
        loadSiteUrl("https://www.google.com/")
                .allowCookies();
        $(genericPageObject.class)
                .typeToSearchBar()
                .clickOnSearchBtn();
        Assert.assertEquals($(genericPageObject.class).searchResultCountCheck(),
                "Ungefähr 33.000.000 Ergebnisse", "Checking search result count");
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
