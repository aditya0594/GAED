
package Testcases;
import ExtentReportListener.MyITestListener;
import Pageobjects.HomePage;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import baseClass.TestBase;



@Listeners(MyITestListener.class)
public class HomepageTest extends TestBase {

    HomePage homePage = new HomePage();
    @Test(priority = 1, enabled = true)
    public void Homepage() throws InterruptedException {
      // LandingPage.verify_signup_disble_firstname();
    /*   LandingPage.verify_signup_disble_email();
       LandingPage.verify_signup_disble_password();
       LandingPage.password_validation();
       LandingPage.password_eye_btn();
       LandingPage.Sign_up();
       LandingPage.get_started_screen_After_signup();
       LandingPage.hamburger_Account_Setting();
       LandingPage.verify_account_name();*/
        ExtentTest test = extent.createTest("To verify GAED homepage Title");
        homePage.homepage();
        test.pass("Title passed successfully.");



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
