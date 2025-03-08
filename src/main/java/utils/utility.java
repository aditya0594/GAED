package utils;

import baseClass.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class utility extends TestBase {


    public static Object property(String key)throws IOException {
        FileInputStream file = new FileInputStream("src/resources/config.properties");
        Properties prop = new Properties();
        prop.load(file);
        return prop.get(key);
    }
    public static void click_javascript(By element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
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
    public static void uploadFiles(String location, By locator){
        String path = System.getProperty("user.dir");
        String relativePath = path+location;
        // Convert to Path object
        Path convertedpath = Paths.get(relativePath);
        String FinalPAth = convertedpath.toString();


        WebElement IDfileInput = driver.findElement(locator);
        IDfileInput.sendKeys(FinalPAth);
    }
    public static void googleAddress(String addressInitials,By locator) throws InterruptedException {
        driver.findElement(locator).sendKeys(addressInitials); // sent the address initials

        List<WebElement> suggestAddress = driver.findElements(By.xpath("//li[@class='text-sm font-normal text-black-900 px-3 py-2 cursor-pointer hover:bg-[#f6f6f6]']"));
        //driver.findElements(By.xpath("//ul[@class='mt-2 border border-gray-300 shadow-md rounded-x1 rounded-bl-xl rounded-br-xl py-1 absolute mx-[2%] left-e top-full w-[96%] z-50 bg-white']/li"));

        System.out.println("Number of ul elements found: " + suggestAddress.size());

        // Example: Interact with the first ul element
        WebElement firstUl = suggestAddress.get(0);
        firstUl.click();
        Thread.sleep(1000);
    }


    public static void AssertTextBtn(By element,String text){
        String btn = driver.findElement(element).getText();
        System.out.println("button text is : " + btn);
        Assert.assertEquals(btn,text);
    }
    public static By waitForElement(By element) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
        w.until(ExpectedConditions.visibilityOfElementLocated((By) element));
        return element;
    }
    @DataProvider(name = "ValidLoginEmail")
    public static Object[][] LoginData() throws IOException {

        FileInputStream fis = new FileInputStream("src/resources/ExcelFile.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet loginsheet = workbook.getSheet("ValidLoginsheet");

        int numberOfRows = loginsheet.getPhysicalNumberOfRows();
        Object[][] testData = new Object[numberOfRows - 1][2]; // Adjust for header row if present

        for (int i = 1; i < numberOfRows; i++) { // Start from 1 to skip header row
            Row row = loginsheet.getRow(i);

            if (row != null) {
                Cell usernameEmail = row.getCell(0);
                Cell password = row.getCell(1);

                if (usernameEmail != null) {
                    testData[i - 1][0] = getCellValue(usernameEmail);
                } else {
                    testData[i - 1][0] = ""; // Handle null cell
                }

                if (password != null) {
                    testData[i - 1][1] = getCellValue(password);
                } else {
                    testData[i - 1][1] = ""; // Handle null cell
                }

                System.out.println("Username fetched value: " + usernameEmail);
                System.out.println("Password fetched value: " + password);
            }
        }

        workbook.close();
        fis.close();

        return testData;
    }
    @DataProvider(name = "InvalidLoginEmail")
    public static Object[][] InvalidLoginEmail() throws IOException {

        FileInputStream fis = new FileInputStream("src/resources/ExcelFile.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet loginsheet = workbook.getSheet("InvalidLoginEmail");

        int numberOfRows = loginsheet.getPhysicalNumberOfRows();
        Object[][] testData = new Object[numberOfRows - 1][2]; // Adjust for header row if present

        for (int i = 1; i < numberOfRows; i++) { // Start from 1 to skip header row
            Row row = loginsheet.getRow(i);

            if (row != null) {
                Cell usernameEmail = row.getCell(0);
                Cell password = row.getCell(1);

                if (usernameEmail != null) {
                    testData[i - 1][0] = getCellValue(usernameEmail);
                } else {
                    testData[i - 1][0] = ""; // Handle null cell
                }

                if (password != null) {
                    testData[i - 1][1] = getCellValue(password);
                } else {
                    testData[i - 1][1] = ""; // Handle null cell
                }

                System.out.println("Username fetched value: " + usernameEmail);
                System.out.println("Password fetched value: " + password);
            }
        }

        workbook.close();
        fis.close();

        return testData;
    }
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    /*<plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version> <!-- Or the latest version -->
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <parallel>tests</parallel>
                    <threadCount>3</threadCount> <!-- Number of threads for parallel execution -->
                </configuration>
            </plugin>
        </plugins>*/

}
