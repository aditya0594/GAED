package Testcases;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static baseClass.TestBase.extent;
import static utils.utility.AssertTextBtn;

public class LoginUserTest extends TestBase {
    HomePage homePage = new HomePage();
    LoginConsumer login = new LoginConsumer();

    public LoginUserTest() throws IOException {
    }

    @Test(priority = 1,enabled = true)
    public void VerifyInvalidEmail() throws Exception {
        ExtentTest test = extent.createTest("To verify the invalid email");
        homePage.homepage();
        login.Login_Btn_home();
        login.Verify_login_Page();
        login.Login_Email();



        test.pass("To verify the invalid email passed successfully.");
    }
}
