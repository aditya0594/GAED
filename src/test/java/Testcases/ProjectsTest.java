package Testcases;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import Pageobjects.MarketPlaceAdmin;
import Pageobjects.Projects;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Optional;

public class ProjectsTest extends TestBase {

    public ProjectsTest() throws IOException {

    }
    HomePage homepage = new HomePage();
    LoginConsumer login = new LoginConsumer();
    Projects project = new Projects();
    MarketPlaceAdmin marketadmin = new MarketPlaceAdmin();

    @Test(priority = 3,enabled = true)
    public void Create_Project() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        project.projectClick();
        project.projectCreate();
        project.createdUnderAssessmentMessage();
        test.pass("Create project mandatory field validation ");
    }
    @Test(priority = 4,enabled = true)
    public void admin_VisitSchedule() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        marketadmin.marketAdminLogin();
        marketadmin.Admin_buyandSell_publish();
        test.pass("Create project mandatory field validation ");
    }
    }
