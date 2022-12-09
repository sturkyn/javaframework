package qa.base;

import org.openqa.selenium.interactions.Actions;
import qa.utils.log.Reporter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BasePage {

    protected final WebDriver driver;

    final FluentWait<WebDriver> wait;
    private final int defaultImplicitlyWaitTime = 40;

    {
        try {
            wait = new FluentWait<>(Objects.requireNonNull(new BaseTest().getDriver()))
                    .withTimeout(Duration.ofSeconds(defaultImplicitlyWaitTime))
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(NoSuchElementException.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BasePage() {
        try {
            driver = BaseTest.getDriver();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement findElement(By element) {
        return driver.findElement(element);
    }

    public void waitForPresenceOfElement(By element) {
        Reporter.logSystem(this.getClass().getSimpleName() + ": " + "Waiting presence of element");
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public boolean isElementPresentWithWait(By element, int timeWaitSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeWaitSeconds));
        try {
            driver.findElement(element).getAttribute("value");
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultImplicitlyWaitTime));
        }
    }

    public void dirtyWait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Reporter.log("Something went wrong with dirtyWait");
        }
    }

    protected void waitForElement(By element) {
        Reporter.logSystem(this.getClass().getSimpleName() + ": " + "Waiting visibility of element");
        wait.until(visibilityOfElementLocated(element));
    }

    protected void waitElementToBeClickable(By element) {
        Reporter.logSystem(this.getClass().getSimpleName() + ": " + "Waiting for element to be clickable");
        wait.until(elementToBeClickable(element));
    }

    private boolean isElementPresent(WebElement element) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        try {
            wait.withTimeout(Duration.ofSeconds(1));
            wait.until(visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultImplicitlyWaitTime));
        }
    }

    protected String getElementText(By element) {
        waitForElement(element);
        String text = findElement(element).getText();
        Reporter.logSystem(this.getClass().getSimpleName() + ": " + "Get element text - " + text);
        return text;
    }

    protected void clickOnElement(String message, By element) {
        Reporter.log(this.getClass().getSimpleName() + ": " + message);
        waitElementToBeClickable(element);
        findElement(element).click();
    }

    public void clickOutsideThePage() {
        Reporter.log(this.getClass().getSimpleName() + ": " + "Click outside the page");
        Actions action = new Actions(driver);
        action.moveByOffset(1, 1).click().build().perform();
    }

    public void typeText(String message, String text, By field) {
        Reporter.log(this.getClass().getSimpleName() + ": " + message + " With data - " + text);
        waitForPresenceOfElement(field);
        findElement(field).sendKeys(text);
    }

    protected void waitForPageLoaded(By element) {
        Reporter.logSystem(this.getClass().getSimpleName() + ": " + "Waiting page to load...");
        Date dateBefore = new Date();
        for (int i = 0; i < 10; i++) {
            if (isElementPresentWithWait(element, 1)) {
                dirtyWait(500);
                continue;
            }
            Date dateAfter = new Date();
            if (i != 0)
                Reporter.logSystem(this.getClass().getSimpleName() + ": " + "Loading Spinner appears " + (dateAfter.getTime() - dateBefore.getTime()) / 1000 + " Seconds");
            break;
        }
        Reporter.logSystem(this.getClass().getSimpleName() + ": " + "Page loaded");
    }

    public void switchToNewlyOpenedTab() {
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        if (windows.size() > 1) {
            driver.switchTo().window(windows.get(1));
        }
    }

    public void switchToTheLatestOpenedTab() {
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
    }

    public void navigateToSpecificUrl(String specURL) {
        Reporter.log("Opening URL: " + specURL);
        driver.navigate().to(specURL);
    }

    public BasePage createNewTab() {
        //creates new tab
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
        //switches to it
        switchToNewlyOpenedTab();
        return this;
    }

}
