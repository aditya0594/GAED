package Pageobjects;

import baseClass.TestBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.utility;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class MarketPlaceAdmin extends TestBase {

    static String AdminUrl = "https://marketplace.qa.gaedkeeper.com/admin/login";
    static By Email = By.xpath("//input[@name='email']");
    static By Submitbtn = By.xpath("//span[@class='text-base font-medium text-white']");
    static By firstOtpBlock = By.xpath("//input[@aria-label='Please enter OTP character 1']");
    static By LoginBtn = By.xpath("//button[@type='button']");
    BuyAndSell buyAndSell = new BuyAndSell();
    By BuySellBtn = By.xpath("(//*[normalize-space()='Buy/Sell'])[2]");
    By eyeBtn = By.xpath("(//span[@class='cursor-pointer hover:underline'])[1]");
    By Projectname = By.xpath("//h3[@class='font-medium xs:text-xl sm:text-2xl lg:text-3xl text-black-900']");
    By StatusDropdown = By.xzpath("//input[@id='react-select-4-input']");
    By SiteVisitStatusDropDownClick = By.xpath(" //div[@class='css-12oqyby-indicatorContainer']");
    By SiteVisitStatusDropDown = By.xpath("//input[@id='react-select-5-input']");
    By ProjectsBtn = By.xpath("(//*[normalize-space()='Projects'])[2]");
    By SubmitBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");
    By CalendorOKbtn = By.xpath("//div[@class='flex justify-end items-center p-5 space-x-4']//*[normalize-space()='OK']");
    //By FromeTime = By.xpath("//div[@class='MuiFormControl-root MuiTextField-root css-1b426h6']//input[@id=':rb:']");
    By monthyear = By.xpath(":r4:-grid-label");
    By calendorNetArrowBtn = By.xpath("//div[@class='MuiPickersArrowSwitcher-root css-gvoll6']//button[@title='Next month']");
    By yearArrow = By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall MuiPickersCalendarHeader-switchViewButton css-1euv0cy']");
    // By SubmitBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");


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
                            clipboard.setContents(stringSelection, null);

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

        //span[@class='MuiClockNumber-root css-vk8m51']
        // utility.scrollToElement(SubmitBtn);
        Thread.sleep(2000);
        click(SubmitBtn);

    }
    By TimeFrom = By.xpath("//div[@class='MuiFormControl-root MuiTextField-root css-1b426h6']//input[@id=':rb:']");
    By ClockClick  = By.xpath("//div[@class='MuiClock-squareMask css-6oh4ak']");

    By Clockhours = By.xpath("//div[@class='MuiClock-wrapper css-1b5m8nj']//*[@class='MuiClockNumber-root css-vk8m51']");
    By ClockMin = By.xpath("//div[@class='MuiClock-wrapper css-1b5m8nj']//*[@class='MuiClockNumber-root css-vk8m51']");
    By ClockOKbtn = By.xpath("//button[@type='button' and normalize-space()='OK']");
     By TimeTo = By.xpath("//div[@class='MuiFormControl-root MuiTextField-root css-1b426h6']//input[@id=':re:']");
     By VisitScheduleSubmitBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");
     By ProjectnamelistAdmin = By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1plsf6o']");
    // By SubmitBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");
    // By SubmitBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");

    public void Admin_Projects_publish() throws InterruptedException {
        Thread.sleep(2000);

        click(ProjectsBtn);
        Thread.sleep(1000);
        click(eyeBtn);
        waitForElement(SiteVisitStatusDropDownClick);
        click(SiteVisitStatusDropDownClick);
        waitForElementToBeClickable(SiteVisitStatusDropDown);
        Thread.sleep(1000);
        driver.findElement(SiteVisitStatusDropDown).sendKeys("Visit Scheduled");

        Thread.sleep(2000);
        driver.findElement(SiteVisitStatusDropDown).sendKeys(Keys.TAB);

        // Click on the year selection arrow
        driver.findElement(yearArrow).click();

        By allyears = By.xpath("//div[contains(@class, 'MuiYearCalendar-root')]//button");
        By AllDates = By.xpath("//div[contains(@class, 'MuiDayCalendar-weekContainer')]//button[contains(@class, 'MuiPickersDay-root')]");
        utility.calendorDateYear(allyears,AllDates,"25","2025");
        click(CalendorOKbtn);
        Thread.sleep(2000);
         //waitForElement(TimeFrom);
       // click(SubmitBtn);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Click on the from to To section
        WebElement timefrom = driver.findElement(TimeFrom);
        actions.moveToElement(timefrom).click().perform();

        //clock code
       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiPickersLayout-root css-16juibx']")));
        waitForElement(Clockhours);
        List<WebElement> hours = driver.findElements(Clockhours);
        for(WebElement h : hours){
            if (h.getText().equals("3")){
                try {
                    actions.moveToElement(h).pause(500).click().perform();
                    System.out.println("Hour 3 selected successfully!");
                    break;
                } catch (Exception e) {
                    System.out.println("Click failed, retrying...");
                }
            }
        }
        List<WebElement> minutes = driver.findElements(ClockMin);
            for(WebElement m : minutes){
                if(m.getText().equals("05")){
                    actions.moveToElement(m).pause(500).click().perform();
                    System.out.println("Hour 05 selected successfully!");
                    break;
                }
            }
            WebElement okBtn = driver.findElement(ClockOKbtn);
        actions.moveToElement(okBtn).pause(500).click().perform();
        Thread.sleep(1000);
        WebElement timeTo = driver.findElement(TimeTo);
        actions.moveToElement(timeTo).click().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiPickersLayout-root css-16juibx']")));
        waitForElement(Clockhours);
        List<WebElement> Tohours = driver.findElements(Clockhours);


        for(WebElement h : Tohours){
            if (h.getText().equals("3")){
                try {
                    actions.moveToElement(h).pause(500).click().perform();
                    System.out.println("Hour 3 selected successfully!");
                    break;
                } catch (Exception e) {
                    System.out.println("Click failed, retrying...");
                }
            }
        }
        List<WebElement> Tominutes = driver.findElements(ClockMin);
        for(WebElement m : Tominutes){
            if(m.getText().equals("05")){
                actions.moveToElement(m).pause(500).click().perform();
                System.out.println("Hour 05 selected successfully!");
                break;
            }
        }
        click(ClockOKbtn);
        waitForElement(SubmitBtn);
        click(VisitScheduleSubmitBtn);
        Thread.sleep(7000);

    }

    By siteVisiteScheduleText = By.xpath("//tr[@class='MuiTableRow-root MuiTableRow-hover css-1n0a3jb']/td[6]");
    public void Verify_Project_visitSchedule_status() throws InterruptedException {
        Thread.sleep(1000);
        click(ProjectsBtn);
        Thread.sleep(1000);
        List<WebElement> projectnames = driver.findElements(ProjectnamelistAdmin);
        String project_name_excel= readLastValue(0,"Project details");
        for(WebElement pro : projectnames){
            if(pro.getText().equals(project_name_excel)){
               String status = driver.findElement(siteVisiteScheduleText).getText();
                Assert.assertTrue(status.contains("Scheduled on "), "Visit Schedule is verified and changed");
                System.out.println("This is the status  : " + status);
            }
        }

    }
    static By ProfileBtn = By.xpath("//*[@class='w-5 h-5 text-white group-hover:text-primary']");
    static By Logout = By.xpath("(//*[normalize-space()='Logout']/li)[1]");
    public void AdminLogout(String option) throws InterruptedException {
        Thread.sleep(2000);
        click(ProfileBtn);
        Thread.sleep(2000);
      List<WebElement> optionsList = driver.findElements(By.xpath("//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-ubifyk']/li"));
        for (WebElement w : optionsList) {
            // Trim and compare text
            if (w.getText().trim().equals(option)) {
                try {
                    w.click();
                } catch (Exception e) {
                    // Use JavaScript Click as a fallback
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", w);
                }
                break; // Stop looping after clicking "Logout"
            }
        }
        driver.navigate().refresh();
        Thread.sleep(2000);
    }
    By SubmitGeneratingReportBtn = By.xpath("//button[@type='button' and normalize-space()='Submit']");
    By GenerateReportText = By.xpath("//tr[@class='MuiTableRow-root MuiTableRow-hover css-1n0a3jb']/td[6]");

    public void ProjectGenerateReport() throws InterruptedException {
        click(ProjectsBtn);
        waitForElementToBeClickable(eyeBtn);
        click(eyeBtn);
        waitForElement(SiteVisitStatusDropDownClick);
        click(SiteVisitStatusDropDownClick);
        Thread.sleep(1000);
        waitForElementToBeClickable(SiteVisitStatusDropDown);
        driver.findElement(SiteVisitStatusDropDown).sendKeys("Generating Report");
        click(SubmitGeneratingReportBtn);
       // waitForElement(GenerateReportText);

        List<WebElement> projectnames1 = driver.findElements(ProjectnamelistAdmin);
        String project_name_excel1= readLastValue(0,"Project details");
        for(WebElement pro : projectnames1){
            if(pro.getText().equals(project_name_excel1)){
                String status = driver.findElement(GenerateReportText).getText();
                Assert.assertTrue(status.contains("Generating Report"), "Generating Report is verified and changed");
                System.out.println("This is the status  : " + status);
            }
        }


        /*
        Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
        String path = System.getProperty("user.dir");
        String relativePath = path+"src/resources/Admin_assessment.pdf";
        // Convert to Path object
        Path convertedpath = Paths.get(relativePath);
        String FinalPAth = convertedpath.toString();
        // Create a StringSelection object containing the OTP
        StringSelection stringSelection = new StringSelection(FinalPAth);
        // Set the StringSelection as the current contents of the clipboard
        clipboard1.setContents(stringSelection,null);

//        click(image_upload);
//        WebElement IDfileInput = driver.findElement(By.xpath("//input[@id='document1File']"));
//        IDfileInput.sendKeys(FinalPAth);
        for(int i=1; i<=1;i++){
            WebElement project_image = driver.findElement(By.xpath("//input[@id='dropzone-file']"));
            project_image.sendKeys(FinalPAth);
        }
*/


    }
}


