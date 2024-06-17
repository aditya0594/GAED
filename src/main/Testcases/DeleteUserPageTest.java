package Testcases;

import Pageobjects.HomePage;
import androidpageobjectss.DeletePageAndroid;
import baseClass.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteUserPageTest extends TestBase {

    HomePage LandingPage= new HomePage();
    DeletePageAndroid DeletePage = new DeletePageAndroid();

    public DeleteUserPageTest() throws IOException {
    }
}
