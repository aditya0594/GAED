package Testcase;

import Pageobjects.BuyAndSell;
import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

public class BuyAndSellTest extends TestBase {
    HomePage homepage = new HomePage();
    LoginConsumer LoginUser = new LoginConsumer();
    BuyAndSell Buyandsell = new BuyAndSell();

    @Test(priority = 1,enabled = true)
    public void validate_buyandsell_btn() throws InterruptedException {
        ExtentTest test = extent.createTest("Click on the Buy and sell in the consumer and");
        homepage.homepage();
        LoginUser.LoginConsumerSuceessful();
        Buyandsell.validate_BuySell_buttons();
        test.pass("Consumer user able to click on the Buy and sell");
        //LoginUser.OTP_read();
    }
    @Test(priority = 3,enabled = true)
    public void invalidate_SellProject_buttons() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        LoginUser.LoginConsumerSuceessful();
        Buyandsell.validate_next_saveAsDraft_back();
        test.pass("Project field is validated ");

    }
    @Test(priority = 4,enabled = true)
    public void invalidat_sellProject_stepper_1_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        LoginUser.LoginConsumerSuceessful();
        Buyandsell.invalidate_Stepper1_ProjectFields_button();
        test.pass("Project field is validated ");
    }
    @Test(priority = 4,enabled = true)
    public void valid_sellProject_stepper_1_fields() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        LoginUser.LoginConsumerSuceessful();
        Buyandsell.vaild_Stepper1_ProjectFields_button();
        Buyandsell.vaild_Stepper2_ProjectFields_button();
        test.pass("Project field is validated ");
    }


   /* @Test(priority = 5,enabled = true)
    public void validate_sellProject_btn() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project from the consumer login ");
        Buyandsell.validate_SellProject_button();
        test.pass("Project is created");

    }*/
}
