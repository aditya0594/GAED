package Testcases;

import Pageobjects.HomePage;
import Pageobjects.LoginUser;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import lombok.extern.java.Log;
import org.testng.annotations.Test;

public class LoginUserTest extends TestBase {

    HomePage homePage = new HomePage();
    LoginUser Loginuser = new LoginUser();


    @Test(priority = 1,enabled = true)
    public void VerifyInvalidEmail() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the invalid email");
        homePage.homepage();
        LoginUser.InvalidLoginInputs();
        test.pass("To verify the invalid email passed successfully.");
    }
    @Test(priority = 2,enabled = true)
    public void VerifyEmptyEmail() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the Empty email");
        homePage.homepage();
        LoginUser.EmptyEmail();
        test.pass("To verify the Empty email passed successfully.");
    }
    @Test(priority = 3,enabled = true)
    public void VerifyEmptyNotExist() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the email is not signed up");
        homePage.homepage();
        LoginUser.EmailDoesExist();
        test.pass("To verify the email is not signed up passed successfully.");
    }
    @Test(priority = 4,enabled = true)
    public void Verify_SignUp_Link_ontheLogin() throws InterruptedException {
        ExtentTest test = extent.createTest("To Verify the signup link on the login page");
        homePage.homepage();
        LoginUser.SignupLinkOnLogin();
        test.pass("To verify the signup link on the login page is clickable and navigate to singup page :  passed successfully.");
    }
    @Test(priority = 4,enabled = false)
    public void verifyLoginConsumer() throws InterruptedException {
        homePage.homepage();
        LoginUser.LoginConsumerSuceessful();
        //LoginUser.OTP_read();
    }


}
