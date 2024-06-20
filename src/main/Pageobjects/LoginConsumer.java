package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.reporters.EmailableReporter;
import utils.utility;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.utility.AssertTextBtn;


public class LoginConsumer extends TestBase{

    static Set<Cookie> cookies;
    static By loginBtn = By.xpath("//*[@class='cursor-pointer hidden lg:inline-block lg:ml-auto lg:mr-3 py-2 px-6 text-sm 3xl:text-lg text-white border border-transparent hover:border hover:border-white font-medium  rounded-full transition duration-200 group-hover:text-primary']");
    static By loginPageVerify = By.xpath("//h3[contains(@class,'py-2 xs:text-2xl xl:text-3xl 3xl:text-4xl text-gray-800 font-semibold xs:text-center xsm:text-left')]");
    static By Loginemailtitle = By.xpath("//label[@class='block tracking-wide text-black-100 text-xs font-normal mb-2']");
    static By LoginEmail = By.xpath("//input[contains(@name,'email')]");
    static By SentOtpBtn = By.xpath("//button[@type='submit']");

    // YOPmail
    static By yopmailemail = By.xpath("//input[@id='login']");
    static By yopmailarrow = By.xpath("//i[@class='material-icons-outlined f36']");
    static By threeDot = By.xpath("//i[contains(text(),'')]");
   static By Deleteinbox = By.xpath("//i[contains(text(),'')]");
   static By Message = By.xpath("//*[@id=\"mail\"]/div/div/table/tbody/tr[2]/td/p[1]");
    static By OTPfirstbox = By.xpath("//input[@aria-label='Please enter OTP character 1']");
    static By VerifyEmailbtn = By.xpath("//span[@class='ml-3']");
   static By ConsumerpProfileBtn = By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circular MuiAvatar-colorDefault css-1llrwy8']//*[name()='svg']");
   static By ViewConsumerProfile = By.xpath("//*[@id=\"basic-menu\"]/div[3]/ul/li[1]");
   static By VerifyConsumerEmail = By.xpath("//div[contains(text(),'"+ HomePage.EMAIL +"')]");
   static By InvalidemailLoginMes   = By.xpath("//span[@class='block my-2 text-xs font-normal text-red-500 ']");
   static By EmptyEmailMess = By.xpath("//span[contains(@class,'block my-2 text-xs font-normal text-red-500')]");
   static By EmaiDoesNotexistlMess = By.xpath("//span[@class='block my-2 text-xs font-normal text-red-500 ']");


  static By SignupLinkLogin = By.xpath("//span[@class='font-medium text-primary hover:underline cursor-pointer']");
  static By SignupTitleOfLink = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div/div/form/div[2]/div/p");
  //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
  //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    String Email;

    public void Login_Btn_home(){
        driver.findElement(loginBtn).click();
    }
    public void Verify_login_Page(){
        AssertTextBtn(loginPageVerify,"Welcome Back!");
    }
    public void Login_Email() throws IOException {
        Email = utility.property("email").toString();
        driver.findElement(LoginEmail).sendKeys(Email);
    }


  public static void InvalidLoginInputs() throws InterruptedException {

      String Loginbtntext =  driver.findElement(loginBtn).getText();
      Assert.assertEquals("Login",Loginbtntext);
      driver.findElement(loginBtn).click();
      String verifyLoginpage  = driver.findElement(loginPageVerify).getText();
      Assert.assertEquals("Welcome Back!",verifyLoginpage);
      String LoginemailTitle  = driver.findElement(Loginemailtitle).getText();
      Assert.assertEquals("Email Address*",LoginemailTitle);
      driver.findElement(LoginEmail).sendKeys(HomePage.INEMAIL);
      String InvalidEmail  = driver.findElement(InvalidemailLoginMes).getText();
      Assert.assertEquals("This looks like an invalid email (eg: abc@xyz.com)",InvalidEmail);

  }
    public static void EmptyEmail() throws InterruptedException {

        String Loginbtntext =  driver.findElement(loginBtn).getText();
        Assert.assertEquals("Login",Loginbtntext);
        driver.findElement(loginBtn).click();
        String verifyLoginpage  = driver.findElement(loginPageVerify).getText();
        Assert.assertEquals("Welcome Back!",verifyLoginpage);
        String LoginemailTitle  = driver.findElement(Loginemailtitle).getText();
        Assert.assertEquals("Email Address*",LoginemailTitle);
        driver.findElement(LoginEmail).sendKeys("");
        driver.findElement(SentOtpBtn).click();
        String EmptyEmail  = driver.findElement(EmptyEmailMess).getText();
        Assert.assertEquals("Field is Required.",EmptyEmail);


    }
    public static void EmailDoesExist() throws InterruptedException {

        String Loginbtntext =  driver.findElement(loginBtn).getText();
        Assert.assertEquals("Login",Loginbtntext);
        driver.findElement(loginBtn).click();
        String verifyLoginpage  = driver.findElement(loginPageVerify).getText();
        Assert.assertEquals("Welcome Back!",verifyLoginpage);
        String LoginemailTitle  = driver.findElement(Loginemailtitle).getText();
        Assert.assertEquals("Email Address*",LoginemailTitle);
        driver.findElement(LoginEmail).sendKeys("xyz@gmail.com");
        driver.findElement(SentOtpBtn).click();
        String EmailDoesNotExist = driver.findElement(EmaiDoesNotexistlMess).getText();
        Assert.assertEquals("This email is not signed up. Double-check or sign up for a new account.",EmailDoesNotExist);
    }
    public static void SignupLinkOnLogin() throws InterruptedException {

        driver.findElement(loginBtn).click();
        String signupTitle_on_login = driver.findElement(SignupTitleOfLink).getText();
        Assert.assertEquals("Here for the first time? Sign Up",signupTitle_on_login);
        driver.findElement(SignupLinkLogin).click();

    }


