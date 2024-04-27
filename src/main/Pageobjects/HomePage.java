package Pageobjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import ExtentReportListener.MyITestListener;
import baseClass.TestBase;
import org.openqa.selenium.By;

import baseClass.ScreenBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.log.Log;
import org.openqa.selenium.devtools.v119.console.Console;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import utils.CommonUtils;


public class HomePage extends TestBase {

    //hp sprocket
    By Full_Name = By.id("com.hp.impulse.sprocket:id/editTextFullname");
    By Email = By.id("com.hp.impulse.sprocket:id/editTextEmail");
    By Password = By.id("com.hp.impulse.sprocket:id/editTextPassword");
    By Sign_Up_Btn = By.id("com.hp.impulse.sprocket:id/createButton");
    By Alert_OK_Btn = By.id("com.hp.impulse.sprocket:id/bt_ok");
    By Password_Eye_btn = By.id("com.hp.impulse.sprocket:id/imageViewEye");
    By Already_Acct = By.id("com.hp.impulse.sprocket:id/tv_login");
    By LoginPage_Txt = By.id("com.hp.impulse.sprocket:id/tv_login");
    By Ill_Do_It_Later = By.id("com.hp.impulse.sprocket:id/illDoItLaterButton");

    By My_sprocket = By.id("com.hp.impulse.sprocket:id/welcome_txt_setup");
    By My_Friend = By.id("com.hp.impulse.sprocket:id/welcome_txt_create");
    By Explore_Sprocket = By.id("com.hp.impulse.sprocket:id/welcome_txt_explore");
    By Login_email = By.id("com.hp.impulse.sprocket:id/editTextEmail");
    By Login_password = By.id("com.hp.impulse.sprocket:id/editTextPassword");
    By Login_btn = By.id("com.hp.impulse.sprocket:id/bt_login");
    By Forget_Password = By.id("com.hp.impulse.sprocket:id/tv_forgot_password");
    By ErrormsgEmail = By.id("com.hp.impulse.sprocket:id/tv_email_error");
    By ErrormsgPassword = By.id("com.hp.impulse.sprocket:id/tv_detail");
    By Get_started_image = By.id("com.hp.impulse.sprocket:id/privacy_image");
    By Get_Start_Btn = By.id("com.hp.impulse.sprocket:id/bt_privacy_get_started");
    By Check_Collection = By.id("com.hp.impulse.sprocket:id/cb_privacy_agree");
    By Check_Google_Analytics = By.id("com.hp.impulse.sprocket:id/cb_analytics_agree");
    By Continue_Button = By.id("com.hp.impulse.sprocket:id/bt_privacy_lets_go");

    // Hamburger menu locators
    By Hamburger_Menu = By.id("com.hp.impulse.sprocket:id/toolbar_icon");
    //com.hp.impulse.sprocket:id/menu_icon_container
    //com.hp.impulse.sprocket:id/menu_txt_item
    By ham_Account_Setting = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout[3]/android.widget.LinearLayout/android.widget.ListView[1]/android.widget.RelativeLayout[4]/android.widget.TextView");
    By ham_Manage_printer = By.id("com.hp.impulse.sprocket:id/menu_txt_item");
    By ham_App_Setting = By.id("com.hp.impulse.sprocket:id/menu_img_item");
    By ham_How_To_help = By.id("com.hp.impulse.sprocket:id/menu_txt_item");
    By ham_HP_icon = By.id("com.hp.impulse.sprocket:id/menu_icon_container");

    // Account setting locators
    By Account_username = By.id("com.hp.impulse.sprocket:id/user_name");

    //delete account locators
    By Delete_account_btn = By.id("com.hp.impulse.sprocket:id/img_delete");
    By Cancel_account_bt = By.id("com.hp.impulse.sprocket:id/bt_cancel");
    By Confirm_btn = By.id("com.hp.impulse.sprocket:id/bt_confirm_delete");
    //com.hp.impulse.sprocket:id/tv_detail
    By Content_delete_pop = By.id("com.hp.impulse.sprocket:id/tv_detail");

    By Account_deleted_ok_btn = By.id("com.hp.impulse.sprocket:id/bt_ok");
    By Account_deleted= By.id("com.hp.impulse.sprocket:id/tv_alert");


