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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Pageobjects.HomePage.properties;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


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
    // By firstname = By.xpath("//input[@name='firstName']");

    public void getSign_up_btn (){
    }
    public void invalid_signup_field() {
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
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
        Assert.assertEquals("This look like an invalid email (eg: abc@xyz.com)",invalidemailMess);
        driver.findElement(firstname).sendKeys(Keys.TAB);
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

    public void consumer_Sign_up_Step_One() throws InterruptedException {
        boolean signbtn = driver.findElement(getSign_Up_Btn).isDisplayed();
        Assert.assertTrue(signbtn);
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(firstname).sendKeys(HomePage.FIRSTNAME);
        driver.findElement(lastname).sendKeys(HomePage.LASTNAME);

        ConsumerSignUpEmail = properties.getProperty("SignupId");


        System.out.println(ConsumerSignUpEmail);
        driver.findElement(EmailField).sendKeys(ConsumerSignUpEmail);
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
    By MobileNumberField = By.xpath("//input[@value='+65']");
    By CalandorClick = By.xpath("//button[@type='button']");
    By Nationality_field = By.xpath("//*[@class=' css-19bb58m']");
    By Nationality_field_value = By.id("react-select-2-input");
    By Address_field = By.xpath("//input[@name='address']");
    By proectAddressGoogleField =  By.xpath("//body/div[@id='root']/div[@class='App']/div/div[@class='h-full']/div[@class='mx-auto']/div[@class='flex justify-center']/div[@class='w-full flex']/div[1]");
    By ID_proof = By.xpath("//*[@id=\"react-select-3-input\"]");
    By ID_proof_value = By.id("react-select-31-input");
    By Address_proof = By.xpath("//*[@id='react-select-4-input']");
    By Address_proof_value = By.id("react-select-32-input");
    public void consumer_Sign_up_Step_Two() throws InterruptedException, AWTException {
        waitForElement(MobileNumberField);
        driver.findElement(MobileNumberField).sendKeys(randomMobile());

        //Calendar
        By Date_of_birth = By.xpath("//input[@placeholder='dd/mm/yyyy']");
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

        driver.findElement(Nationality_field).click();
        Thread.sleep(1000);
        driver.findElement(Nationality_field_value).sendKeys("Indian");
        Thread.sleep(1000);
        tab(Nationality_field_value);

        driver.findElement(Address_field).click();
        Thread.sleep(2000);
        driver.findElement(Address_field).sendKeys("MIHAN, Nagpur, Nagpur, Maharashtra, India");
        Thread.sleep(1000);
        driver.findElement(proectAddressGoogleField).click();
        tab(Address_field);

        //button[@type='submit']
        waitForElement(ID_proof);
       // driver.findElement(ID_proof).click();
        Thread.sleep(1000);
        driver.findElement(ID_proof).sendKeys("Aadhaar Card");
        Thread.sleep(1000);
        tab(ID_proof);
        tab(ID_proof);

        Thread.sleep(1000);
        driver.findElement(Address_proof).sendKeys("Bank Passbook with Address");
        Thread.sleep(1000);
        tab(Address_proof);
        tab(Address_proof);

        By Address_Browse_btn = By.xpath("//label[@for='addressProofFile']//span[contains(text(),'Browse Files')]");
        By Id_Browse_btn = By.xpath("//label[@for='idProofFile']//span[contains(text(),'Browse Files')]");
        By Stepper_two_submitBtn = By.xpath("//button[@class='mt-2 tracking-wide font-semibold bg-primary text-white w-full py-3 rounded-full transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none']");
        By Form_sumitted_message = By.xpath("//*[@class='xs:text-sm sm:text-base xl:text-xl font-normal text-black-900 text-center xs:px-10 xl:px-72 2xl:px-96 3xl:px-[500px] my-5']");
        By Image_Address_Avaliable =  By.xpath("//span[@class=' truncate max-w-[90%] text-sm font-normal text-black-900']");
        By Image1_ID_Avaliable =  By.xpath("//span[@class=' truncate max-w-[90%] text-sm font-normal text-black-900']");
        Thread.sleep(1000);


        Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
        // Create a StringSelection object containing the OTP

        StringSelection stringSelection = new StringSelection("C:\\GAED\\src\\resources\\image1.jpg");
        // Set the StringSelection as the current contents of the clipboard
        clipboard1.setContents(stringSelection,null);

        click(Id_Browse_btn);

        Robot robot = new Robot();
        // Press the CTRL key
        robot.keyPress(KeyEvent.VK_CONTROL);
        // Press the V key
        robot.keyPress(KeyEvent.VK_V);
        // Release the V key
        robot.keyRelease(KeyEvent.VK_V);
        // Release the CTRL key
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);


        Thread.sleep(2000);

       Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
        // Create a StringSelection object containing the OTP
       // String image2 = "C:\\GAED\\src\\resources\\image1.jpg";
      StringSelection stringSelection1 = new StringSelection("C:\\GAED\\src\\resources\\image1.jpg");
        // Set the StringSelection as the current contents of the clipboard
        clipboard2.setContents(stringSelection1,null);
        Thread.sleep(1000);

        click(Address_Browse_btn);

        Robot robot1 = new Robot();
        // Press the CTRL key
        robot1.keyPress(KeyEvent.VK_CONTROL);
        // Press the V key
        robot1.keyPress(KeyEvent.VK_V);
        // Release the V key
        robot1.keyRelease(KeyEvent.VK_V);
        // Release the CTRL key
        robot1.keyRelease(KeyEvent.VK_CONTROL);
        robot1.keyPress(KeyEvent.VK_ENTER);

        Thread.sleep(5000);
        tab(Address_proof);
       // moveToElement(Stepper_two_submitBtn);
        //waitForElement(Stepper_two_submitBtn);

        click(Stepper_two_submitBtn);


        Thread.sleep(15000);


   /*     waitForElement(Form_sumitted_message);
        String ActualFormText = driver.findElement(Form_sumitted_message).getText();
        String expectedString = "Thank you for submitting your KYC details! Your information has been received and is currently under review by the GAED Team. You will be able to access the platform once your KYC is approved. We appreciate your patience and cooperation. If you have any questions or need further assistance, please feel free to contact our support team via\"enquiries@gaed2.com\". Thank you for choosing GAED!";
        Assert.assertEquals(ActualFormText,expectedString);

        Thread.sleep(5000);*/
    }

}
