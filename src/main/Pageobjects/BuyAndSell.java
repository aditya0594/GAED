package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import static Pageobjects.LoginConsumer.cookies;

public class BuyAndSell extends TestBase {

    LoginConsumer LoginUser = new LoginConsumer();
     By BuySelltab =  By.xpath("//a[@class='text-sm text-primary bg-white rounded-full py-2 p-4 active']");
    By SellProjectButton =  By.xpath("//span[normalize-space()='Sell Project']");
    By HomeButton = By.xpath("//a[@class='text-sm text-white rounded-full py-2 p-4 hover:bg-white hover:text-primary']");
    public void validate_BuySell_buttons() throws InterruptedException {

        String  BuyandSelltitle =  driver.findElement(BuySelltab).getText();
        Assert.assertEquals("Buy/Sell",BuyandSelltitle);
        driver.findElement(BuySelltab).click();
        String  sellProjecttitle =  driver.findElement(SellProjectButton).getText();
        Assert.assertEquals("Sell Project",sellProjecttitle);
        driver.findElement(SellProjectButton).click();
    }
    public void validate_SellProject_button(){
        String  sellProjecttitle =  driver.findElement(SellProjectButton).getText();
        Assert.assertEquals("Sell Project",sellProjecttitle);
        driver.findElement(SellProjectButton).click();
    }
    By NextBtn = By.xpath("//span[normalize-space()='Back']");
    By SaveAsDraftbtn = By.xpath("//span[normalize-space()='Save as draft']");
    By Backbtn = By.xpath("//span[normalize-space()='Save as draft']");
    By ProjectpageTitle = By.xpath("//span[@class='bg-white absolute left-0 top-6 pr-8']");
    By invalidProjectTileFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[1]/div[1]/span[1]");
    By invalidProjectTypeFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[2]/div[1]/span[1]");
    By invalidProjectAddressFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[3]/div[1]/span[1]");
    By invalidProjectBidsFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[4]/div[1]/span[1]");
    By ProjectTileField = By.xpath("//span[@class='bg-white absolute left-0 top-6 pr-8']");
    public void  validate_next_saveAsDraft_back(){

        driver.findElement(SellProjectButton).click();
        boolean nextbtn = driver.findElement(NextBtn).isDisplayed();
        Assert.assertTrue(nextbtn);

        boolean saveAsbtn = driver.findElement(SaveAsDraftbtn).isDisplayed();
        Assert.assertTrue(saveAsbtn);

        boolean backbtn = driver.findElement(Backbtn).isDisplayed();
        Assert.assertTrue(backbtn);

    }
    public void invalidate_Stepper1_ProjectFields_button(){

        driver.findElement(SellProjectButton).click();
       driver.findElement(NextBtn).click();
       String projectDetailsTitle = driver.findElement(ProjectpageTitle).getText();

       Assert.assertEquals("Project Details",projectDetailsTitle);
        String ProjectTileError = driver.findElement(invalidProjectTileFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectTileError);

        String ProjectTypeerror = driver.findElement(invalidProjectTypeFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectTypeerror);

        String ProjectAddressError = driver.findElement(invalidProjectAddressFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectAddressError);

        String ProjectbidsError = driver.findElement(invalidProjectBidsFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectbidsError);

    }







}
