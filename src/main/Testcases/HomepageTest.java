
package Testcases;
import ExtentReportListener.MyITestListener;
import Pageobjects.HomePage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.log.Log;
import org.openqa.selenium.devtools.v119.console.Console;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import baseClass.TestBase;
import java.util.Optional;



@Listeners(MyITestListener.class)
public class HomepageTest extends TestBase {

    HomePage homePage = new HomePage();
    @Test(priority = 1, enabled = true)
    public void Homepage() throws InterruptedException {

        ExtentTest test = extent.createTest("To verify GAED homepage Title");
        homePage.homepage();
        test.pass("Title passed successfully.");

    }
    @Test(priority = 2, enabled = true)
    public void Sign_up_invaild_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify field invalid inpt validation");
        homePage.homepage();
        homePage.invalid_signup_field();
        test.pass("To verify field invalid inpt validation");

    }
    @Test(priority = 3, enabled = true)
    public void Sign_up_emtpy_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify field with empty data");
        homePage.homepage();
        homePage.Empty_signup_field();
        test.pass("To verify field with empty data");

    }
}
