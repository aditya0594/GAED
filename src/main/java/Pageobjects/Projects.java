package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.utility;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Projects extends TestBase {
    SignUp signup = new SignUp();
    By ProjectBtn = By.xpath("//ul[@class='hidden absolute top-1/2 left-20 transform -translate-y-1/2  lg:flex lg:mx-auto lg:items-center lg:w-auto lg:space-x-6']//a[normalize-space()='Projects']");
    By ProjectCreateNewButton = By.xpath("//button[@type='button'] //span[normalize-space()='Create New Project']");
    By CreatePageTitle = By.xpath("//div[normalize-space()='Create New Project']");
    By ProjectTitle = By.xpath("//input[@name='projectTitle']");
    By Property_location = By.xpath("//input[@name='location']");
    By Property_type = By.xpath("//input[@id='react-select-2-input']");

    By projectmodelcapEX = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='CapEX (Outright)1']");
    By projectmodelLeasing = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='Leasing3']");
    By projectmodelPPA = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='PPA2']");
    By ProjectImage = By.xpath("//input[@id='dropzone-file']");
    By drawtool = By.xpath("(//*[@class='w-5 h-5 rounded-full duration-200 bg-white hover:bg-[#DBE1FF]'])[3]");
    By projectname = By.xpath("(//div[@class='rounded-3xl p-4 border border-gray-300 flex flex-col'])[1]");

    By canvasArealocator = By.xpath("//div[@class='gm-style']");

    By SubmitBtn = By.xpath("//button[@type='submit']");
    By OkayBtn = By.xpath("//div[@class='absolute top-0 left-0 pb-10 flex items-center justify-end w-full h-full flex-col text-white ']//span[normalize-space()='Okay']");
    By UnderAssessmentMess = By.xpath("//div[@class='absolute top-0 left-0 pb-10 flex items-center justify-end w-full h-full flex-col text-white ']//h2[starts-with(normalize-space(), 'Great!')]");
    //By OkayBtn = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='PPA2']");
    //By OkayBtn = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='PPA2']");
    //By OkayBtn = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='PPA2']");
    //By OkayBtn = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='PPA2']");
    public void projectClick() throws InterruptedException {
        waitForElement(projectname);
        //waitForElement(ProjectBtn);
        click(ProjectBtn);
        waitForElement(ProjectCreateNewButton);
    }

    public void projectCreate() throws InterruptedException {
        Thread.sleep(3000);
        click(ProjectCreateNewButton);
        waitForElement(CreatePageTitle);
        String projectTile = ProjectTileText();
        write_excel(0,1,projectTile); // writing the project name m=in sheet


        driver.findElement(ProjectTitle).sendKeys(projectTile);
        Thread.sleep(2000);
        utility.dropdownWithText(Property_type, "Land");
        //driver.findElement(By.xpath("//input[@id='react-select-2-input']")).sendKeys("Land");
        //driver.findElement(By.xpath("//input[@id='react-select-2-input']")).sendKeys(Keys.TAB);
        signup.addressDropdown("chatra", Property_location);
        click(projectmodelcapEX);
        waitForElement(projectmodelPPA);
        click(projectmodelPPA);
        waitForElement(projectmodelLeasing);
        click(projectmodelLeasing);
        Thread.sleep(2000);
        utility.imageUpload(ProjectImage);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement drawtoolpen = driver.findElement(drawtool);
        // Scroll the canvas into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drawtoolpen);
        // Wait for and click the drawing tool (pen icon)
        WebElement drawTool = wait.until(ExpectedConditions.elementToBeClickable(drawtool)); // Replace with actual ID
        drawTool.click();

        // Wait for the canvas to be interactive
        WebElement canvasElement = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(canvasArealocator)));

        // Get the canvas size
        int canvasWidth = canvasElement.getSize().getWidth();
        int canvasHeight = canvasElement.getSize().getHeight();

