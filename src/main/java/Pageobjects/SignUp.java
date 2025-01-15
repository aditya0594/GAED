package Pageobjects;

import baseClass.TestBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Pageobjects.HomePage.properties;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static utils.utility.*;


public class SignUp extends TestBase {
    String ConsumerSignUpEmail ="";
    By getSign_Up_Btn= By.xpath("//span[@class='cursor-pointer hidden lg:inline-block py-2 px-6 text-sm 3xl:text-lg text-white border border-white font-medium rounded-full transition duration-200 group-hover:text-primary group-hover:border-primary']");
    By signup_page_title = By.xpath("//h3[@class='py-2 xs:text-2xl xl:text-3xl 3xl:text-4xl text-black-900 font-semibold']");
    By firstname = By.xpath("//input[@name='firstName']");
    By lastname = By.xpath("//input[@name='lastName']");
    By email = By.xpath("//input[@name='email']");
    By agreementChk = By.xpath("//input[@name='tncCheck']");
    By sentOTPbtn = By.xpath("//button[@type='submit']");
    By invalidFistnameMesss = By.xpath("//div[@class='w-full md:w-1/2 md:mb-0 xs:mb-3 px-3']//div//span[contains(@class,'block my-2 text-xs font-normal text-red-500')][normalize-space()='Field cannot contain any special characters.']");
    By invalidLastnameMesss = By.xpath("//div[@class='w-full md:w-1/2 px-3']//div//span[contains(@class,'block my-2 text-xs font-normal text-red-500')][normalize-space()='Field cannot contain any special characters.']");
    By invalidEmailMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[2]/div/div/span");
    By EmpFistnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[1]/div/span");
    By EmpLastnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[2]/div/span");
    By EmpEmailMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[2]/div/div/span");
    static By yopmailemail = By.xpath("//input[@id='login']");
    static By yopmailarrow = By.xpath("//i[@class='material-icons-outlined f36']");
    By EmailField = By.xpath("//input[@name='email']");

    By SignupVerifyEmailbtn = By.xpath("//button[@type='button']");
    By firstOtpBlock = By.xpath("//*[@id='otp']/div/input[1]");
    By MobileNumberField = By.xpath("//input[@value='+65']");
    By CalandorClick = By.xpath("//button[@type='button']");
    By Nationality_field = By.xpath("//*[@class='css-19bb58m']");
    By Nationality_field_value = By.id("react-select-3-input");   //react-select-4-input in case shows error
    By Address_field = By.xpath("//input[@name='address']");
    By proectAddressGoogleField =  By.xpath("//body/div[@id='root']/div[@class='App']/div/div[@class='h-full']/div[@class='mx-auto']/div[@class='flex justify-center']/div[@class='w-full flex']/div[1]");
    By ID_proof = By.xpath("//*[@id=\"react-select-3-input\"]");
    By ID_proof_value = By.id("react-select-31-input");
    By Address_proof = By.xpath("//*[@id='react-select-4-input']");
    By Address_proof_value = By.id("react-select-32-input");
    By Date_of_birth = By.xpath("//input[@placeholder='dd/mm/yyyy']");

    By Address_Browse_btn = By.xpath("//label[@for='addressProofFile']//span[contains(text(),'Browse Files')]");
    By Id_Browse_btn = By.xpath("//label[@for='idProofFile']//span[contains(text(),'Browse Files')]");
    By Stepper_two_submitBtn = By.xpath("//button[@class='mt-2 tracking-wide font-semibold bg-primary text-white w-full py-3 rounded-full transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none']");
    By Form_sumitted_message = By.xpath("//*[@class='xs:text-sm sm:text-base xl:text-xl font-normal text-black-900 text-center xs:px-10 xl:px-72 2xl:px-96 3xl:px-[500px] my-5']");
    By Image_Address_Avaliable =  By.xpath("//span[@class=' truncate max-w-[90%] text-sm font-normal text-black-900']");
    By Image1_ID_Avaliable =  By.xpath("//span[@class=' truncate max-w-[90%] text-sm font-normal text-black-900']");

    // By firstname = By.xpath("//input[@name='firstName']");

