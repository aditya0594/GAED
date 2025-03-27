package Pageobjects;

import baseClass.TestBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.utility;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Optional;

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
    By SiteVisitStatusDropDown = By.xpath("//input[@id='react-select-8-input']");
    By ProjectsBtn = By.xpath("(//*[normalize-space()='Buy/Sell'])[2]");
    By SubmitBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");
    By checkboxAssessment = By.xpath("(//input[@class='PrivateSwitchBase-input css-j8yymo'])[2]");
    By continueBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By confirmBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By OnHoldBtn = By.xpath("//span[normalize-space()='Put On Hold']");
    By OnHoldReasons = By.xpath("//textarea[@placeholder='Kindly specify the reason here']");
    By OnHoldReasonBtn = By.xpath("//button[normalize-space()='Submit']");
    //textarea[@placeholder='Kindly specify the reason here']
    public void marketAdminhomepage() throws IOException, InterruptedException {
        Thread.sleep(2000);
        driver.navigate().to("https://marketplace.qa.gaedkeeper.com/admin/login");
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()).close();
      //  driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
    }
    public void marketAdminLogin() throws InterruptedException {
        driver.navigate().to(AdminUrl);
        driver.navigate().refresh();
        driver.findElement(Email).sendKeys("gabriel.sze@yopmail.com");

        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // **Print All Network Responses (Debugging)**
        devTools.addListener(Network.responseReceived(), response -> {
            RequestId requestId = response.getRequestId();
            Response res = response.getResponse();

            // **Print ALL API responses**
         //   System.out.println("Network Response URL: " + res.getUrl());

            // **Check if the OTP API request is being captured**
            if (res.getUrl().contains("/send-otp")) {  // Change URL based on actual API
              //  System.out.println("Captured OTP API Response: " + res.getUrl());

                Optional<Network.GetResponseBodyResponse> responseBody =
                        Optional.ofNullable(devTools.send(Network.getResponseBody(requestId)));

                if (responseBody.isPresent()) {
                    String body = responseBody.get().getBody();
                    //System.out.println("Full Response: " + body);

                    // **Parse JSON and extract OTP**
                    JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
                    if (jsonObject.has("data")) {
                        JsonObject data = jsonObject.getAsJsonObject("data");
                        if (data.has("code")) {
                            String extractedOTP = data.get("code").getAsString();
                            System.out.println("Extracted OTP: " + extractedOTP);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            // Create a StringSelection object containing the OTP
                            StringSelection stringSelection = new StringSelection(extractedOTP);
                            // Set the StringSelection as the current contents of the clipboard
                            clipboard.setContents(stringSelection,null);

                        } else {
                            System.out.println("OTP field 'code' not found in response!");
                        }
                    } else {
                        System.out.println("Response does not contain 'data' object!");
                    }
                }
            }
        });
        click(Submitbtn);
        //  driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        Thread.sleep(1000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(1000);
        click(LoginBtn);
        Thread.sleep(1000);

    }
    public void Admin_buyandSell_publish() throws InterruptedException {
        Thread.sleep(2000);
        click(BuySellBtn);
        Thread.sleep(1000);
        click(eyeBtn);
        Thread.sleep(2000);
        driver.findElement(StatusDropdown).sendKeys("Publish");
        Thread.sleep(2000);
        driver.findElement(StatusDropdown).sendKeys(Keys.TAB);

       // utility.scrollToElement(SubmitBtn);
        Thread.sleep(2000);
        click(SubmitBtn);

        }
    public void Admin_Projects_publish() throws InterruptedException {
        Thread.sleep(2000);

        click(ProjectsBtn);
        Thread.sleep(1000);
        click(eyeBtn);

        Thread.sleep(2000);
        driver.findElement(SiteVisitStatusDropDown).sendKeys("Visit Scheduled");
        Thread.sleep(2000);
        driver.findElement(StatusDropdown).sendKeys(Keys.TAB);

        // utility.scrollToElement(SubmitBtn);
        Thread.sleep(2000);
        click(SubmitBtn);

    }

    }

