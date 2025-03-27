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


}
