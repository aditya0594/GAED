package Pageobjects;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Projects extends TestBase {

    By ProjectBtn = By.xpath(" //ul[@class='hidden absolute top-1/2 left-20 transform -translate-y-1/2  lg:flex lg:mx-auto lg:items-center lg:w-auto lg:space-x-6'] //a[@href=\"/projects\"] ")
    By ProjectCreateNewButton = By.xpath("//button[@type='button'] //span[normalize-space()='Create New Project']");
    By CreatePageTitle = By.xpath("//div[normalize-space()='Create New Project']");
    By ProjectTitle = By.xpath("//input[@name='projectTitle']");
    By Property_location = By.xpath("//input[@name='location']");


    public void projectClick() throws InterruptedException {
        waitForElement(ProjectBtn);
        click(ProjectBtn);
    }
    public void projectCreate() throws InterruptedException {
        click(ProjectCreateNewButton);
        waitForElement(CreatePageTitle);
        String projectTile = ProjectTileText();
        driver.findElement(ProjectTitle).sendKeys(projectTile);
        driver.findElement(By.id("react-select-2-placeholder")).sendKeys("Land");
        driver.findElement(By.id("react-select-2-placeholder")).sendKeys(Keys.TAB);

    }


}
