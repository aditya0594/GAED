package Testcases;

import java.baseClass.TestBase;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import java.utils.Dataprovider;

import java.io.IOException;

public class LoginUserTest extends TestBase {
    HomePage homePage = new HomePage();
    LoginConsumer login = new LoginConsumer();

    public LoginUserTest() throws IOException {
    }
  /*  @DataProvider(name = "Loginuser")
    public static Object[][] LoginData() throws IOException {

        FileInputStream fis = new FileInputStream("src/resources/ExcelFile.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet loginsheet = workbook.getSheet("Loginsheet");

        int numberOfRows = loginsheet.getPhysicalNumberOfRows();
        Object[][] testData = new Object[numberOfRows - 1][2]; // Adjust for header row if present

        for (int i = 1; i < numberOfRows; i++) { // Start from 1 to skip header row
            Row row = loginsheet.getRow(i);

            if (row != null) {
                Cell usernameEmail = row.getCell(0);
                Cell password = row.getCell(1);

                if (usernameEmail != null) {
                    testData[i - 1][0] = getCellValue(usernameEmail);
                } else {
                    testData[i - 1][0] = ""; // Handle null cell
                }

                if (password != null) {
                    testData[i - 1][1] = getCellValue(password);
                } else {
                    testData[i - 1][1] = ""; // Handle null cell
                }

                System.out.println("Username fetched value: " + usernameEmail);
                System.out.println("Password fetched value: " + password);
            }
        }

        workbook.close();
        fis.close();

        return testData;
    }
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
*/
/*  @DataProvider(name = "Loginuser")
  public static Object[][] LoginData() throws IOException {
     utility.LoginData();
  }*/

      @Test(priority = 1, enabled = true, dataProvider = "ValidLoginEmail",dataProviderClass = Dataprovider.class)
    public void Login_VerifyEmail(String usernameEmail, String password) throws IOException, InterruptedException {
        ExtentTest test = extent.createTest("To verify the valid email");
        System.out.println(usernameEmail + " " + password);
       homePage.homepage();
        login.Login_Btn_home();
        login.Verify_login_Page();
        login.Login_Email(usernameEmail);
        login.sentOTPbtn();
        login.Login_OTP_read(usernameEmail);
        login.enterOTP();
        login.verifyEmailbtn();
        login.Veriyconsumerprofile(usernameEmail);
        test.pass("To verify the valid email passed successfully.");
    }
    @Test(priority = 2, enabled = true, dataProvider = "InvalidLoginEmail",dataProviderClass = Dataprovider.class)
    public void Login_VerifyInvalidEmail(String usernameEmail, String password) throws IOException {
        ExtentTest test = extent.createTest("To verify the invalid email");
        System.out.println(usernameEmail);
        homePage.homepage();
        login.Login_Btn_home();
        login.Verify_login_Page();
        login.Login_Email(usernameEmail);
        login.sentOTPbtn();
        login.invalid_validation();
        test.pass("To verify the invalid email passed successfully.");
    }
    @Test(priority = 3, enabled = true)
    public void Login_VerifyEmptyEmail() throws IOException {
        ExtentTest test = extent.createTest("To verify the invalid email");
        homePage.homepage();
        login.Login_Btn_home();
        login.Verify_login_Page();
        login.sentOTPbtn();
        login.Empty_invalid_validation();
        test.pass("To verify the Email email passed successfully.");
    }
    @Test(priority = 4, enabled = true, dataProvider = "ValidLoginEmail",dataProviderClass = Dataprovider.class)
    public void InvalidOTP(String usernameEmail, String password) throws IOException, InterruptedException {
        ExtentTest test = extent.createTest("To verify the Invalid OTP message");
        System.out.println(usernameEmail + " " + password);
        homePage.homepage();
        login.Login_Btn_home();
        login.Verify_login_Page();
        login.Login_Email(usernameEmail);
        login.sentOTPbtn();
        login.InvalidOTP();
        login.verifyEmailbtn();
        login.invalidOTP_message();
    }


    /* @Test(priority = 2,enabled = true)
    public void VerifyEmptyEmail() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the Empty email");
        homePage.homepage();
        LoginConsumer.EmptyEmail();
        test.pass("To verify the Empty email passed successfully.");
    }
   @Test(priority = 3,enabled = true)
    public void VerifyEmptyNotExist() throws InterruptedException {
        ExtentTest test = extent.createTest("To verify the email is not signed up");
        homePage.homepage();
        LoginConsumer.EmailDoesExist();
        test.pass("To verify the email is not signed up passed successfully.");
    }
    @Test(priority = 4,enabled = true)
    public void Verify_SignUp_Link_ontheLogin() throws InterruptedException {
        ExtentTest test = extent.createTest("To Verify the signup link on the login page");
        homePage.homepage();
        LoginConsumer.SignupLinkOnLogin();
        test.pass("To verify the signup link on the login page is clickable and navigate to singup page :  passed successfully.");
    }
    @Test(priority = 5,enabled = true)
    public void verifyLoginConsumer() throws InterruptedException {
        homePage.homepage();
        LoginConsumer.LoginConsumerSuceessful();
        LoginConsumer.Veriy_consumer_profile();
        //LoginUser.OTP_read();
    }*/

}