    public static void LoginConsumerSuceessful() throws InterruptedException {

        String Loginbtntext =  driver.findElement(loginBtn).getText();
        Assert.assertEquals("Login",Loginbtntext);
        driver.findElement(loginBtn).click();
        String verifyLoginpage  = driver.findElement(loginPageVerify).getText();
        Assert.assertEquals("Welcome Back!",verifyLoginpage);
        String LoginemailTitle  = driver.findElement(Loginemailtitle).getText();
        Assert.assertEquals("Email Address*",LoginemailTitle);
        driver.findElement(LoginEmail).sendKeys(HomePage.EMAIL);
        String SentOTPtitle  = driver.findElement(SentOtpBtn).getText();
        Assert.assertEquals("Send OTP",SentOTPtitle);
        driver.findElement(SentOtpBtn).click();
        Thread.sleep(5000);
        Logjn_OTP_read(HomePage.EMAIL);
        driver.findElement(OTPfirstbox).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        driver.findElement(VerifyEmailbtn).click();
        Thread.sleep(6000);

    }

    public static void Veriy_consumer_profile(){
        waitForElement(ConsumerpProfileBtn);
        driver.findElement(ConsumerpProfileBtn).click();
        driver.findElement(ViewConsumerProfile).click();
        String GetConsumeremailFromProfile  = driver.findElement(VerifyConsumerEmail).getText();
        Assert.assertEquals(HomePage.EMAIL,GetConsumeremailFromProfile);

    }
    public static void verify_LoginConsumerSuceessful() throws InterruptedException {


    }



        public static void Logjn_OTP_read(String emailforInbox) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        String defaultTab = driver.getWindowHandle();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());


        // Simulate human-like interaction: Wait for a short duration
        Thread.sleep(2000);

        driver.get("https://yopmail.com/en/wm");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div")).click();
        waitForElement(yopmailemail);
        WebElement emailInput = driver.findElement(yopmailemail);
        typeLikeAHuman(emailInput, emailforInbox);
        //driver.findElement(yopmailemail).sendKeys(emailforInbox);
        driver.findElement(yopmailarrow).click();

        // Simulate human-like interaction: Mouse movement after clicking
        moveMouseAround(driver, driver.findElement(By.xpath("//i[contains(text(),'\uE16C')]")));

        Thread.sleep(3000);
        driver.switchTo().frame("ifmail");
        String messageBody = driver.findElement(By.xpath("//*[@id=\"mail\"]")).getText();
        System.out.println(messageBody);

        String otpPattern = "\\b\\d{6}\\b"; // Regex pattern to match 6-digit numbers
        Pattern pattern = Pattern.compile(otpPattern);
        Matcher matcher = pattern.matcher(messageBody);

        if (matcher.find()) {
            String otp = matcher.group();
            // Get the system clipboard
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            // Create a StringSelection object containing the OTP
            StringSelection stringSelection = new StringSelection(otp);
            // Set the StringSelection as the current contents of the clipboard
            clipboard.setContents(stringSelection,null);
            System.out.println("Extracted OTP: " + otp);
        } else {
            System.out.println("No OTP found in the message.");
        }
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
    }
}
