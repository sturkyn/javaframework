package qa.utils.log;

import qa.base.BaseTest;
import qa.utils.PropKeys;
import qa.utils.PropertiesUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Reporter {

    private static final Logger LOGGER = log;
    private static String LAST_STEP = "";

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private static void logging(String message, String consoleColor) {
        System.out.println(consoleColor + message + ConsoleColors.RESET);
        logAllureStep(message);
    }
    public static void log(String message) {
        logging(getStandardLogRowHeader(message), ConsoleColors.GREEN);
    }

    public static void logSystem(String message) {
        if (PropertiesUtils.getProp(PropKeys.SYSTEM_LOGS.getPropName()).equals("true"))
            logging(getStandardLogRowHeader(message), ConsoleColors.BLUE);
    }

    public static void logAssert(String message) {
        logging(getStandardLogRowHeader(message), ConsoleColors.YELLOW);
    }

    public static void logFailedStep() {
        logging("FAILED STEP - " + LAST_STEP, ConsoleColors.RED);
    }

    public static void logFailedStep(String message) {
        logging(getStandardLogRowHeader(message), ConsoleColors.RED);
    }

    @Step("{0}")
    public static void logAllureStep(final String message){
        if (PropertiesUtils.getProp(PropKeys.SCREEN_ON_STEP.getPropName()).equals("true"))
           new BaseTest().screenshot();
        LAST_STEP = message;
    }
    private static String getStandardLogRowHeader(String message) {
        LocalDateTime now = LocalDateTime.now();
        String threadId = Long.toString(Thread.currentThread().getId());
        String testName = Thread.currentThread().getName();
        testName = testName != null ? testName.split("_")[0] : "";
        return "[T:" + threadId + "/" + testName + " @ " + dtf.format(now) + "] - " + message;

    }
    public static void logFail(String s) {
        LOGGER.error(s);
    }
    public static void logException(Exception e) {
        logging(getStandardLogRowHeader(e.getMessage()), ConsoleColors.RED);
    }

}
