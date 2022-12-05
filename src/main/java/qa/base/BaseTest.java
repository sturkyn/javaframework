package qa.base;

import qa.utils.PropertiesUtils;
import qa.utils.log.Reporter;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Listeners({BaseListener.class})
public class BaseTest {
    private static final Map<Long, BaseDriver> threadSwitch = new HashMap<>();
    public static WebDriver getDriver() throws TestException {
        Long threadId = Thread.currentThread().getId();
        BaseDriver baseD = null;
        synchronized (threadSwitch) {
            baseD = threadSwitch.get(threadId);
        }
        if (baseD == null) {
            throw new TestException("Webdriver is NULL"); // NULL driver check, ideally this shouldn't happen!
        }
        return baseD.getDr();
    }

    private static BaseDriver createDriver(String browserName, Long threadId) {
        BaseDriver baseD = new BaseDriver(browserName);
        synchronized (threadSwitch) {
            threadSwitch.put(threadId, baseD);
        }

        if (!baseD.isInitiated())
            baseD.initiateDriver();

        return baseD;
    }

    @BeforeMethod
    public void runOn() {
        String browserName = PropertiesUtils.getProp("browserName");
        Reporter.log("STARTING BROWSER -" + browserName);
        //Config launching app
        createDriver(browserName, Thread.currentThread().getId()); //launch browser using webdriver manager
    }

    @BeforeMethod
    public static void setup(Method method) {
        //Extent report config
        String testName = method.getName();
        Thread.currentThread().setName(testName);
        Reporter.log("Method -" + testName + " - is started.");
        Reporter.log("---------------------------------------------------------------------------------------------");
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        try {
            WebDriver currentDriver = getDriver();
            if (currentDriver != null) {
                return ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception e) {
            Reporter.logFail("Screenshot could not be taken!");
        }
        return new byte[0];
    }

    @AfterMethod(alwaysRun = true)
    public void closeWindow(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Reporter.logFail("Test FAILED");
            screenshot();
        } else if (result.getStatus() == ITestResult.SKIP)
            screenshot();
        else
            screenshot();
        Reporter.log("Test COMPLETED.");
    }

    @AfterMethod
    public void tearDownDriver() throws TestException {
        try {
            WebDriver currentDriver = getDriver();
            if (currentDriver != null) {
                currentDriver.close();
                currentDriver.quit();
            }
        } catch (Exception e) {
            Reporter.logFail("Driver could not be closed.");
        }

    }

    @AfterSuite
    public void tearDownChrome() {
        try {
            ProcessBuilder process = new ProcessBuilder();
            process.command("pkill Chrome");
            process.command("pkill chromedriver");
        } catch (Exception e) {
            Reporter.logFail("Bash command could not be executed.");
        }
    }

}