    public static String URLS = "";
    public static String FIRSTNAME = "";
    public static String LASTNAME = "";
    public static String INEMAIL = "";
    public static String INFIRSTNAME = "";
    public static String INLASTNAME = "";
    public static String EMAIL = "";


    String Month = "";
    String DAY = "";
    String YEAR = "";

    public  HomePage() {
        try {
            Properties properties = TestBase.read_properties();
            URLS = properties.getProperty("url");
            FIRSTNAME = properties.getProperty("firstname");
            LASTNAME = properties.getProperty("lastname");
            EMAIL = properties.getProperty("email");
            INFIRSTNAME = (String) properties.get("invalidfirstname");
            INLASTNAME = (String) properties.get("invalidlastname");
            INEMAIL = (String) properties.get("invalidemail");
            // Name= (String) Properties.get("name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homepage(){
        driver.get(URLS);
        String hometitle = driver.getTitle();
        Assert.assertEquals("GAED",hometitle);
    }

    By getSign_Up_Btn= By.xpath("//span[@class='cursor-pointer hidden lg:inline-block py-2 px-6 text-sm text-white border border-white font-medium rounded-full transition duration-200 group-hover:text-primary group-hover:border-primary']");
    By signup_page_title = By.xpath("//h3[@class='py-2 xl:text-3xl text-black-900 font-semibold']");
    By firstname = By.xpath("//input[@name='firstName']");
    By lastname = By.xpath("//input[@name='lastName']");
    By email = By.xpath("//input[@name='email']");
    By agreementChk = By.xpath("//input[@name='tncCheck']");
    By sentOTPbtn = By.xpath("//button[@type='submit']");
    By invalidFistnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[1]/div/span");
    By invalidLastnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[2]/div/span");
    By invalidEmailMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[2]/div/div/span");
    By EmpFistnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[1]/div/span");
    By EmpLastnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[2]/div/span");
    By EmpEmailMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[2]/div/div/span");
   // By firstname = By.xpath("//input[@name='firstName']");

    public void invalid_signup_field() {
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(firstname).sendKeys(INFIRSTNAME);
        waitForElement(invalidFistnameMesss);
        String invalidfirstnameMess = driver.findElement(invalidFistnameMesss).getText();
        Assert.assertEquals("Field cannot contain any special characters.",invalidfirstnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(lastname).sendKeys(INLASTNAME);
        waitForElement(invalidFistnameMesss);
        String invalidlastnameMess = driver.findElement(invalidLastnameMesss).getText();
        Assert.assertEquals("Field cannot contain any special characters.",invalidlastnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(email).sendKeys(INEMAIL);
        waitForElement(invalidFistnameMesss);
        String invalidemailMess = driver.findElement(invalidEmailMesss).getText();
        Assert.assertEquals("This looks like an invalid email (eg: abc@xyz.com)",invalidemailMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
    }
    public void Empty_signup_field() {
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(agreementChk).click();
        driver.findElement(sentOTPbtn).click();
        driver.findElement(firstname).sendKeys("");
        waitForElement(invalidFistnameMesss);
        String invalidfirstnameMess = driver.findElement(EmpFistnameMesss).getText();
        Assert.assertEquals("Field is Required.",invalidfirstnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(lastname).sendKeys("");
        waitForElement(invalidFistnameMesss);
        String invalidlastnameMess = driver.findElement(EmpLastnameMesss).getText();
        Assert.assertEquals("Field is Required.",invalidlastnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(email).sendKeys("");
        waitForElement(invalidFistnameMesss);
        String invalidemailMess = driver.findElement(EmpEmailMesss).getText();
        Assert.assertEquals("Field is Required.",invalidemailMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
    }
    public void Mailinator_tab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        // Get the dynamic part from the variable
        String dynamicPart = EMAIL;
        // Navigate to the URL with the dynamic part
        String url = "https://www.mailinator.com/v4/public/inboxes.jsp?to=" + dynamicPart;
        driver.get(url);


    }
    public void console_OTp() {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(), entry -> {
            String text = (String) entry.getText();
            if (text.contains("OTP:")) {
                String otp = text.split("OTP:")[1].trim();
                System.out.println("OTP: " + otp);
            }
        });

    }


    public void Sign_up() {
        boolean signbtn = driver.findElement(getSign_Up_Btn).isDisplayed();
        Assert.assertTrue(signbtn);
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(firstname).sendKeys(FIRSTNAME);
        driver.findElement(lastname).sendKeys(LASTNAME);
        driver.findElement(email).sendKeys(EMAIL);
        driver.findElement(agreementChk).click();
        driver.findElement(sentOTPbtn).click();

    }

    public void verify_signup_disble_firstname() {
        boolean signbtn = driver.findElement(By.xpath("getSign_Up_Btn")).isDisplayed();
        Assert.assertTrue(signbtn);
        driver.findElement(By.xpath("getSign_Up_Btn")).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(firstname).sendKeys(FIRSTNAME);
        driver.findElement(lastname).sendKeys(LASTNAME);
        driver.findElement(email).sendKeys(EMAIL);
        driver.findElement(Sign_Up_Btn).click();

    }

    public void verify_signup_disble_email() {
        driver.findElement(Full_Name).sendKeys("aditya");
        driver.findElement(Email).sendKeys(" ");
       // driver.findElement(Password).sendKeys(PASSWORD);
        Assert.assertFalse((driver.findElement(Sign_Up_Btn)).isEnabled(), "OK button is disabled.");
        System.out.println("OK button is disabled.");

    }

    public void verify_signup_disble_password() {
        driver.findElement(Full_Name).sendKeys("aditya");
        driver.findElement(Email).sendKeys(EMAIL);
        driver.findElement(Password).sendKeys(" ");
        Assert.assertFalse((driver.findElement(Sign_Up_Btn)).isEnabled(), "OK button is disabled.");
        System.out.println("OK button is disabled.");

    }

    public void password_validation() {
        driver.findElement(Full_Name).sendKeys("aditya");
        driver.findElement(Email).sendKeys(EMAIL);
       // driver.findElement(Password).sendKeys(INUPASSWORD);
        driver.findElement(Sign_Up_Btn).click();
        Assert.assertTrue(driver.findElement(Alert_OK_Btn).isDisplayed());
        driver.findElement(Alert_OK_Btn).click();
        Assert.assertEquals("Password must include: At least 1 uppercase letter", "Password must include: At least 1 uppercase letter");
    }

    public void password_eye_btn() throws InterruptedException {
       // driver.findElement(Password).sendKeys(PASSWORD);
        String pas = driver.findElement(Password).getText();
        System.out.println("Text of password" + pas);
        Thread.sleep(2000);
        driver.findElement(Password_Eye_btn).click();
        Thread.sleep(6000);
        String eyepas = driver.findElement(Password).getAttribute("text");
        System.out.println("Seen Password" + eyepas);
    }

    public void sign_up() throws InterruptedException {

        driver.findElement(Sign_Up_Btn).click();
       // driver.findElement(Already_Acct).click();
      //  String Login_page = driver.findElement(LoginPage_Txt).getText();
      //  System.out.println("Login page title : " + Login_page);
      //  Assert.assertEquals(Login_page, "Login");
        //driver.wait(2000);
        //driver.closeApp();

    }

    public void ill_do_later() throws InterruptedException {
        waitForElement(Ill_Do_It_Later);
        driver.findElement(Ill_Do_It_Later).click();
        String my_sprocket = driver.findElement(My_sprocket).getText();
        Assert.assertEquals(my_sprocket, "my sprocket");
        String my_friend = driver.findElement(My_Friend).getText();
        Assert.assertEquals(my_friend, "my friend's sprocket");
        String explore_sprocket = driver.findElement(Explore_Sprocket).getText();
        Assert.assertEquals(explore_sprocket, "explore sprocket");

    }

    public void login_invalid_email() {

        driver.findElement(Already_Acct).click();
        driver.findElement(Login_email).sendKeys(INEMAIL);
      //  driver.findElement(Login_password).sendKeys(PASSWORD);
        driver.findElement(Login_btn).click();
        driver.findElement(Alert_OK_Btn).click();
        String LErrormsg_Email =  driver.findElement(ErrormsgEmail).getText();
        Assert.assertEquals(LErrormsg_Email, "Please enter your email address");
    }
    public void login_invalid_password() {

        driver.findElement(Already_Acct).click();
        driver.findElement(Login_email).sendKeys(EMAIL);
       // driver.findElement(Login_password).sendKeys(INUPASSWORD);
        driver.findElement(Login_btn).click();
        String LErrormsg_Password =  driver.findElement(ErrormsgPassword).getText();
        Assert.assertEquals(LErrormsg_Password, "You have entered an invalid email address or password");
        driver.findElement(Alert_OK_Btn).click();

    }
    public void login() {

        driver.findElement(Already_Acct).click();
        driver.findElement(Login_email).sendKeys(EMAIL);
      //  driver.findElement(Login_password).sendKeys(PASSWORD);
        driver.findElement(Login_btn).click();
        String my_sprocket = driver.findElement(My_sprocket).getText();
        Assert.assertEquals(my_sprocket, "my sprocket");
        String my_friend = driver.findElement(My_Friend).getText();
        Assert.assertEquals(my_friend, "my friend's sprocket");
        String explore_sprocket = driver.findElement(Explore_Sprocket).getText();
        Assert.assertEquals(explore_sprocket, "explore sprocket");
        driver.findElement(Explore_Sprocket).click();


    }

    public void Normal_login() {
        //waitForElement(Already_Acct);
        driver.findElement(Already_Acct).click();
        driver.findElement(Login_email).sendKeys(EMAIL);
       // driver.findElement(Login_password).sendKeys(PASSWORD);
        driver.findElement(Login_btn).click();
        driver.findElement(Explore_Sprocket).click();
    }
    public void hamburger_Account_Setting() {
        waitForElement(Hamburger_Menu);
        driver.findElement(Hamburger_Menu).click();
        waitForElement(ham_Account_Setting);
        driver.findElement(ham_Account_Setting).click();
    }
    public void verify_account_name() throws InterruptedException {
        String account_setting_username = driver.findElement(Account_username).getText();
        Assert.assertEquals(FIRSTNAME, account_setting_username);
        Thread.sleep(3000);
        //driver.closeApp();

    }
    public void delete_account() throws InterruptedException {
        waitForElement(Delete_account_btn);
        driver.findElement(Delete_account_btn).click();
        driver.findElement(Cancel_account_bt).click();
        driver.findElement(Delete_account_btn).click();
        driver.findElement(Confirm_btn).click();
        Thread.sleep(5000);
       // waitForElement(Account_deleted_ok_btn);
       // Thread.sleep(5000);
       // Assert.assertTrue((driver.findElement(Account_deleted_ok_btn)).isEnabled(), "Popup is visible.");
        //driver.findElement(Account_deleted_ok_btn).click();
        //WebDriverWait w = new WebDriverWait(driver,3);
        // presenceOfElementLocated condition
      //  w.until(ExpectedConditions.presenceOfElementLocated (Account_deleted_ok_btn));
       // driver.findElement(Account_deleted_ok_btn).click();


    }
    public void delete_account_ok_popup() throws InterruptedException {
        Thread.sleep(5000);
        waitForElement(Account_deleted_ok_btn);
        Thread.sleep(5000);
        Assert.assertTrue((driver.findElement(Account_deleted_ok_btn)).isEnabled(), "Popup is visible.");
        driver.findElement(Account_deleted_ok_btn).click();
    }
    public void get_started_screen() throws InterruptedException {

        // driver.findElement(Already_Acct).click();
        // driver.findElement(Login_email).sendKeys(EMAIL);
        // driver.findElement(Login_password).sendKeys(PASSWORD);
        // driver.findElement(Login_btn).click();
        //String my_sprocket = driver.findElement(My_sprocket).getText();
        // Assert.assertEquals(my_sprocket, "my sprocket");
        // String my_friend = driver.findElement(My_Friend).getText();
        // Assert.assertEquals(my_friend, "my friend's sprocket");
        // String explore_sprocket = driver.findElement(Explore_Sprocket).getText();
        //  Assert.assertEquals(explore_sprocket, "explore sprocket");
        // driver.findElement(Explore_Sprocket).click();
        //  Assert.assertTrue(driver.findElement(Get_started_image).isDisplayed());
        //  String Get_Started_text = driver.findElement(Get_Start_Btn).getText();
        // Assert.assertEquals(Get_Started_text, "Get Started");
        driver.findElement(Get_Start_Btn).click();
        driver.findElement(Check_Collection).click();
        driver.findElement(Check_Google_Analytics).click();
        String Continue_text = driver.findElement(Continue_Button).getText();
        Assert.assertEquals(Continue_text, "Continue");
        driver.findElement(Continue_Button).click();

    }
    public void get_started_screen_signup() throws InterruptedException {

       // driver.findElement(Already_Acct).click();
       // driver.findElement(Login_email).sendKeys(EMAIL);
       // driver.findElement(Login_password).sendKeys(PASSWORD);
       // driver.findElement(Login_btn).click();
        waitForElement(Ill_Do_It_Later);
        driver.findElement(Ill_Do_It_Later).click();
        String my_sprocket = driver.findElement(My_sprocket).getText();
       Assert.assertEquals(my_sprocket, "my sprocket");
       String my_friend = driver.findElement(My_Friend).getText();Assert.assertEquals(my_friend, "my friend's sprocket");String explore_sprocket = driver.findElement(Explore_Sprocket).getText();
       Assert.assertEquals(explore_sprocket, "explore sprocket");
       driver.findElement(Explore_Sprocket).click();
       Assert.assertTrue(driver.findElement(Get_started_image).isDisplayed());
       String Get_Started_text = driver.findElement(Get_Start_Btn).getText();
       Assert.assertEquals(Get_Started_text, "Get Started");
        driver.findElement(Get_Start_Btn).click();
        driver.findElement(Check_Collection).click();
        driver.findElement(Check_Google_Analytics).click();
        String Continue_text = driver.findElement(Continue_Button).getText();
        Assert.assertEquals(Continue_text, "Continue");
        driver.findElement(Continue_Button).click();
        Thread.sleep(5000);

    }
    public void get_started_screen_After_signup() throws InterruptedException {

        // driver.findElement(Already_Acct).click();
        // driver.findElement(Login_email).sendKeys(EMAIL);
        // driver.findElement(Login_password).sendKeys(PASSWORD);
        // driver.findElement(Login_btn).click();
        //waitForElement(Ill_Do_It_Later);
        // driver.findElement(Ill_Do_It_Later).click();
        String my_sprocket = driver.findElement(My_sprocket).getText();
        Assert.assertEquals(my_sprocket, "my sprocket");
        String my_friend = driver.findElement(My_Friend).getText();Assert.assertEquals(my_friend, "my friend's sprocket");String explore_sprocket = driver.findElement(Explore_Sprocket).getText();
        Assert.assertEquals(explore_sprocket, "explore sprocket");
        driver.findElement(Explore_Sprocket).click();
        Assert.assertTrue(driver.findElement(Get_started_image).isDisplayed());
        String Get_Started_text = driver.findElement(Get_Start_Btn).getText();
        Assert.assertEquals(Get_Started_text, "Get Started");
        driver.findElement(Get_Start_Btn).click();
        driver.findElement(Check_Collection).click();
        driver.findElement(Check_Google_Analytics).click();
        String Continue_text = driver.findElement(Continue_Button).getText();
        Assert.assertEquals(Continue_text, "Continue");
        driver.findElement(Continue_Button).click();
        Thread.sleep(5000);

    }

    public void get_started_screen_AfterIlldoit_signup() throws InterruptedException {

        // driver.findElement(Already_Acct).click();
        // driver.findElement(Login_email).sendKeys(EMAIL);
        // driver.findElement(Login_password).sendKeys(PASSWORD);
        // driver.findElement(Login_btn).click();
        waitForElement(Ill_Do_It_Later);
         driver.findElement(Ill_Do_It_Later).click();
        String my_sprocket = driver.findElement(My_sprocket).getText();
        Assert.assertEquals(my_sprocket, "my sprocket");
        String my_friend = driver.findElement(My_Friend).getText();Assert.assertEquals(my_friend, "my friend's sprocket");String explore_sprocket = driver.findElement(Explore_Sprocket).getText();
        Assert.assertEquals(explore_sprocket, "explore sprocket");
        driver.findElement(Explore_Sprocket).click();
        Assert.assertTrue(driver.findElement(Get_started_image).isDisplayed());
        String Get_Started_text = driver.findElement(Get_Start_Btn).getText();
        Assert.assertEquals(Get_Started_text, "Get Started");
        driver.findElement(Get_Start_Btn).click();
        driver.findElement(Check_Collection).click();
        driver.findElement(Check_Google_Analytics).click();
        String Continue_text = driver.findElement(Continue_Button).getText();
        Assert.assertEquals(Continue_text, "Continue");
        driver.findElement(Continue_Button).click();
        Thread.sleep(5000);

    }

    public void recent_app() {

    }
    
    public void exit_app() {

    }
}


