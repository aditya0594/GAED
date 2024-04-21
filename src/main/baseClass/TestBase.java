package baseClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.CommonUtils;

public class TestBase {

    //public static AppiumDriver driver;
    //public AppiumDriverLocalService service;
    //public LandingPageAndroid LandingPage;
    public static WebDriver driver ;
    static ExtentSparkReporter spark;
    public static ExtentReports extent;

/*    @Parameters("browser")
    @BeforeSuite

        public void Setup(String browser){
            if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
            }
            else if(browser.equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();
            }
            else if (browser.equalsIgnoreCase("IE")){
                driver = new InternetExplorerDriver();
            }
            else if (browser.equalsIgnoreCase("webdrivermanager")){
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);

            }
        // appium driver
       *//* System.out.println("Setup TestCase");
        CommonUtils utils = new CommonUtils();
        utils.setup(AppConfigTags.ANDROID, AppConfigTags.MOTOROLA, Constants.ANDROID_URI);
        driver = utils.driver;*//*
    }*/
    @BeforeMethod
    public void Setup(){

        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        //Implicitly wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
      /*  WebDriverManager.chromedriver().driverVersion("121.0.6167.161").setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);*/
        //explicit wait
       /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));*/

        /*Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoAlertPresentException.class);*/

    }
    @BeforeTest
    public static void report(){

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/GEAD.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static Properties read_properties() throws IOException {


        File file = new File("src/resources/config.properties");
        Properties prop = new Properties();
        InputStreamReader is = new InputStreamReader(new FileInputStream(file));
        prop.load(is);
        return prop;
    }
    public static void main(String... args) throws IOException {
        TestBase.read_properties();
    }
//    @BeforeTest
//    public void startapp() throws IOException {
//    	pageObjectConfig();
//        System.out.println("Setup TestCase");
//
//        CommonUtils utils = new CommonUtils();
//
//        utils.setup(AppConfigTags.ANDROID, AppConfigTags.MOTOROLA, Constants.ANDROID_URI);
//        driver = utils.driver;
//    }
@AfterMethod
public void getResult(ITestResult result) throws Exception{
    ExtentTest test;
    if(result.getStatus() == ITestResult.FAILURE) {
        // Executed when a test method fails
        test = extent.createTest(result.getName() + " - Test Case Failed");
        test.log(Status.FAIL, result.getName() + " - Test Case Failed");
        test.log(Status.FAIL, result.getThrowable() + " - Test Case Failed");

        // Capture screenshot
        String screenshotPath = getScreenShot(driver, result.getName());
        test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));

    } else if(result.getStatus() == ITestResult.SKIP) {
        // Executed when a test method is skipped
        test = extent.createTest(result.getName() + " - Test Case Skipped");
        test.log(Status.SKIP, result.getName() + " - Test Case Skipped");
    } else if(result.getStatus() == ITestResult.SUCCESS) {
        // Executed when a test method passes
        test = extent.createTest(result.getName() + " - Test Case PASSED");
        test.log(Status.PASS, result.getName() + " - Test Case PASSED");
    }
    driver.quit();
}
	@AfterSuite
	public void tear() {
       driver.quit();
	}
    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(3);
        driver.quit();
    }
    public static By waitForElement(By element) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
        w.until(ExpectedConditions.presenceOfElementLocated ((By) element));
        return element;
    }


    public static void swipe(int startX, int startY,int endX,int endY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down"); // Optionally you can still provide a direction
        scrollObject.put("startX", startX);
        scrollObject.put("startY", startY);
        scrollObject.put("endX", endX);
        scrollObject.put("endY", endY);
        js.executeScript("mobile: dragGesture", scrollObject);
        // Slide_touch_mobile(531, 51, 463, 1094);

    }
    public static void swipeUp(int startX, int startY,int endX,int endY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> scrollObject = new HashMap<>();
        scrollObject.put("direction", "up"); // Optionally you can still provide a direction
        scrollObject.put("startX", startX);
        scrollObject.put("startY", startY);
        scrollObject.put("endX", endX);
        scrollObject.put("endY", endY);
        js.executeScript("mobile: dragGesture", scrollObject);
        // Slide_touch_mobile(531, 51, 463, 1094);

    }

    public static void moveTo(int startX, int startY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> swipeObject = new HashMap<>();
        swipeObject.put("action", "moveTo");
        swipeObject.put("x", startX); // ending x-coordinate
        swipeObject.put("y", startY); // ending y-coordinate
        js.executeScript("mobile: touchAction", swipeObject);
    }
    public static void release(int startX, int startY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> releaseObject = new HashMap<>();
        releaseObject.put("action", "release");
        js.executeScript("mobile: touchAction", releaseObject);
    }
    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}
 /*   public void scroll_UIautomator(){
        String scrollableList = "new UiScrollable(new UiSelector().scrollable(true))";
        String scrollToText = ".scrollIntoView(new UiSelector().text(\"Your Element Text\"))";
        String scrollToEnd = ".scrollToEnd(1)";
        String command = scrollableList + scrollToText;
        driver.findElement(AppiumBy.androidUIAutomator(command));

    }*/

/*    public static void Tap_screen (int startx, int starty)throws InterruptedException {
        TouchAction action = new  TouchAction(driver);
        action.tap(PointOption.point(startx, starty))
                .release()
                .perform();
    }*/
  /*  public static void Slide_touch (int startx, int starty, int endX, int endY ) throws InterruptedException {
        // int startx = 568;
        // int starty = 2140 ;
        TouchAction action = new  TouchAction(driver);
        action.press(PointOption.point(startx, starty))
                .waitAction()
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }*/

