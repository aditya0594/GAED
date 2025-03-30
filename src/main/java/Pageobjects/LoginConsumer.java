package Pageobjects;


import baseClass.TestBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static utils.utility.AssertTextBtn;


public class LoginConsumer extends TestBase {

    static Set<Cookie> cookies;
    static By loginBtn = By.xpath("//div[@class='hidden lg:flex']//span[text()='Login']");
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
    static By VerifyEmailbtn = By.xpath("//button[@type='button']");
    static By ConsumerpProfileBtn = By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary flex items-center space-x-1 css-1ujsas3']");
    static By ViewConsumerProfile = By.xpath("(//*[@id=\"basic-menu\"]/div[3]/ul/li[1])[1]");
    static By VerifyConsumerEmail = By.xpath("//div[contains(text(),'" + HomePage.EMAIL + "')]");
    static By InvalidemailLoginMes = By.xpath("//span[@class='block my-2 text-xs font-normal text-red-500 ']");
    static By EmptyEmailMess = By.xpath("//span[@class='block my-2 text-xs font-normal text-red-500 ']");
    static By EmaiDoesNotexistlMess = By.xpath("//span[@class='block my-2 text-xs font-normal text-red-500 ']");
    static By InvalidOTPmessage = By.xpath("//*[contains(text(),'Invalid OTP. Please try again.')]");
    static By SignupLinkLogin = By.xpath("//span[@class='font-medium text-primary hover:underline cursor-pointer']");
    static By SignupTitleOfLink = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div/div/form/div[2]/div/p");
    static By Marketplace = By.xpath("//div[@class='relative transition-transform duration-300 hover:scale-105 cursor-pointer']//img[@class='rounded-lg object-cover object-center mx-auto  ']");
    static By ProfileBtn = By.xpath("//*[@class='w-5 h-5 text-white group-hover:text-primary']");
    static By Logout = By.xpath("//*[@class='w-5 h-5 text-white group-hover:text-primary']");
    By projectname = By.xpath("(//div[@class='rounded-3xl p-4 border border-gray-300 flex flex-col'])[1]");
    static By projectname1 = By.xpath("(//div[@class='rounded-3xl p-4 border border-gray-300 flex flex-col'])[1]");
    // By projectname = By.xpath("(//div[@class='rounded-3xl p-4 border border-gray-300 flex flex-col'])[1]");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    //  static By SentOtpBtn = By.xpath("//span[@class='ml-3']");
    String Email;

    public static void Login_OTP_read(String emailforInbox) throws InterruptedException {
        String previousTab = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript("window.open()");
        Thread.sleep(2000);

        // Switch to new tab (latest opened)
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(previousTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Simulate human-like interaction: Wait for a short duration
        Thread.sleep(2000);
        driver.get("https://yopmail.com/en/wm");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div")).click();
        waitForElement(yopmailemail);
        WebElement emailInput = driver.findElement(yopmailemail);
        emailInput.clear();
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
            clipboard.setContents(stringSelection, null);
            System.out.println("Extracted OTP: " + otp);
        } else {
            System.out.println("No OTP found in the message.");
        }
        driver.close();
        driver.switchTo().window(previousTab);
        Thread.sleep(2000);
    }

    public static void Veriyconsumerprofile(String email) throws InterruptedException {
        // waitForElement(ConsumerpProfileBtn);
        //  driver.findElement(ConsumerpProfileBtn).click();
        //Thread.sleep(8000);
        // driver.findElement(By.xpath("//*[@class='mr-2 w-5 h-5']")).click();
        Thread.sleep(5000);

        // Find all ul elements with the specified class
        List<WebElement> ulElements = driver.findElements(By.xpath("//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters !text-sm !font-normal !text-black-100 css-5dycmn']"));

        // Check how many elements were found
        System.out.println("Number of ul elements found: " + ulElements.size());

        // Example: Interact with the first ul element
        WebElement firstUl = ulElements.get(0);
        // This is the same as using [1] in XPath
        System.out.println("First ul element text: " + firstUl.getText());
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/ul/li[1]")).click();

        // Example: Interact with the second ul element, if it exists
       /* if (ulElements.size() > 1) {
            WebElement secondUl = ulElements.get(1);  // This is the same as using [2] in XPath
            System.out.println("Second ul element text: " + secondUl.getText());

        }*/

        Thread.sleep(1000);

        String GetConsumeremailFromProfile = driver.findElement(By.xpath("//div[contains(text(),'" + email + "')]")).getText();
        Assert.assertEquals(email, GetConsumeremailFromProfile);
    }

    /* public static void InvalidLoginInputs() throws InterruptedException {

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
       }*/
    public static void SignupLinkOnLogin() throws InterruptedException {

        driver.findElement(loginBtn).click();
        String signupTitle_on_login = driver.findElement(SignupTitleOfLink).getText();
        Assert.assertEquals("Here for the first time? Sign Up", signupTitle_on_login);
        driver.findElement(SignupLinkLogin).click();

    }

