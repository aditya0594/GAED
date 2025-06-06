package Pageobjects;



import baseClass.TestBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.utility;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;


public class SignUp extends TestBase {
    String ConsumerSignUpEmail ="";
    By getSign_Up_Btn= By.xpath("//*[contains(text(),'Sign Up')]");
    By signup_page_title = By.xpath("//*[@class='py-2 xs:text-2xl xl:text-3xl 3xl:text-4xl text-black-900 font-semibold']");
    By firstname = By.xpath("//input[@name='firstName']");
    By lastname = By.xpath("//input[@name='lastName']");
    By email = By.xpath("//input[@name='email']");
    By agreementChk = By.xpath("//input[@name='tncCheck']");
    By sentOTPbtn = By.xpath("//button[@type='submit']");
    By invalidFistnameMesss = By.xpath("//div[@class='w-full md:w-1/2 md:mb-0 xs:mb-3 px-3']//div//span[contains(@class,'block my-2 text-xs font-normal text-red-500')][normalize-space()='Field cannot contain any special characters.']");
    By invalidLastnameMesss = By.xpath("//div[@class='w-full md:w-1/2 px-3']//div//span[contains(@class,'block my-2 text-xs font-normal text-red-500')][normalize-space()='Field cannot contain any special characters.']");
    By invalidEmailMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[2]/div/div/span");
    By EmpFistnameMesss = By.xpath("(//*[@class='block my-2 text-xs font-normal text-red-500 '])[1]");
    By EmpLastnameMesss = By.xpath("(//*[@class='block my-2 text-xs font-normal text-red-500 '])[2]");
    By EmpEmailMesss = By.xpath("(//*[@class='block my-2 text-xs font-normal text-red-500 '])[3]");
    static By yopmailemail = By.xpath("//input[@id='login']");
    static By yopmailarrow = By.xpath("//i[@class='material-icons-outlined f36']");
    static By Go_to_website = By.xpath("//button[span[contains(text(),'Go to Website')]]");
    // By firstname = By.xpath("//input[@name='firstName']");

    public void getSign_up_btn (){
    }
    public void validate_signup_page(){
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
    }


