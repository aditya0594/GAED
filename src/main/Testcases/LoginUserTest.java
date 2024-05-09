package Testcases;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

public class LoginUserTest extends TestBase {

    HomePage homePage = new HomePage();
    LoginConsumer Loginuser = new LoginConsumer();


    @Test(priority = 1,enabled = true)
    public void VerifyInvalidEmail() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the invalid email");
        homePage.homepage();
        LoginConsumer.InvalidLoginInputs();
        test.pass("To verify the invalid email passed successfully.");
    }
    @Test(priority = 2,enabled = true)
    public void VerifyEmptyEmail() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the Empty email");
        homePage.homepage();
        LoginConsumer.EmptyEmail();
        test.pass("To verify the Empty email passed successfully.");
    }
   @Test(priority = 3,enabled = true)
    public void VerifyEmptyNotExist() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the email is not signed up");
        homePage.homepage();
        LoginConsumer.EmailDoesExist();
        test.pass("To verify the email is not signed up passed successfully.");
    }
    @Test(priority = 4,enabled = true)
    public void Verify_SignUp_Link_ontheLogin() throws InterruptedException {
        ExtentTest test = extent.createTest("To Verify the signup link on the login page");
        homePage.homepage();
        LoginConsumer.SignupLinkOnLogin();
        test.pass("To verify the signup link on the login page is clickable and navigate to singup page :  passed successfully.");
    }
    @Test(priority = 5,enabled = true)
    public void verifyLoginConsumer() throws InterruptedException {
        homePage.homepage();
        LoginConsumer.LoginConsumerSuceessful();
        LoginConsumer.Veriy_consumer_profile();
        //LoginUser.OTP_read();
    }

}
