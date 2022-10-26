package qa.utils;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import qa.base.BaseTest;
import qa.pageobject.exampleHomePage;
import qa.utils.log.Reporter;

public class PageObjectSupplier {


    public static <T> T $(Class<T> pageObject) {
        return ConstructorAccess.get(pageObject).newInstance();
    }

    public static exampleHomePage loadSiteUrl(String url) {
        Reporter.log("Opening URL: " + url);
        try {
            BaseTest.getDriver().get(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return $(exampleHomePage.class);
    }

    private PageObjectSupplier() {
    }
}