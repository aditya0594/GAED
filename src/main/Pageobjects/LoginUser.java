package Pageobjects;

import baseClass.TestBase;
import org.bouncycastle.jcajce.provider.symmetric.Threefish;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginUser extends TestBase {

    static By loginBtn = By.xpath("//span[normalize-space()='Login']");
    static By loginPageVerify = By.xpath("//h3[contains(@class,'py-2 text-3xl text-gray-800 font-semibold')]");
    static By Loginemailtitle = By.xpath("//label[@class='block tracking-wide text-black-100 text-xs font-normal mb-2']");
    static By LoginEmail = By.xpath("//input[@name='email']");
    static By SentOtpBtn = By.xpath("//span[@class='ml-3']");

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
        OTP_read(HomePage.EMAIL);
        driver.findElement(OTPfirstbox).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        driver.findElement(VerifyEmailbtn).click();
        Thread.sleep(6000);
        consumer_profile();
    }
    public static void consumer_profile(){
        waitForElement(ConsumerpProfileBtn);
        driver.findElement(ConsumerpProfileBtn).click();
        driver.findElement(ViewConsumerProfile).click();
        String GetConsumeremailFromProfile  = driver.findElement(VerifyConsumerEmail).getText();
        Assert.assertEquals(HomePage.EMAIL,GetConsumeremailFromProfile);

    }
    public static void OTP_read(String emailforInbox) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        String defaultTab = driver.getWindowHandle();
        // Switch to the new tab
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        // Navigate to the specified URL
        driver.get("https://yopmail.com/en/wm");
        Thread.sleep(3000);
        waitForElement(yopmailemail);
        driver.findElement(yopmailemail).sendKeys(emailforInbox);
        driver.findElement(yopmailarrow).click();
        driver.findElement(By.xpath("//i[contains(text(),'\uE16C')]"));
       // driver.findElement(threeDot).click();// driver.findElement(Deleteinbox).click();// Alert alert = driver.switchTo().alert();// alert.accept();
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
        //driver.switchTo().window(defaultTab);
    }
}
