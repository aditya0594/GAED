package Testcases;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
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

    @Test(priority = 3,enabled = true)
    public void Create_Project() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        project.projectClick();
        project.projectCreate();
        test.pass("Create project mandatory field validation ");
    }
//    @Test(priority = 3,enabled = true)
//    public void otp() throws InterruptedException {
//        ExtentTest test = extent.createTest("Create project mandatory field validation ");
//        homepage.homepage();
//        login.LoginConsumerSuceessful();
//        test.pass("Create project mandatory field validation ");
//

    }
