package ExtentReportListener;


import org.testng.ITest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyITestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Executed when a test method starts
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Executed when a test method is successful
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Executed when a test method fails
        System.out.println("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Executed when a test method is skipped
        System.out.println("Test Skipped: " + result.getName());
    }
}

