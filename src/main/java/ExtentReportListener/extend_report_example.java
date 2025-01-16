package ExtentReportListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class extend_report_example {
    private ExtentReports extent;
    private ExtentTest logger;
    WebDriver driver;
    @BeforeMethod
    public void startReport() {

        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver_win.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // Initialize ExtentReports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/aditya.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Create a test case

    }

    @Test(priority = 1,enabled = true)
    public void get_title(){
        ExtentTest test = extent.createTest("To verify Register Title");
        driver.get("https://demo.automationtesting.in/Register.html");
        Assert.assertEquals(driver.getTitle(),"Register");
        test.pass("Title passed successfully.");

    }
    @Test(priority = 2,enabled = true)
    public void image_present(){
        ExtentTest test = extent.createTest("To verify Image on the Register page");
        driver.get("https://demo.automationtesting.in/Register.html");
        Boolean img = driver.findElement(By.xpath("//img[@alt='image not displaying']")).isDisplayed();
        Assert.assertTrue(img);
        test.pass("To verify Image passed successfully.");

    }
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Test Case Failed: " + result.getName());
            logger.log(Status.FAIL, "Test Case Failed Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        }
        driver.quit();
    }
    @AfterTest
    public void endReport() {
        extent.flush();
    }
}
