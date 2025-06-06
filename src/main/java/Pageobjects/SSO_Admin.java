package Pageobjects;

import baseClass.TestBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.emulation.Emulation;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
import org.openqa.selenium.devtools.v132.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.utility;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static Pageobjects.SignUp.signUp_OTP_read;

public class SSO_Admin extends TestBase {




    static String AdminUrl = "https://qa.gaedkeeper.com/admin/login";



    static By Email = By.xpath("//input[@name='email']");
    static By Submitbtn = By.xpath("//span[@class='text-base font-medium text-white']");
    static By firstOtpBlock = By.xpath("//input[@aria-label='Please enter OTP character 1']");

    static By LoginBtn = By.xpath("//button[@type='button']");

    By Email23 = By.xpath("//input[@name='email']");
    By ViewBtn = By.xpath("//span[@class='cursor-pointer hover:underline']");
  //  By ViewBtn = By.xpath("//tr[@class='MuiTableRow-root MuiTableRow-hover css-1n0a3jb']/td[5]");


    By VerifyUserBtn = By.xpath("//span[normalize-space()='Verify User']");
    By checkboxMarkertplace = By.xpath("(//input[@class='PrivateSwitchBase-input css-j8yymo'])[1]");
    By checkboxAssessment = By.xpath("(//input[@class='PrivateSwitchBase-input css-j8yymo'])[2]");
    By continueBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By confirmBtn = By.xpath("//*[@class='text-sm font-medium text-white']");
    By OnHoldBtn = By.xpath("//span[normalize-space()='Put On Hold']");
    By OnHoldReasons = By.xpath("//textarea[@placeholder='Kindly specify the reason here']");
    By OnHoldReasonBtn = By.xpath("//button[normalize-space()='Submit']");

    public SSO_Admin() throws IOException {
    }

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

        driver.findElement(Email).sendKeys("gabriel.sze@yopmail.com");  Thread.sleep(2000);
        // **Start DevTools for Capturing OTP**
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // **Print All Network Responses (Debugging)**
        devTools.addListener(Network.responseReceived(), response -> {
            RequestId requestId = response.getRequestId();
            Response res = response.getResponse();

            // **Print ALL API responses**
          //  System.out.println("Network Response URL: " + res.getUrl());

            // **Check if the OTP API request is being captured**
            if (res.getUrl().contains("/send-otp")) {  // Change URL based on actual API
                //System.out.println("Captured OTP API Response: " + res.getUrl());

                Optional<Network.GetResponseBodyResponse> responseBody =
                        Optional.ofNullable(devTools.send(Network.getResponseBody(requestId)));

                if (responseBody.isPresent()) {
                    String body = responseBody.get().getBody();
                   // System.out.println("Full Response: " + body);

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
       // signUp_OTP_read("gabriel.sze@yopmail.com");
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

        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // **Print All Network Responses (Debugging)**
        devTools.addListener(Network.responseReceived(), response -> {
            RequestId requestId = response.getRequestId();
            Response res = response.getResponse();

            // **Print ALL API responses**
            System.out.println("Network Response URL: " + res.getUrl());

            // **Check if the OTP API request is being captured**
            if (res.getUrl().contains("/send-otp")) {  // Change URL based on actual API
                System.out.println("Captured OTP API Response: " + res.getUrl());

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
        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(5000);
        click(LoginBtn);
        waitForElement(ViewBtn);
        click(ViewBtn);
        //verify the user
        click(VerifyUserBtn);
        click(checkboxMarkertplace);
       // click(checkboxAssessment);
        click(continueBtn);
        Thread.sleep(2000);
        click(confirmBtn);
    }
    By StatusDropdown = By.xpath("//div[@class='css-1gdrfkc']");
    By ListOfOptions = By.id("//div[@tabindex='-1']");
    By holdConsumerEmail = By.xpath("(//tr[@class='MuiTableRow-root MuiTableRow-hover css-1n0a3jb']/td[2])[1]");
    public void AdminLogin_OnHoldConsumer() throws InterruptedException {
        driver.navigate().to(AdminUrl);
        driver.findElement(Email).sendKeys("gabriel.sze@yopmail.com");
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // **Print All Network Responses (Debugging)**
        devTools.addListener(Network.responseReceived(), response -> {
            RequestId requestId = response.getRequestId();
            Response res = response.getResponse();

            // **Print ALL API responses**
           // System.out.println("Network Response URL: " + res.getUrl());

            // **Check if the OTP API request is being captured**
            if (res.getUrl().contains("/send-otp")) {  // Change URL based on actual API
               // System.out.println("Captured OTP API Response: " + res.getUrl());

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
        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(5000);
        click(LoginBtn);
        Thread.sleep(1000);
        waitForElement(ViewBtn);
        click(ViewBtn);
        waitForElement(OnHoldBtn);
        click(OnHoldBtn);
        driver.findElement(OnHoldReasons).sendKeys("This is by automation");
        waitForElement(OnHoldReasonBtn);
        click(OnHoldReasonBtn);
        Thread.sleep(5000);
        driver.findElement(StatusDropdown).click();
        Thread.sleep(2000);
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='css-1kf7eui-option']"));
        for(WebElement ele : list){
                if(ele.getText().equals("Hold")){
                    System.out.println("this is option name " + ele.getText());
                    ele.click();
                }
        }
        waitForElement(holdConsumerEmail);
        String signupconsumerEmail =  readLastValue(1,"Sheet1");
        System.out.println("Create Consumer email id from the excel : "+signupconsumerEmail);

        String consumerEmailToVerify = driver.findElement(holdConsumerEmail).getText();
        System.out.println("From the Hold list Consumer email id from the excel : "+consumerEmailToVerify);



    }
    public void BuySell_Accept() throws InterruptedException {

    }


}
