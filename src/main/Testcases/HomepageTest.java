
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
    @Test(priority = 3, enabled = true)
    public void Sign_up_OTP_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify field with empty data");
        homePage.homepage();
        Thread.sleep(5000);
        homePage.Sign_up();
        Thread.sleep(5000);

        // Capture browser console logs
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);

        // Iterate through the log entries
        for (LogEntry entry : logs) {
            System.out.println(entry.getMessage());
        }


        test.pass("To verify field with empty data");

    }
    @Test(priority = 2, enabled = false)
    public void login() throws InterruptedException {
        homePage.login();
        homePage.get_started_screen();
        homePage.hamburger_Account_Setting();
        homePage.verify_account_name();
    }

    @Test(priority = 3, enabled = false)
    public void Ill_Do_it() throws InterruptedException {
        homePage.ill_do_later();
    }
    @Test (priority = 4,enabled = false)
    public void Get_Started_onboarding() throws InterruptedException {
       // LandingPage.ill_do_later();
        homePage.get_started_screen_AfterIlldoit_signup();
    }


}
