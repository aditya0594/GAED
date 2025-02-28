package Testcases;




import Pageobjects.SSO_Admin;
import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import Pageobjects.SignUp;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import utils.Dataprovider;


import java.awt.*;
import java.io.IOException;

public class SignUpTest extends TestBase {
    HomePage homePage = new HomePage();
    LoginConsumer Loginuser = new LoginConsumer();
    Api_Call api_call = new Api_Call();
    SignUp signup = new SignUp();
    SSO_Admin adminlogin = new SSO_Admin();

    public SignUpTest() throws IOException {
    }

    @Test(priority = 1,enabled = true)
    public void Signup_page_verify() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the Signup Page");
        homePage.homepage();
        signup.signup_button();
        test.pass("To verify the Signup Page opened successfully.");
    }
    @Test(priority = 2,enabled = true)
    public void Verify_Empty_signup_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the empty fields");
        homePage.homepage();
        signup.Empty_signup_field1();
        test.pass("To verify the empty fields passed successfully.");
    }

    @Test(priority = 3,enabled = true, dataProvider = "Signupstep", dataProviderClass = Dataprovider.class)
    public void Consumer_Sign_up_and_Verify_by_admin(String fname, String lname,String ConsumerSignUpEmail ) throws InterruptedException, AWTException, IOException {
        ExtentTest test = extent.createTest("To verify the invalid email");
        //api_call.deleteUser();
        homePage.homepage();
        signup.consumer_Sign_up_Step_One(fname,lname);
        Thread.sleep(2000);
        adminlogin.AdminLogin_VerifyConsumer();
        test.pass("To verify the invalid email passed successfully.");
    }
    @Test(priority = 4,enabled = true, dataProvider = "Signupstep", dataProviderClass = Dataprovider.class)
    public void Consumer_Sign_up_and_OnHold_by_admin(String fname, String lname,String ConsumerSignUpEmail ) throws InterruptedException, AWTException, IOException {
        ExtentTest test = extent.createTest("To verify the invalid email");
        //api_call.deleteUser();
        homePage.homepage();
        signup.consumer_Sign_up_Step_One(fname,lname);
        Thread.sleep(2000);
        adminlogin.AdminLogin_OnHoldConsumer();
        test.pass("To verify the invalid email passed successfully.");
    }
}
////body/div[@id='root']/div[@class='App']/div/div[@class='h-full']/div[@class='mx-auto']/div[@class='flex justify-center']/div[@class='w-full flex']/div[2]
