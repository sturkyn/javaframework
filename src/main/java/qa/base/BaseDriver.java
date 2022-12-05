package qa.base;

import qa.utils.PropKeys;
import qa.utils.PropertiesUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BaseDriver {

    private WebDriver dr;
    private String method = "null";
    private boolean initiated = false;
    private String type;
    public BaseDriver(String type) {
        this.type = type;
    }

    public void initiateDriver() {

        initiated = true;
        switch(type) {
            case "chrome":
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
                if (PropertiesUtils.getProp(PropKeys.HEADLESS.getPropName()).equals("true"))
                    options.addArguments("--headless");
                options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.53 Safari/537.36");
                /* Browser versions are hard-coded for CI now, please contact in team slack or DevOps to configure */
                /* -> */WebDriverManager.chromedriver().browserVersion("108.0.5359.71").setup();/* <- */
                /* Do not send PR before making sure the driver version will work on Cloud. TO DO FLOW-8330 */
                dr = new ChromeDriver(options);
                dr.manage().window().maximize();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                dr = new FirefoxDriver();
                break;

            case "safari":
                WebDriverManager.safaridriver().setup();
                dr = new SafariDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                dr = new EdgeDriver();
                break;

            default:
                System.out.print("Failed to setup browser");
        }

    }

    public boolean isInitiated() {
        return initiated;
    }

    public WebDriver getDr() {
        return dr;
    }

    public String getStatus() {
        return method;
    }

    public void setStatus(String method) {
        this.method = method;
    }

}