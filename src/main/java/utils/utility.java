package utils;


import baseClass.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class utility extends TestBase {

    public static void imageUpload(By xpath) throws InterruptedException {
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
//        click(image_upload);
//        WebElement IDfileInput = driver.findElement(By.xpath("//input[@id='document1File']"));
//        IDfileInput.sendKeys(FinalPAth);
        for(int i=1; i<=4;i++){
            WebElement project_image = driver.findElement(xpath);
            project_image.sendKeys(FinalPAth);
        }
        Thread.sleep(2000);
    }

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
    public static void scrollToElement(By element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }
    public static void dropdownWithText(By xpathOfDropdown, String value){
        driver.findElement(xpathOfDropdown).sendKeys(value);
        driver.findElement(xpathOfDropdown).sendKeys(Keys.TAB);
    }
    public static void dropdown(By field_click, By textget, String TextToBeSelected ) throws InterruptedException {
        driver.findElement(field_click).click();
        Thread.sleep(1000);
        List<WebElement> nationality = driver.findElements(textget);
        for(WebElement element : nationality){
            String text = element.getText();
            System.out.println("The nationality list : " + text );
            if(text.equals(TextToBeSelected)){
                driver.findElement(textget).click();
            }
            break;
        }
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



        public static String randomEmailSignup() {
            String randomEmail = "aditya" + UUID.randomUUID().toString().substring(0, 8) + "@yopmail.com";
            System.out.println("Generated Email: " + randomEmail);
            return randomEmail;
        }
      public static void click(By element) {
      driver.findElement(element).click();
        }
    }


    /*<plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version> <!-- Or the latest version -->
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng_old.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <parallel>tests</parallel>
                    <threadCount>3</threadCount> <!-- Number of threads for parallel execution -->
                </configuration>
            </plugin>
        </plugins>*/


