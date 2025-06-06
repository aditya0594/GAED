    package baseClass;
    
    import java.awt.*;
    import java.io.*;
    import java.net.URISyntaxException;
    import java.net.URL;
    import java.text.SimpleDateFormat;
    import java.time.Duration;
    import java.util.*;
    
    import ExtentReportListener.MyITestListener;
    import com.aventstack.extentreports.ExtentReports;
    import com.aventstack.extentreports.ExtentTest;
    import com.aventstack.extentreports.Status;
    import com.aventstack.extentreports.reporter.ExtentSparkReporter;
    import io.github.bonigarcia.wdm.WebDriverManager;
    import org.apache.commons.io.FileUtils;
    import org.apache.commons.lang3.RandomStringUtils;
    import org.apache.poi.ss.usermodel.*;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    import org.openqa.selenium.*;
    import org.openqa.selenium.NoSuchElementException;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.openqa.selenium.interactions.Actions;
    import org.openqa.selenium.remote.DesiredCapabilities;
    import org.openqa.selenium.remote.RemoteWebDriver;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.FluentWait;
    import org.openqa.selenium.support.ui.Wait;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import org.testng.ITestResult;
    import org.testng.annotations.*;
    import utils.utility;
    
    
    @Listeners(MyITestListener.class)
    public class TestBase {
    
        //public static AppiumDriver driver;
        //public AppiumDriverLocalService service;
        //public LandingPageAndroid LandingPage;
        protected static WebDriver driver;
        public static ExtentReports extent;
        public static ExtentTest test;
    
        protected TestBase() {
            // Private constructor to prevent instantiation
        }
        @Parameters("browserName")
        @BeforeMethod
        public static WebDriver Setup() throws IOException, URISyntaxException {
            String browserName = utility.property("browserName").toString();
            System.out.println("The browser name is : " + browserName);
            ChromeOptions options = new ChromeOptions(); // Ensure options is initialized

            if (driver == null) {
                String seleniumHubUrl = "http://selenium-hub:4444/wd/hub";

                if (browserName.equalsIgnoreCase("chromeRemote")) {
                    options.addArguments("--remote-allow-origins=*");
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new RemoteWebDriver(new URL(seleniumHubUrl), capabilities);
                } else if (browserName.equalsIgnoreCase("chrome")) {
                    // Check if headless mode should be enabled
                    if (utility.property("headless").equals("true")) {
                        options.addArguments("--start-maximized");
                        options.addArguments("--headless");  // Run in headless mode
                        options.addArguments("--window-size=1920,1080");  // Set screen resolution
                        options.addArguments("--force-device-scale-factor=1.25"); // Set scaling to 125%
                        options.addArguments("--high-dpi-support=1.25"); // Improve rendering
                        options.addArguments("--disable-gpu");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--no-sandbox");
                        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.5481.178 Safari/537.36");
                        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                        options.setExperimentalOption("useAutomationExtension", false);
                    }
                    WebDriverManager.chromedriver().setup();
                    //options.addArguments("--headless","--window-size=1920x1080");
                    driver = new ChromeDriver(options);

                } else {
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
                }
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            return driver;
        }
      @BeforeMethod
      public void createTestReport(ITestResult result) {
          test = extent.createTest(result.getMethod().getMethodName());
          test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
      }
    
       @BeforeTest
        public void report(){
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/GAED_Testcase_report.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
    
        public void moveToElement(By locator){
            JavascriptExecutor js = (JavascriptExecutor) driver;
    
            // Find the element
            WebElement element = driver.findElement(locator);
    
            // Scroll to the element on the page
            js.executeScript("arguments[0].scrollIntoView();", element);
        }
        @AfterMethod
        public void getResult(ITestResult result) throws Exception {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.log(Status.FAIL, "Test Case Failed: " + result.getName());
                test.log(Status.FAIL, "Reason: " + result.getThrowable());
    
                String screenshotPath = getScreenShot(driver, result.getName());
                test.fail("Screenshot of Failure").addScreenCaptureFromPath("Screenshots");
            }
            else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test Case Passed: " + result.getName());
            }
            else if (result.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
            }
    
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    
        public String getTitle() {
            return driver.getTitle();
        }
    
        public static Properties read_properties() throws IOException {
            File file = new File("src/resources/config.properties");
            Properties prop = new Properties();
            InputStreamReader is = new InputStreamReader(new FileInputStream(file));
            prop.load(is);
            return prop;
        }
    
    
        /*public void getResult(ITestResult result) throws Exception{
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
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    */
    
        @AfterSuite
        public void tear(){
            extent.flush();
        }
    
      /*  @AfterTest
        public void endReport() {
            extent.flush();
        }*/
    
        public static By waitForElement(By element) {
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(40));
            w.until(ExpectedConditions.visibilityOfElementLocated((By) element));
            return element;
        }
        public void fluentWait(By locator) {
            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .ignoring(ElementNotInteractableException.class);

            WebElement element = fluentWait.until(driver -> driver.findElement(locator));
            // or any action you want to perform
        }
        public static void waitForLoaderToDisappear(By loaderLocator) {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        }
        public static By waitForElementToBeClickable(By element) {
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
            w.until(ExpectedConditions.elementToBeClickable((By) element));
            return element;
        }
    
    
        public static String randomEmail() {
            String allowedChars = "123456789";
            String nameChars = "abcdefg";
            String email = "adity" + RandomStringUtils.random(2, nameChars) + RandomStringUtils.random(3, allowedChars) + "@yopmail.com";
            return email;
        }
        public static String ProjectTileText() {
            String nameChars = "ABCDEFGHI";
            String Text = "Solar Project " + RandomStringUtils.random(3, nameChars);
            return Text;
        }
    
    
        public static String randomMobile() {
            String allowedChars = "1234567889";
            String MobileNumber = RandomStringUtils.random(8, allowedChars);
            return MobileNumber;
        }
    
        public static int getLastRowNumInColumn(Sheet sheet, int columnIndex) {
            int lastRowNum = sheet.getLastRowNum();
            for (int i = lastRowNum; i >= 0; i--) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null && !cell.getStringCellValue().isEmpty()) {
                        return i;
                    }
                }
            }
            return -1; // If no data found in the column
        }
    
        public static void write_excel(Integer Row,int Sheet,String saveString) {
            String filePath = "src/resources/Consumer_signup_ids.xlsx";
            try (FileInputStream fis = new FileInputStream(filePath);
                 Workbook workbook = fis.available() > 0 ? WorkbookFactory.create(fis) : new XSSFWorkbook()) {
                Sheet sheet = workbook.getSheetAt(Sheet);
                int lastRowNum = getLastRowNumInColumn(sheet, Row);
                // Add data to the next row
                Row row = sheet.createRow(lastRowNum + 1);
                Cell cell = row.createCell(Row);
                cell.setCellValue(saveString);
                // Write to file
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    workbook.write(fos);
                }
                System.out.println("Random Email written to Excel file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String readLastValue(int columnIndex, String SheetName) {
            String filePath = "src/resources/Consumer_signup_ids.xlsx";
            String lastValue = "";

            try (FileInputStream fis = new FileInputStream(new File(filePath));
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheet(SheetName);
                int lastRowNum = getLastRowNumInColumn(sheet, columnIndex);

                if (lastRowNum != -1) {
                    Row row = sheet.getRow(lastRowNum);
                    Cell cell = row.getCell(columnIndex);

                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                lastValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                lastValue = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                lastValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            default:
                                lastValue = "Unknown cell type";
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lastValue;
        }
        public static int getLastRowNumber(String SheetName, int columnIndex) {
            String filePath = "src/resources/Consumer_signup_ids.xlsx";
            int lastRowNum = -1;

            try (FileInputStream fis = new FileInputStream(new File(filePath));
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheet(SheetName);
                lastRowNum = getLastRowNumInColumn(sheet, columnIndex);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return lastRowNum; // Returns the last row number in the column
        }
        public static void tab(By element){
            driver.findElement(element).sendKeys(Keys.TAB);
        }
        public static void click(By element){
            driver.findElement(element).click();
        }
    
    
        public String getTextVerify(By element) {
            String actualText = driver.findElement(element).getText();
            return actualText;
        }
        public static void moveMouseAround(WebDriver driver, WebElement element) {
            // Get current location of the element
            org.openqa.selenium.Point elementLocation = element.getLocation();
    
            // Get current location of the mouse pointer
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            java.awt.Point mouseLocation = pointerInfo.getLocation();
    
            // Calculate the distance between the element and the mouse pointer
            int dx = (int) (elementLocation.getX() - mouseLocation.getX());
            int dy = (int) (elementLocation.getY() - mouseLocation.getY());
    
            // Calculate the number of steps for the mouse to move
            int steps = 20; // You can adjust the number of steps for smoother or more abrupt movement
    
            // Calculate the step sizes for x and y directions
            double stepX = (double) dx / steps;
            double stepY = (double) dy / steps;
    
            // Create Actions object
            Actions actions = new Actions(driver);
    
            // Move the mouse to the element's location
            actions.moveToElement(element).perform();
    
            // Simulate human-like movement by moving the mouse pointer in steps
            Random random = new Random();
            for (int i = 0; i < steps; i++) {
                // Calculate the next position of the mouse pointer
                int nextX = (int) (mouseLocation.getX() + stepX + random.nextGaussian() * 2); // Add some randomness
                int nextY = (int) (mouseLocation.getY() + stepY + random.nextGaussian() * 2); // Add some randomness
    
                // Move the mouse to the next position
                try {
                    Robot robot = new Robot();
                    robot.mouseMove(nextX, nextY);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
    
                // Pause for a short time to simulate human-like movement
                try {
                    Thread.sleep(30 + random.nextInt(50)); // Randomize pause duration between steps
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                // Update the current mouse location
                mouseLocation.setLocation(nextX, nextY);
            }
    
            // Finally, move the mouse back to the element to ensure it stays focused
            actions.moveToElement(element).perform();
        }
        public static void typeLikeAHuman(WebElement element, String text) {
            // Iterate through each character in the text
            for (int i = 0; i < text.length(); i++) {
                // Get the current character
                char c = text.charAt(i);
    
                // Send the current character to the element
    
                element.sendKeys(String.valueOf(c));
    
                // Add a random delay between keystrokes to simulate human typing speed
                try {
                    // Adjust the delay range as needed
                    Thread.sleep(50 + (int) (Math.random() * 50)); // Random delay between 50 to 100 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    
        public static void swipe(int startX, int startY, int endX, int endY) {
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
    
        public static void swipeUp(int startX, int startY, int endX, int endY) {
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
    
        public void scroll_UIautomator() {
            String scrollableList = "new UiScrollable(new UiSelector().scrollable(true))";
            String scrollToText = ".scrollIntoView(new UiSelector().text(\"Your Element Text\"))";
            String scrollToEnd = ".scrollToEnd(1)";
            String command = scrollableList + scrollToText;
            //driver.findElement(AppiumBy.androidUIAutomator(command));
    
        }
    }
    
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
    
