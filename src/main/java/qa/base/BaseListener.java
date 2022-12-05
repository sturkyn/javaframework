package qa.base;

import qa.utils.log.Reporter;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.Augmenter;
import org.testng.*;
import java.util.List;

@Slf4j
public class BaseListener implements IInvokedMethodListener, ITestListener {

    public static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.logFailedStep();
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
        new BaseTest().screenshot();
        Reporter.logFailedStep(getLastJavaScriptConsoleError());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Reporter.log("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    public String getLastJavaScriptConsoleError() {
        try {
            String resultError = "Last console error: ";
            WebDriver augmentedDriver = new Augmenter().augment(new BaseTest().getDriver());
            Logs logs = augmentedDriver.manage().logs();
            LogEntries logEntries = logs.get(LogType.BROWSER);
            List<LogEntry> logEntriesList = logEntries.getAll();
            if (logEntriesList.size() > 0) {
                LogEntry lastLogEntry = logEntriesList.get(logEntriesList.size() - 1);
                if (lastLogEntry.getMessage().toLowerCase().contains("error")) {
                    resultError = resultError + lastLogEntry.getMessage();
                } else if (lastLogEntry.getMessage().toLowerCase().contains("warning")) {
                    resultError = resultError + lastLogEntry.getMessage();
                } else {
                    resultError = resultError + lastLogEntry.getMessage();
                }
                return resultError;
            }
            return "No console error found";
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "No console error found";
    }
}