    public void consumer_Sign_up_Step_One(String fname, String lname) throws InterruptedException {
        Thread.sleep(2000);
        // String signbtn = driver.findElement(getSign_Up_Btn).getText();
        //  Assert.assertEquals(signbtn,"Sign Up");
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(firstname).sendKeys(fname);
        driver.findElement(lastname).sendKeys(lname);
        waitForElement(EmailField);
        // ConsumerSignUpEmail = properties.getProperty("SignupId");
        //adityapawarsignup@yopmail.com
        ConsumerSignUpEmail = utility.randomEmailSignup();
        System.out.println("Created email id : " + ConsumerSignUpEmail);
        write_excel(1,0, ConsumerSignUpEmail);
        driver.findElement(EmailField).sendKeys(ConsumerSignUpEmail);
        driver.findElement(agreementChk).click();
        Thread.sleep(2000);

       // signUp_OTP_read(ConsumerSignUpEmail);
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
                System.out.println("Captured OTP API Response: " + res.getUrl());

                Optional<Network.GetResponseBodyResponse> responseBody =
                        Optional.ofNullable(devTools.send(Network.getResponseBody(requestId)));

                if (responseBody.isPresent()) {
                    String body = responseBody.get().getBody();
                  //  System.out.println("Full Response: " + body);

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
        driver.findElement(sentOTPbtn).click();
        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(3000);
        driver.findElement(SignupVerifyEmailbtn).click();
        Thread.sleep(3000);
        driver.navigate().refresh();
        Thread.sleep(2000);

    }
    public void invalid_signup_field() {
        driver.findElement(getSign_Up_Btn).click();
        validate_signup_page();
        driver.findElement(firstname).sendKeys(HomePage.INFIRSTNAME);
        waitForElement(invalidFistnameMesss);
        String invalidfirstnameMess = driver.findElement(invalidFistnameMesss).getText();
        Assert.assertEquals("Field cannot contain any special characters.",invalidfirstnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(lastname).sendKeys(HomePage.INLASTNAME);
        waitForElement(invalidFistnameMesss);
        String invalidlastnameMess = driver.findElement(invalidLastnameMesss).getText();
        Assert.assertEquals("Field cannot contain any special characters.",invalidlastnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(email).sendKeys(HomePage.INEMAIL);
        waitForElement(invalidFistnameMesss);
        String invalidemailMess = driver.findElement(invalidEmailMesss).getText();
        Assert.assertEquals("This looks like an invalid email (eg: abc@xyz.com)",invalidemailMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
    }
    public void addressDropdown(String addressInitials,By xpathlocation) throws InterruptedException {
        driver.findElement(xpathlocation).sendKeys(addressInitials); // sent the address initials
//"//input[@name='location']"
        List<WebElement> suggestAddress = driver.findElements(By.xpath("//li[@class='text-sm font-normal text-black-900 px-3 py-2 cursor-pointer hover:bg-[#f6f6f6]']"));
        //driver.findElements(By.xpath("//ul[@class='mt-2 border border-gray-300 shadow-md rounded-x1 rounded-bl-xl rounded-br-xl py-1 absolute mx-[2%] left-e top-full w-[96%] z-50 bg-white']/li"));

        System.out.println("Number of ul elements found: " + suggestAddress.size());

        // Example: Interact with the first ul element
        WebElement firstUl = suggestAddress.get(0);
        firstUl.click();
        Thread.sleep(1000);


        //class="mt-2 border border-gray-300 shadow-md rounded-x1 rounded-bl-xl rounded-br-xl py-1 absolute mx-[2%] left-e top-full w-[96%] z-50 bg-white"

        // Find all ul elements with the specified class
       /* List<WebElement> ulElements = driver.findElements(By.xpath("//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-r8u8y9']"));

        // Check how many elements were found
        System.out.println("Number of ul elements found: " + ulElements.size());

        // Example: Interact with the first ul element
        WebElement firstUl = ulElements.get(0);
        // This is the same as using [1] in XPath
        System.out.println("First ul element text: " + firstUl.getText());
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/ul/li[1]")).click();

        // Example: Interact with the second ul element, if it exists
       *//* if (ulElements.size() > 1) {
            WebElement secondUl = ulElements.get(1);  // This is the same as using [2] in XPath
            System.out.println("Second ul element text: " + secondUl.getText());

        }*//*

        Thread.sleep(1000);

        String GetConsumeremailFromProfile  = driver.findElement(By.xpath("//div[contains(text(),'"+email+"')]")).getText();
        Assert.assertEquals(email,GetConsumeremailFromProfile);*/
    }

    public void Error_message(By element, String actucalmessage){
        String invalidfirstnameMess = driver.findElement(element).getText();
        Assert.assertEquals(actucalmessage,invalidfirstnameMess);
    }
    public void verify_text(By element, String actucalmessage){
        String invalidfirstnameMess = driver.findElement(element).getText();
        Assert.assertEquals(actucalmessage,invalidfirstnameMess);

    }
    public void Error_message_Empty_field(By element){
        String invalidfirstnameMess = driver.findElement(element).getText();
        Assert.assertEquals("Field is Required.",invalidfirstnameMess);

    }


    public void signup_button(){
        driver.findElement(getSign_Up_Btn).click();
        verify_text(signup_page_title, "Let's get you an Account");
    }

    public void Empty_signup_field1(){
        signup_button();
        driver.findElement(agreementChk).click();
        driver.findElement(sentOTPbtn).click();
        Error_message_Empty_field(EmpFistnameMesss);
        Error_message_Empty_field(EmpLastnameMesss);
        Error_message_Empty_field(EmpEmailMesss);
    }

    public void Empty_signup_field() {
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(agreementChk).click();
        driver.findElement(sentOTPbtn).click();
        driver.findElement(firstname).sendKeys("");
        waitForElement(invalidFistnameMesss);
        String invalidfirstnameMess = driver.findElement(EmpFistnameMesss).getText();
        Assert.assertEquals("Field is Required.",invalidfirstnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(lastname).sendKeys("");
        waitForElement(invalidFistnameMesss);
        String invalidlastnameMess = driver.findElement(EmpLastnameMesss).getText();
        Assert.assertEquals("Field is Required.",invalidlastnameMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
        driver.findElement(email).sendKeys("");
        waitForElement(invalidFistnameMesss);
        String invalidemailMess = driver.findElement(EmpEmailMesss).getText();
        Assert.assertEquals("Field is Required.",invalidemailMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
    }
    By EmailField = By.xpath("//input[@name='email']");
    By SignupVerifyEmailbtn = By.xpath("//button[@type='button']");
    By firstOtpBlock = By.xpath("//*[@id='otp']/div/input[1]");

    public static String CreatRandomEmailyopmail() throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        String defaultTab = driver.getWindowHandle();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        // Simulate human-like interaction: Wait for a short duration
        Thread.sleep(2000);
        driver.get("https://yopmail.com/en/wm");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title='Generate a random email address']")).click();
        Thread.sleep(2000);
        String EmailGenerateed = driver.findElement(By.xpath("//*[@id=\"egen\"]")).getText();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[normalize-space()='Check Inbox']")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        return EmailGenerateed;
        
    }
    public static void signUp_OTP_read(String signupEmail) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        String defaultTab = driver.getWindowHandle();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());


        // Simulate human-like interaction: Wait for a short duration
        Thread.sleep(2000);

        driver.get("https://yopmail.com/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div")).click();
        waitForElement(yopmailemail);
        WebElement emailInput = driver.findElement(yopmailemail);
        emailInput.clear();
        typeLikeAHuman(emailInput,signupEmail);
        //driver.findElement(yopmailemail).sendKeys(emailforInbox);
        driver.findElement(yopmailarrow).click();

        // Simulate human-like interaction: Mouse movement after clicking
        moveMouseAround(driver, driver.findElement(By.xpath("//i[contains(text(),'\uE16C')]")));


        Thread.sleep(10000);
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

       // driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        driver.close();

        // Switch back to the original tab
        driver.switchTo().window(defaultTab);
    }


    By MobileNumberField = By.xpath("//input[@type='tel' and @value='+65']");
    By CalandorClick = By.xpath("//button[@type='button']");
    By Nationality_field = By.xpath("(//div[@class='css-1xc3v61-indicatorContainer'])[1]");
    By Nationality_field_value = By.xpath("//div[@class='css-1kf7eui-option']");
    By Address_field = By.xpath("//input[@name='address']");
    By proectAddressGoogleField =  By.xpath("//body/div[@id='root']/div[@class='App']/div/div[@class='h-full']/div[@class='mx-auto']/div[@class='flex justify-center']/div[@class='w-full flex']/div[1]");
    By ID_proof = By.xpath("(//div[@class='css-hlgwow'])[2]");
    By ID_proof_value = By.xpath("//input[@id='react-select-4-input']");
    By Address_proof = By.xpath("(//div[@class='css-hlgwow'])[3]");
    By Address_proof_value = By.xpath("//input[@id='react-select-5-input']"); //react-select-5-input
    By Date_of_birth = By.xpath("//input[@placeholder='dd/mm/yyyy']");

    By Address_Browse_btn = By.xpath("//label[@for='addressProofFile']//span[contains(text(),'Browse Files')]");
    By Id_Browse_btn = By.xpath("//label[@for='idProofFile']//span[contains(text(),'Browse Files')]");
    By Stepper_two_submitBtn = By.xpath("//button[@class='mt-2 tracking-wide font-semibold bg-primary text-white w-full py-3 rounded-full transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none']");
    By Form_sumitted_message = By.xpath("//*[@class='xs:text-sm sm:text-base xl:text-xl font-normal text-black-900 text-center xs:px-10 xl:px-72 2xl:px-96 3xl:px-[500px] my-5']");
    By Image_Address_Avaliable =  By.xpath("//span[@class=' truncate max-w-[90%] text-sm font-normal text-black-900']");
    By Image1_ID_Avaliable =  By.xpath("//span[@class=' truncate max-w-[90%] text-sm font-normal text-black-900']");

    public void consumer_Sign_up_Step_Two() throws InterruptedException, AWTException, IOException {
        //waitForElement(MobileNumberField);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileNumberField));
        Thread.sleep(1000);
       // WebElement element = driver.findElement(MobileNumberField);
/*        System.out.println(driver.getPageSource());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input[value=\"+65\"][type=\"tel\"').value='6534567812';");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("screenshot for number.png"));*/

        driver.findElement(MobileNumberField).sendKeys(randomMobile());
//        WebElement mobileNumberField = driver.findElement(MobileNumberField);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        mobileNumberField.click();
//        Thread.sleep(2000);
//        js.executeScript("arguments[0].value=arguments[1];", mobileNumberField, randomMobile());

        // Simulate pressing Tab using JavaScript
       // js.executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keydown', {key: 'Tab'}));", mobileNumberField);

        //Calendar
        driver.findElement(Date_of_birth).click();
        Thread.sleep(1000);
        //month selection
        WebElement dateDropdown = driver.findElement(By.xpath("//*[@class='react-datepicker__month-select']"));
        Select select_date = new Select(dateDropdown);
        select_date.selectByValue("3");
        Thread.sleep(1000);
        //Year selection
        WebElement yearDropdown = driver.findElement(By.xpath("//*[@class='react-datepicker__year-select']"));
        Select select_year = new Select(yearDropdown);
        select_year.selectByValue("1994");
        Thread.sleep(1000);
        //Day click
        driver.findElement(By.xpath("//*[@class='react-datepicker__day react-datepicker__day--005']")).click();
        Thread.sleep(1000);

        utility.dropdown(Nationality_field,Nationality_field_value,"Indian");
//        driver.findElement(Nationality_field).click();
//        Thread.sleep(1000);
//        List<WebElement> nationality = driver.findElements(Nationality_field_value);
//        for(WebElement element : nationality){
//            String text = element.getText();
//            System.out.println("The nationality list : " + text );
//            if(text.equals("Indian")){
//                driver.findElement(Nationality_field_value).click();
//            }
//            break;
//        }



        driver.findElement(Address_field).click();
        Thread.sleep(2000);
        By projectAddress = By.xpath("//input[@name='address']");
        addressDropdown("chatra",projectAddress);
        Thread.sleep(1000);
       // driver.findElement(proectAddressGoogleField).click();
        tab(Address_field);

        //button[@type='submit']
        waitForElement(ID_proof);
       // driver.findElement(ID_proof).click();
        Thread.sleep(1000);
        driver.findElement(ID_proof).click();
        driver.findElement(ID_proof_value).sendKeys("Aadhaar Card");
        Thread.sleep(500);
        tab(ID_proof_value);






        Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
        String path = System.getProperty("user.dir");
        String relativePath = path+"/src/resources/image1.jpg";
        // Convert to Path object
        Path convertedpath = Paths.get(relativePath);
        String FinalPAth = convertedpath.toString();
        // Create a StringSelection object containing the OTP
         StringSelection stringSelection = new StringSelection(FinalPAth);
        // Set the StringSelection as the current contents of the clipboard
        clipboard1.setContents(stringSelection,null);

        WebElement IDfileInput = driver.findElement(By.xpath("//input[@id='idProofFile']"));
        IDfileInput.sendKeys(FinalPAth);



        Thread.sleep(1000);
        driver.findElement(Address_proof_value).sendKeys("Bank Passbook with Address");
        Thread.sleep(5000);
        tab(Address_proof_value);


        WebElement AddressfileInput = driver.findElement(By.xpath("//input[@id='addressProofFile']"));
        AddressfileInput.sendKeys(FinalPAth);


        click(Stepper_two_submitBtn);


        Thread.sleep(5000);


   /*     waitForElement(Form_sumitted_message);
        String ActualFormText = driver.findElement(Form_sumitted_message).getText();
        String expectedString = "Thank you for submitting your KYC details! Your information has been received and is currently under review by the GAED Team. You will be able to access the platform once your KYC is approved. We appreciate your patience and cooperation. If you have any questions or need further assistance, please feel free to contact our support team via\"enquiries@gaed2.com\". Thank you for choosing GAED!";
        Assert.assertEquals(ActualFormText,expectedString);

        Thread.sleep(5000);*/
    }

}
