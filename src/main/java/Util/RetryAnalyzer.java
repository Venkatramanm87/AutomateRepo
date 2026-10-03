package Util;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxRetryCount = 2; // Retry up to 2 times

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetryCount) {
            count++;
            return true;
        }
        return false;
    }
}