    /*public static void LoginConsumerSuceessful() throws InterruptedException {
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
        Login_OTP_read(HomePage.EMAIL);
        driver.findElement(OTPfirstbox).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        driver.findElement(VerifyEmailbtn).click();
        Thread.sleep(3000);
        waitForElement(Marketplace);
        click(Marketplace);
        Thread.sleep(3000);
    }
*/
    public static void LoginConsumerSuceessful() throws InterruptedException {
        String Loginbtntext = driver.findElement(loginBtn).getText();
        Assert.assertEquals("Login", Loginbtntext);
        driver.findElement(loginBtn).click();
        String verifyLoginpage = driver.findElement(loginPageVerify).getText();
        Assert.assertEquals("Welcome Back!", verifyLoginpage);
        String LoginemailTitle = driver.findElement(Loginemailtitle).getText();
        Assert.assertEquals("Email Address*", LoginemailTitle);
        driver.findElement(LoginEmail).sendKeys(HomePage.EMAIL);
        String SentOTPtitle = driver.findElement(SentOtpBtn).getText();
        Assert.assertEquals("Send OTP", SentOTPtitle);


        Thread.sleep(2000);
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
                // System.out.println("Captured OTP API Response: " + res.getUrl());

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
        driver.findElement(SentOtpBtn).click();
        // **Wait for some time to allow capturing**
        Thread.sleep(2000);
        driver.findElement(OTPfirstbox).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        driver.findElement(VerifyEmailbtn).click();
        //Thread.sleep(2000);
        waitForElement(Marketplace);
        click(Marketplace);

        waitForElement(projectname1);


    }

    public static void LoginVendor(String vendorEmail) throws InterruptedException {
        String Loginbtntext = driver.findElement(loginBtn).getText();
        Assert.assertEquals("Login", Loginbtntext);
        driver.findElement(loginBtn).click();
        String verifyLoginpage = driver.findElement(loginPageVerify).getText();
        Assert.assertEquals("Welcome Back!", verifyLoginpage);
        String LoginemailTitle = driver.findElement(Loginemailtitle).getText();
        Assert.assertEquals("Email Address*", LoginemailTitle);
        driver.findElement(LoginEmail).sendKeys(vendorEmail);
        String SentOTPtitle = driver.findElement(SentOtpBtn).getText();
        Assert.assertEquals("Send OTP", SentOTPtitle);


        Thread.sleep(2000);
        // **Start DevTools for Capturing OTP**
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // **Print All Network Responses (Debugging)**
        devTools.addListener(Network.responseReceived(), response -> {
            RequestId requestId = response.getRequestId();
            Response res = response.getResponse();

            // **Print ALL API responses**
            //System.out.println("Network Response URL: " + res.getUrl());

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
        driver.findElement(SentOtpBtn).click();
        // **Wait for some time to allow capturing**
        Thread.sleep(2000);
        driver.findElement(OTPfirstbox).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        driver.findElement(VerifyEmailbtn).click();
        Thread.sleep(2000);
        waitForElement(Marketplace);
        click(Marketplace);
        Thread.sleep(3000);


    }

    public static void consumerLogout(String option) throws InterruptedException {
        Thread.sleep(2000);
        click(ProfileBtn);
        Thread.sleep(2000);
        waitForElement(Logout);
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
        //driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString()).close();
        Thread.sleep(2000);

    }

    public static void Veriy_consumer_profile() {
        waitForElement(ConsumerpProfileBtn);
        driver.findElement(ConsumerpProfileBtn).click();
        driver.findElement(ViewConsumerProfile).click();
        String GetConsumeremailFromProfile = driver.findElement(VerifyConsumerEmail).getText();
        Assert.assertEquals(HomePage.EMAIL, GetConsumeremailFromProfile);

    }

    public static void verify_LoginConsumerSuceessful() throws InterruptedException {


    }

    public void Login_Btn_home() {

        driver.findElement(loginBtn).click();
    }

    public void Verify_login_Page() {
        AssertTextBtn(loginPageVerify, "Welcome Back!");
    }

    public void Login_Email(String email) throws IOException {
        //Email = utility.property("email").toString();
        driver.findElement(LoginEmail).sendKeys(email);
    }

    public void invalid_validation() {
        String element = driver.findElement(InvalidemailLoginMes).getText();
        Assert.assertEquals("This looks like an invalid email (eg: abc@xyz.com)", element);
    }

    public void Empty_invalid_validation() {
        String element = driver.findElement(EmptyEmailMess).getText();
        Assert.assertEquals("Field is Required.", element);
    }

    public void invalidOTP_message() throws InterruptedException {
        Thread.sleep(2000);
        // ✅ Use JavaScript to force visibility

        //By.xpath("//div[contains(@class,'flex text-sm font-medium') and contains(@class,'text-gray-400') and contains(@class,'mb-10')]//span[contains(@class,'text-red-500')]")
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );

        // ✅ Use the new accurate XPath for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'flex text-sm font-medium') and contains(@class,'text-gray-400') and contains(@class,'mb-10')]//span[contains(@class,'text-red-500')]")
        ));

        // ✅ Force the element visible (if hidden)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", errorMessage);

        // waitForElement(InvalidOTPmessage);
        String element1 = driver.findElement(InvalidOTPmessage).getText();
        Assert.assertEquals("Invalid OTP. Please try again.", element1);
    }

    public void setOTPbuttonSimple() {
        waitForElement(SentOtpBtn);
        driver.findElement(SentOtpBtn).click();
    }

    public void sentOTPbtn() {
        driver.findElement(SentOtpBtn).click();
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
    }

    public void enterOTP() {
        driver.findElement(OTPfirstbox).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
    }

    public void InvalidOTP() {
        String randomOTP = "123456";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // Create a StringSelection object containing the OTP
        StringSelection stringSelection = new StringSelection(randomOTP);
        // Set the StringSelection as the current contents of the clipboard
        clipboard.setContents(stringSelection, null);
        System.out.println("Extracted OTP: " + randomOTP);
        driver.findElement(OTPfirstbox).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
    }

    public void verifyEmailbtn() throws InterruptedException {
        driver.findElement(VerifyEmailbtn).click();
       /* Thread.sleep(6000);
        WebElement element = driver.findElement(By.xpath("//*[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary flex items-center space-x-1 css-iyey26']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);*/
        //driver.findElement(By.xpath("//*[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary flex items-center space-x-1 css-iyey26']")).click();
    }


}
