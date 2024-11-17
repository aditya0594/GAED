package Pageobjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import baseClass.TestBase;
import org.openqa.selenium.By;

import baseClass.ScreenBase;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.log.Log;
import org.openqa.selenium.devtools.v119.console.Console;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import utils.CommonUtils;
import utils.utility;


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
    By Account_deleted = By.id("com.hp.impulse.sprocket:id/tv_alert");


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
    static Properties properties;

    static {
        try {
            properties = TestBase.read_properties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HomePage() throws IOException {

        URLS = utility.property("url").toString();
        FIRSTNAME = utility.property("firstname").toString(); //properties.getProperty("firstname");
        LASTNAME = utility.property("lastname").toString(); //properties.getProperty("lastname");
        EMAIL = utility.property("email").toString(); //properties.getProperty("email");
        INFIRSTNAME = utility.property("invalidfirstname").toString();//(String) properties.get("invalidfirstname");
        INLASTNAME =  utility.property("invalidlastname").toString();//properties.get("invalidlastname");
        INEMAIL = utility.property("invalidemail").toString();//(String) properties.get("invalidemail");
        // Name= (String) Properties.get("name");
    }

    public void homepage() {
        driver.get(URLS);
        String hometitle = driver.getTitle();
        Assert.assertEquals("GAED", hometitle);
    }



    public void Mailinator_tab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        // Get the dynamic part from the variable
        String dynamicPart = EMAIL;
        // Navigate to the URL with the dynamic part
        String url = "https://www.mailinator.com/v4/public/inboxes.jsp?to=" + dynamicPart;
        driver.get(url);


    }

}


