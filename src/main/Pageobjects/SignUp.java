package Pageobjects;

import baseClass.TestBase;
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends TestBase {
    By getSign_Up_Btn= By.xpath("//span[@class='cursor-pointer hidden lg:inline-block py-2 px-6 text-sm text-white border border-white font-medium rounded-full transition duration-200 group-hover:text-primary group-hover:border-primary']");
    By signup_page_title = By.xpath("//h3[@class='py-2 xl:text-3xl text-black-900 font-semibold']");
    By firstname = By.xpath("//input[@name='firstName']");
    By lastname = By.xpath("//input[@name='lastName']");
    By email = By.xpath("//input[@name='email']");
    By agreementChk = By.xpath("//input[@name='tncCheck']");
    By sentOTPbtn = By.xpath("//button[@type='submit']");
    By invalidFistnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[1]/div/span");
    By invalidLastnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[2]/div/span");
    By invalidEmailMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[2]/div/div/span");
    By EmpFistnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[1]/div/span");
    By EmpLastnameMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[1]/div[2]/div/span");
    By EmpEmailMesss = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/form/div[2]/div/div/span");
    static By yopmailemail = By.xpath("//input[@id='login']");
    static By yopmailarrow = By.xpath("//i[@class='material-icons-outlined f36']");
    // By firstname = By.xpath("//input[@name='firstName']");

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
    By firstOtpBlock = By.xpath("//input[@aria-label='Please enter OTP character 1']");

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
    public static void signUp_OTP_read() throws InterruptedException {

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
        typeLikeAHuman(emailInput, CreatRandomEmailyopmail());
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

       driver.close();
    }

    public void consumer_Sign_up_Step_One() throws InterruptedException {
        boolean signbtn = driver.findElement(getSign_Up_Btn).isDisplayed();
        Assert.assertTrue(signbtn);
        CreatRandomEmailyopmail();
        driver.findElement(getSign_Up_Btn).click();
        String Getsignuptitle = driver.findElement(signup_page_title).getText();
        Assert.assertEquals("Let's get you an Account",Getsignuptitle);
        driver.findElement(firstname).sendKeys(HomePage.FIRSTNAME);
        driver.findElement(lastname).sendKeys(HomePage.LASTNAME);

        String ConsumerEmailFromYopmail = CreatRandomEmailyopmail();

        System.out.println(ConsumerEmailFromYopmail);
        write_excel(1,0,randomEmail());
        driver.findElement(EmailField).sendKeys(ConsumerEmailFromYopmail);
        driver.findElement(agreementChk).click();

        signUp_OTP_read();

        driver.findElement(firstOtpBlock).click();
        Actions actions = new Actions(driver);
        // Use Actions class to perform keyboard shortcut (Ctrl + V) for paste
        Thread.sleep(2000);
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(10000);
        driver.findElement(SignupVerifyEmailbtn).click();

    }
    By MobileNumberField = By.xpath("//input[@value='+65']");
    By CalandorClick = By.xpath("//button[@type='button']");

    public void consumer_Sign_up_Step_Two() throws InterruptedException {
        waitForElement(MobileNumberField);
        driver.findElement(MobileNumberField).sendKeys(randomMobile());
        driver.findElement(CalandorClick).click();
        WebElement yearSelect = driver.findElement(By.xpath("//div[@class = \"react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select\"]"));
        Select yearDropdown = new Select(yearSelect);
        yearDropdown.selectByValue("3");
        Thread.sleep(12000);// Replace with the desired year
    }

}
