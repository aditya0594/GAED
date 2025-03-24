package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalysor implements IRetryAnalyzer {

    private int count =0;
    private static int maxTry =2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(!iTestResult.isSuccess()){
            if(count<maxTry){
                count++;
                iTestResult.getStatus();
                return true;
            }
            else {
                iTestResult.setStatus(iTestResult.FAILURE);
            }
        }
        return false;
    }
}
