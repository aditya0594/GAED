package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.utility;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Projects extends TestBase {
    SignUp signup = new SignUp();
    By ProjectBtn = By.xpath("//ul[@class='hidden absolute top-1/2 left-20 transform -translate-y-1/2  lg:flex lg:mx-auto lg:items-center lg:w-auto lg:space-x-6'] //a[@href=\"/projects\"]");
    By ProjectCreateNewButton = By.xpath("//button[@type='button'] //span[normalize-space()='Create New Project']");
    By CreatePageTitle = By.xpath("//div[normalize-space()='Create New Project']");
    By ProjectTitle = By.xpath("//input[@name='projectTitle']");
    By Property_location = By.xpath("//input[@name='location']");
    By Property_type = By.xpath("//input[@id='react-select-2-input']");

    By projectmodelcapEX = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='CapEX (Outright)1']");
    By projectmodelLeasing = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='Leasing3']");
    By projectmodelPPA = By.xpath("//div[@class='flex space-x-2 items-center']/input[@id='PPA2']");


    public void projectClick() throws InterruptedException {
        waitForElement(ProjectBtn);
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
        utility.dropdownWithText(Property_type,"Land");
        //driver.findElement(By.xpath("//input[@id='react-select-2-input']")).sendKeys("Land");
        //driver.findElement(By.xpath("//input[@id='react-select-2-input']")).sendKeys(Keys.TAB);
        signup.addressDropdown("chatra",Property_location);
        click(projectmodelcapEX);
        click(projectmodelPPA);
        click(projectmodelLeasing);
        Thread.sleep(2000);
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
            WebElement project_image = driver.findElement(By.xpath("//input[@id='dropzone-file']"));
            project_image.sendKeys(FinalPAth);
        }
        Thread.sleep(2000);






    }


}
