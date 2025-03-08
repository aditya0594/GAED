package Testcases;

import Pageobjects.SSO_Admin;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAdminTest extends TestBase {
    public LoginAdminTest() throws IOException {
    }
    SSO_Admin admin = new SSO_Admin();
    SSO_Admin loginadmin = new SSO_Admin();
    @Test(priority = 1,enabled = true)
    public void AdminLogin_valid() throws IOException, InterruptedException {
        ExtentTest test = extent.createTest("To verify the vaild email");
        admin.Adminhomepage();
        admin.AdminLogin_with_validData();
       test.pass("Admin login to portal is passed");
    }
}
