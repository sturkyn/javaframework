package qa.utils.retryAnalyzer;

import qa.utils.PropKeys;
import qa.utils.PropertiesUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int counter = 0;
    private final int retryLimit = PropertiesUtils.getIntValue(PropKeys.RETRY_LIMIT.getPropName());

    @Override
    public boolean retry(ITestResult result) {

        if(counter < retryLimit)
        {
            counter++;
            return true;
        }
        return false;
    }
}
