
package Testcases;
import ExtentReportListener.MyITestListener;
import Pageobjects.BuyAndSell;
import Pageobjects.HomePage;
import Pageobjects.SignUp;
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

import java.io.IOException;
import java.util.Optional;




public class HomepageTest extends TestBase {

    HomePage homePage = new HomePage();
    BuyAndSell buysell = new BuyAndSell();
    SignUp signup = new SignUp();

    public HomepageTest() throws IOException {
    }

    @Test(priority = 1, enabled = true)
    public void Homepage() throws InterruptedException {

        ExtentTest test = extent.createTest("To verify GAED homepage Title");
        homePage.homepage();
        test.pass("Title passed successfully.");

    }
    @Test(priority = 1, enabled = true)
    public void BuyandSellbuttonVerify() throws InterruptedException {

        ExtentTest test = extent.createTest("To verify GAED homepage buy/sell button");
        homePage.homepage();
        buysell.validate_BuySell_buttons();
        signup.validate_signup_page();
        test.pass("To verify GAED homepage buy/sell button passed.");

    }
}
