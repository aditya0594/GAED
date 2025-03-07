package Testcases;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import Pageobjects.Projects;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import java.io.IOException;

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
        Buyandsell.vaild_Stepper1_ProjectFields_button();
        Buyandsell.vaild_Stepper2_ProjectFields_button();
        Buyandsell.vaild_Stepper3_ProjectFields_button();
        test.pass("Create project mandatory field validation ");
    }
}
