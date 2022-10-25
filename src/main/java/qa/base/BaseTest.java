package qa.base;

import io.qameta.allure.Attachment;
import qa.utils.PropertiesUtils;
import qa.utils.log.Reporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {
    private static final ThreadLocal<WebDriver> WEB_DRIVER_CONTAINER = new ThreadLocal<>();
    private WebDriver driver;

    public static WebDriver getDriver() {
        return WEB_DRIVER_CONTAINER.get();
    }

    @BeforeMethod
    public void runOn() {
        Reporter.log("STARTING BROWSER");
        //Config launching app
        createDriver(); //launch browser using webdriver manager
    }

    @BeforeMethod
    public void setup(Method method) {
        //Extent report config
        String testName = method.getName();
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
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            WEB_DRIVER_CONTAINER.remove();
        }
    }
    public void createDriver() {

                ChromeOptions options = new ChromeOptions();
                options.addArguments(
                        "--lang=en",
                        "--window-size=2000,1500",
                        "--disable-extensions",
                        "--disable-dev-shm-usage",
                        "--verbose",
                        "--disable-web-security",
                        "--ignore-certificate-errors",
                        "--allow-running-insecure-content",
                        "--allow-insecure-localhost",
                        "--no-sandbox",
                        "--disable-gpu"
                );
                //options.addArguments("--headless");
                options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.5249.119 Safari/537.36\"");
                //System.out.println(WebDriverManager.chromedriver().getDriverVersions()); 106.0.5249.21, 106.0.5249.61, 107.0.5304.18, 107.0.5304.62
                WebDriverManager.chromedriver().driverVersion("106.0.5249.61").setup();
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                WEB_DRIVER_CONTAINER.set(driver);

    }

}
