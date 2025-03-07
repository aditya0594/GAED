package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.utility;

public class Projects extends TestBase {
    SignUp signup = new SignUp();
    By ProjectBtn = By.xpath("//ul[@class='hidden absolute top-1/2 left-20 transform -translate-y-1/2  lg:flex lg:mx-auto lg:items-center lg:w-auto lg:space-x-6'] //a[@href=\"/projects\"]");
    By ProjectCreateNewButton = By.xpath("//button[@type='button'] //span[normalize-space()='Create New Project']");
    By CreatePageTitle = By.xpath("//div[normalize-space()='Create New Project']");
    By ProjectTitle = By.xpath("//input[@name='projectTitle']");
    By Property_location = By.xpath("//input[@name='location']");
    By Property_type = By.xpath("//input[@id='react-select-2-input']");



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

    }


}
