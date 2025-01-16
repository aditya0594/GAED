package Testcases;

import Pageobjects.BuyAndSell;
import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import utils.Dataprovider;


import java.io.IOException;

public class BuyAndSellTest extends TestBase {
    HomePage homepage = new HomePage();
    LoginConsumer login = new LoginConsumer();
    BuyAndSell Buyandsell = new BuyAndSell();


    public BuyAndSellTest() throws IOException {
    }

    @Test(priority = 1, enabled = true, dataProvider = "ValidLoginEmail",dataProviderClass = Dataprovider.class)
    public void validate_buyandsell_btn(String usernameEmail) throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Click on the Buy and sell in the consumer and");
        homepage.homepage();
        login.Login_Btn_home();
        login.Verify_login_Page();
        login.Login_Email(usernameEmail);
        login.sentOTPbtn();
        login.Login_OTP_read(usernameEmail);
        login.enterOTP();
        login.verifyEmailbtn();
        login.Veriyconsumerprofile(usernameEmail);
        Buyandsell.validate_BuySell_buttons();
        test.pass("Consumer user able to click on the Buy and sell");
        //LoginUser.OTP_read();
    }
    @Test(priority = 3,enabled = true)
    public void invalidate_SellProject_buttons() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        Buyandsell.validate_next_saveAsDraft_back();
        test.pass("Create project mandatory field validated");

    }
    @Test(priority = 4,enabled = true)
    public void invalidat_sellProject_stepper_1_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        Buyandsell.invalidate_Stepper1_ProjectFields_button();
        test.pass("Project field is validated ");
    }
    @Test(priority = 4,enabled = true)
    public void Create_Project() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        Buyandsell.vaild_Stepper1_ProjectFields_button();
        Buyandsell.vaild_Stepper2_ProjectFields_button();
        test.pass("Create project mandatory field validation ");
    }


   /* @Test(priority = 5,enabled = true)
    public void validate_sellProject_btn() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project from the consumer login ");
        Buyandsell.validate_SellProject_button();
        test.pass("Project is created");

    }*/
}
