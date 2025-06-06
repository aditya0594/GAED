package Testcases;

import Pageobjects.*;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import utils.Dataprovider;

import java.io.IOException;

public class BuySell_BidTest extends TestBase {

    HomePage homepage = new HomePage();
    LoginConsumer login = new LoginConsumer();
    BuyAndSell Buyandsell = new BuyAndSell();
    BuyandSellBid Buysellbid = new BuyandSellBid();
    MarketPlaceAdmin marketadmin = new MarketPlaceAdmin();

    public BuySell_BidTest() throws IOException {
    }
    @Test(priority =1,enabled = true)
    public void createProject() throws InterruptedException {
        ExtentTest test = extent.createTest("Create the project for the bid ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        Buyandsell.vaild_Stepper1_ProjectFields_button();
        Buyandsell.vaild_Stepper2_ProjectFields_button();
        Buyandsell.vaild_Stepper3_ProjectFields_button();
        Thread.sleep(3000);
        login.consumerLogout("Logout");
        marketadmin.marketAdminLogin();
        marketadmin.Admin_buyandSell_publish();
        test.pass("Project created on the for the bid");
    }

    @Test(priority =2,enabled = true,dataProvider = "vendorEmail",dataProviderClass = Dataprovider.class)
    public void bidAccept(String vendorEmail,String pass) throws InterruptedException {
        ExtentTest test = extent.createTest("Bid on the project");
        homepage.homepage();
        login.LoginVendor(vendorEmail);
        String project_name= readLastValue(0,"Project details");
        Buysellbid.ProjectToBid();
        //System.out.println("This is the project name : " +  project_name );
        test.pass("bid on the project ");
    }
    @Test(priority =3,enabled = true)
    public void AcceptBidConsumer() throws InterruptedException {
        homepage.homepage();
        login.LoginConsumerSuceessful();
        Buysellbid.consumerMyProjectBuyAndSell();
        Buysellbid.viewProposal();
    }

}
