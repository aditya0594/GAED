package Testcase;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import Pageobjects.SignUp;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import java.awt.*;

public class SignUpTest extends TestBase {
    HomePage homePage = new HomePage();
    LoginConsumer Loginuser = new LoginConsumer();
    Api_Call api_call = new Api_Call();
    SignUp Signup = new SignUp();


    @Test(priority = 1,enabled = true)
    public void Verify_Invalid_Signup_Fields () throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the invalid Signup fields ");
        homePage.homepage();
        Signup.invalid_signup_field();
        test.pass("To verify the invalid email passed successfully.");
    }
    @Test(priority = 1,enabled = true)
    public void Verify_Empty_signup_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the empty fields");
        homePage.homepage();
        Signup.Empty_signup_field();
        test.pass("To verify the empty fields passed successfully.");
    }
    @Test(priority = 1,enabled = true)
    public void Consumer_Sign_up() throws InterruptedException, AWTException {
        ExtentTest test = extent.createTest("To verify the invalid email");
        homePage.homepage();

        Signup.consumer_Sign_up_Step_One();
        Signup.consumer_Sign_up_Step_Two();
        api_call.deleteUser();
        test.pass("To verify the invalid email passed successfully.");
    }
}
