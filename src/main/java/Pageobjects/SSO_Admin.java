package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

import static Pageobjects.SignUp.signUp_OTP_read;

public class SSO_Admin extends TestBase {


    static String AdminUrl = "https://qa.gaedkeeper.com/admin/login";


    static By Email = By.xpath("//input[@name='email']");
    static By Submitbtn = By.xpath("//span[@class='text-base font-medium text-white']");
    static By firstOtpBlock = By.xpath("//input[@aria-label='Please enter OTP character 1']");

    static By LoginBtn = By.xpath("//button[@type='button']");

    By Email23 = By.xpath("//input[@name='email']");
    By ViewBtn = By.xpath("//*[@class='cursor-pointer hover:underline']");
    //By ViewBtn = By.xpath("//*[@d='M0 0h24v24H0V0z']");
    By VerifyUserBtn = By.xpath("//span[normalize-space()='Verify User']");
    By checkboxMarkertplace = By.xpath("(//input[@class='PrivateSwitchBase-input css-j8yymo'])[1]");
    By checkboxAssessment = By.xpath("(//input[@class='PrivateSwitchBase-input css-j8yymo'])[2]");
    By continueBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By confirmBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By OnHoldBtn = By.xpath("//span[normalize-space()='Put On Hold']");
    By OnHoldReasons = By.xpath("//textarea[@placeholder='Kindly specify the reason here']");
    By OnHoldReasonBtn = By.xpath("//button[normalize-space()='Submit']");

    //textarea[@placeholder='Kindly specify the reason here']
    public void AdminLogin() throws InterruptedException {
        driver.navigate().to(AdminUrl);
        driver.findElement(Email).sendKeys("gabriel.sze@yopmail.com");
        click(Submitbtn);
        signUp_OTP_read("gabriel.sze@yopmail.com");
        //  driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(5000);
        click(LoginBtn);
        Thread.sleep(1000);

    }
    public void Adminhomepage() throws IOException {

        driver.get("https://qa.gaedkeeper.com/admin/login");
    }
    public void AdminLogin_with_validData() throws InterruptedException, IOException {

        driver.findElement(Email).sendKeys("gabriel.sze@yopmail.com");
        click(Submitbtn);
        signUp_OTP_read("gabriel.sze@yopmail.com");
        //  driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(5000);
        click(LoginBtn);
    }
    public void AdminLogin_VerifyConsumer() throws InterruptedException {
        driver.navigate().to(AdminUrl);
        driver.findElement(Email).sendKeys("gabriel.sze@yopmail.com");
        click(Submitbtn);
        signUp_OTP_read("gabriel.sze@yopmail.com");
      //  driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(5000);
        click(LoginBtn);
        Thread.sleep(5000);
        click(ViewBtn);
        //verify the user
        click(VerifyUserBtn);
        click(checkboxMarkertplace);
        click(checkboxAssessment);
        click(continueBtn);
        Thread.sleep(2000);
        click(confirmBtn);
    }
    public void AdminLogin_OnHoldConsumer() throws InterruptedException {
        driver.navigate().to(AdminUrl);
        driver.findElement(Email).sendKeys("gabriel.sze@yopmail.com");
        click(Submitbtn);
        signUp_OTP_read("gabriel.sze@yopmail.com");
        //  driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(5000);
        click(LoginBtn);
        Thread.sleep(1000);
        click(ViewBtn);
        //verify the user
        click(OnHoldBtn);
        driver.findElement(OnHoldReasons).sendKeys("This is by automation");
        click(OnHoldReasonBtn);
        Thread.sleep(2000);
    }
    public void BuySell_Accept() throws InterruptedException {

    }


}