// Define square size (adjusting to canvas bounds)
        int squareSize = Math.min(100, Math.min(canvasWidth, canvasHeight) - 10);

// Define the start position (ensuring it’s within bounds)
        int startX = Math.min(50, canvasWidth / 2);
        int startY = Math.min(50, canvasHeight / 2);

// Create Actions instance
        Actions action = new Actions(driver);

        // Move to starting position and click
        action.moveToElement(canvasElement, startX, startY).click().perform();
        Thread.sleep(2000);
        // Move right and click
        action.moveByOffset(squareSize, 0).click().perform();
        Thread.sleep(2000);
        // Move down and click
        action.moveByOffset(0, squareSize).click().perform();
        Thread.sleep(2000);
        // Move left and click
        action.moveByOffset(-squareSize, 0).click().perform();
        Thread.sleep(2000);
        // Move up and click (back to start)
        action.moveByOffset(0, -squareSize).click().perform();
        waitForElement(SubmitBtn);
        click(SubmitBtn);
        waitForElement(OkayBtn);
    }
    public void createdUnderAssessmentMessage(){
        String getmessage_UnderAssessment  = driver.findElement(UnderAssessmentMess).getText();
        Assert.assertEquals(getmessage_UnderAssessment,"Great! Your project has been successfully created and is now under assessment.");
        click(OkayBtn);
    }
    By MyProjectTab_Project = By.xpath("//*[@class='flex items-center justify-center text-center h-full relative bg-transparent py-1 px-2 antialiased font-sans select-none cursor-pointer top-1 w-full sm:min-w-48 text-base font-normal text-black-100 pb-3']");
    By TabProjectNames = By.xpath("//div[@class='flex items-center mb-3 justify-between']//h3[@class='font-semibold text-black-900 text-base truncate max-w-[70%]']");
    By SiteVisitProjectDate = By.xpath("//div[@class='flex flex-col']//span[@class='text-black-900 text-sm font-medium']");
    By navigationProject = By.xpath("//a[@class='text-sm 3xl:text-lg text-primary bg-white rounded-full py-2 p-4 active']");

    public void consumerSiteVisitStatusCheck() throws InterruptedException {
       // click(navigationProject);
        Thread.sleep(2000);
        waitForElement(MyProjectTab_Project);
        WebElement projectTab = driver.findElement(MyProjectTab_Project);
        try {
            Actions action = new Actions(driver);
            action.moveToElement(projectTab).pause(100).click().perform();
            System.out.println("Clicked using Actions!");
        } catch (Exception e) {
            System.out.println("Actions click failed, using JavaScript...");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", projectTab);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", projectTab);
            System.out.println("Clicked using JavaScript!");
        }

        String project_name_excel= readLastValue(0,"Project details");
        String targetProjectName = project_name_excel;
        System.out.println("Project name is :  "+ targetProjectName);//ExcelPRojectname;  // Project to find
        boolean projectFound = false;

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        while (!projectFound) {
            List<WebElement> projectNames = driver.findElements(TabProjectNames);

            for (WebElement ele : projectNames) {
                if (ele.getText().trim().equals(targetProjectName)) {
                    System.out.println("Project Found: " + targetProjectName);
                    String date= driver.findElement(SiteVisitProjectDate).getText().trim();
                    String datePattern = "\\d{2}/\\d{2}/\\d{4}"; // Example: 12/05/2025
                    // Assert that the date is available and matches the format
                    Assert.assertTrue(Pattern.matches(datePattern, date), "Date is not in the expected format!");
                    System.out.println("Extracted Date: " + date);
                    // Click on the project
                    Thread.sleep(5000);
                    projectFound = true;
                    break;
                }
            }
            if (!projectFound) {
                System.out.println("Project not found. Scrolling horizontally...");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelector('.your-scroll-container').scrollLeft += 300");
                try {
                    Thread.sleep(1000); // Wait for scrolling effect
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