    public void getSign_up_btn (){
    }
    public void validate_signup_page(){
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
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
    public static void addressDropdown(String addressInitials) throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='address']")).sendKeys(addressInitials); // sent the address initials

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

        driver.get("https://yopmail.com/en/wm");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div")).click();
        waitForElement(yopmailemail);
        WebElement emailInput = driver.findElement(yopmailemail);
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

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
    }
    By companycategory = By.xpath("//input[@id='react-select-3-input']");
    By companyname = By.xpath("//input[@name='companyName']");
    By companyUrl = By.xpath("//input[@name='companyUrl']");
    By companyAddressField = By.xpath("//input[@name='address']");
    public void consumer_Sign_up_Step_One(String fname, String lname,String ConsumerSignUpEmail) throws InterruptedException {
        Thread.sleep(2000);
        String signbtn = driver.findElement(getSign_Up_Btn).getText();
        Assert.assertEquals(signbtn,"Sign Up");
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(firstname).sendKeys(fname);
        driver.findElement(lastname).sendKeys(lname);
        waitForElement(EmailField);

       // ConsumerSignUpEmail = properties.getProperty("SignupId");
        //adityapawarsignup@yopmail.com
        System.out.println(ConsumerSignUpEmail);
        driver.findElement(EmailField).sendKeys(ConsumerSignUpEmail);

//        driver.findElement(By.id("react-select-3-input")).sendKeys("Legal");
//        tab(companycategory);
//
//        String companynamestring = "TestCompany" + generateRandomString(3);
//        driver.findElement(companyname).sendKeys(companynamestring);
//        tab(companyname);
//
//        driver.findElement(companyUrl).sendKeys("www"+companynamestring+".com");
//        tab(companyUrl);


//        driver.findElement(companyUrl).sendKeys("Legal");
//        tab(companycategory);
//        driver.findElement(companyname);

//        driver.findElement(companyAddressField).click();
//        By location = By.xpath("//input[@name='address']");
//        googleAddress("chatra",location);
//        tab(companyAddressField);

        driver.findElement(agreementChk).click();
        driver.findElement(sentOTPbtn).click();
        Thread.sleep(5000);

        signUp_OTP_read(ConsumerSignUpEmail);

        Thread.sleep(2000);
        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(5000);
        driver.findElement(SignupVerifyEmailbtn).click();
    }


    public void consumer_Sign_up_Step_Two() throws InterruptedException, AWTException, IOException {
        waitForElement(MobileNumberField);
        driver.findElement(MobileNumberField).sendKeys(randomMobile());

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
        driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day react-datepicker__day--005 react-datepicker__day--keyboard-selected') or contains(@class, 'react-datepicker__day react-datepicker__day--005')]")).click();
        Thread.sleep(2000);

        driver.findElement(Nationality_field).click();
        Thread.sleep(1000);
        driver.findElement(Nationality_field).sendKeys("Indian");
        Thread.sleep(1000);
        tab(Nationality_field_value);

        driver.findElement(Address_field).click();
        Thread.sleep(2000);
        addressDropdown("chatra");
        Thread.sleep(1000);
       // driver.findElement(proectAddressGoogleField).click();
        tab(Address_field);

        //button[@type='submit']
        waitForElement(ID_proof);
       // driver.findElement(ID_proof).click();
        Thread.sleep(1000);
        driver.findElement(ID_proof).sendKeys("Aadhaar Card");
        Thread.sleep(500);
        tab(ID_proof);
        tab(ID_proof);


        By idBrowseBtn = By.xpath("//input[@id='idProofFile']");
        uploadFiles("/src/resources/image1.jpg", idBrowseBtn);


        Thread.sleep(1000);
        driver.findElement(Address_proof).sendKeys("Bank Passbook with Address");
        Thread.sleep(5000);
        tab(Address_proof);
        tab(Address_proof);
        By AddressBrowseBtn = By.xpath("//input[@id='idProofFile']");
        uploadFiles("/src/resources/image1.jpg", AddressBrowseBtn);
        click(Stepper_two_submitBtn);
        Thread.sleep(1000);

    }

}
