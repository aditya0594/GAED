package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.utility;

import java.io.IOException;

import static Pageobjects.SignUp.signUp_OTP_read;

public class MarketPlaceAdmin extends TestBase {

    static String AdminUrl = "https://marketplace.qa.gaedkeeper.com/admin/login";

    BuyAndSell buyAndSell = new BuyAndSell();
    static By Email = By.xpath("//input[@name='email']");
    static By Submitbtn = By.xpath("//span[@class='text-base font-medium text-white']");
    static By firstOtpBlock = By.xpath("//input[@aria-label='Please enter OTP character 1']");

    static By LoginBtn = By.xpath("//button[@type='button']");

    By BuySellBtn = By.xpath("(//*[normalize-space()='Buy/Sell'])[2]");
    By eyeBtn = By.xpath("(//span[@class='cursor-pointer hover:underline'])[1]");
    By Projectname = By.xpath("//h3[@class='font-medium xs:text-xl sm:text-2xl lg:text-3xl text-black-900']");
    By StatusDropdown = By.xpath("//input[@id='react-select-4-input']");
    By SubmitBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");
    By checkboxAssessment = By.xpath("(//input[@class='PrivateSwitchBase-input css-j8yymo'])[2]");
    By continueBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By confirmBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By OnHoldBtn = By.xpath("//span[normalize-space()='Put On Hold']");
    By OnHoldReasons = By.xpath("//textarea[@placeholder='Kindly specify the reason here']");
    By OnHoldReasonBtn = By.xpath("//button[normalize-space()='Submit']");
    //textarea[@placeholder='Kindly specify the reason here']
    public void marketAdminhomepage() throws IOException {

        driver.navigate().to("https://marketplace.qa.gaedkeeper.com/admin/login");
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()).close();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
    }
    public void marketAdminLogin() throws InterruptedException {
        driver.navigate().to(AdminUrl);
        driver.navigate().refresh();
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
    public void Admin_buyandSell_publish() throws InterruptedException {
        Thread.sleep(2000);
        click(BuySellBtn);
        Thread.sleep(1000);
        click(eyeBtn);
       /* WebElement pro = driver.findElement(Projectname);
        String Projectname = pro.getText();
        String created_project_name = buyAndSell.project_Name;*/
//        System.out.println("Project name is which is created : " + created_project_name);
//        Assert.assertEquals(Projectname,created_project_name);
        Thread.sleep(2000);
        driver.findElement(StatusDropdown).sendKeys("Publish" + Keys.TAB);

       // utility.scrollToElement(SubmitBtn);
        Thread.sleep(2000);
        click(SubmitBtn);

        }

    }

