package Pageobjects;

import baseClass.TestBase;
import freemarker.template.utility.DateUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BuyandSellBid extends TestBase {
    LoginConsumer LoginUser = new LoginConsumer();
    SignUp signup = new SignUp();

    By projectCard = By.xpath("//div[@class='rounded-3xl p-4 border border-gray-300 flex flex-col']");
    By projectsingle = By.xpath("//div[@class='rounded-3xl p-4 border border-gray-300 flex flex-col']/div/div/h3");
    By StartBidding = By.xpath("//button[@class='w-full px-8 py-2 rounded-full bg-white border border-primary hover:bg-primary text-primary hover:text-white text-base cursor-pointer']");
    By verifyProjectNameInDetails = By.xpath("//h3[@class='font-medium xs:text-xl sm:text-3xl text-black-900']");
    By bidNowBtn = By.xpath("//div[@class='xs:hidden sm:flex xs:my-2 sm:my-10 flex justify-end space-x-3']//button[@type='submit']");
    By bidvalueInput = By.xpath("//input[@name='bidValue']");
    By makeOrderBtn = By.xpath("//button[normalize-space()='Make Offer']");
    By OkayBtn = By.xpath("//button[normalize-space()='Okay']");
    By MyProjectTab = By.xpath("//div[normalize-space()='My Projects']");
    By TabProjectNames = By.xpath("//div[@class='flex items-center mb-3 justify-between']//h3[@class='font-semibold text-black-900 text-base truncate max-w-[70%]']");
    By ViewProposalsbtn = By.xpath("//button[@type='button']//span[normalize-space()='View Proposals']");
    By AccpectBtn = By.xpath("//button[@type='button']//span[normalize-space()='Accept']");
    By RejectBtn = By.xpath("//button[@type='button']//span[normalize-space()='Reject']");
    By DoneButton = By.xpath("//div[@class='flex flex-col space-y-3 w-[400px] m-auto pt-5']//button[normalize-space()='Done']");
    //    By makeOrderBtn = By.xpath("//button[normalize-space()='Make Offer']");
    //    By makeOrderBtn = By.xpath("//button[normalize-space()='Make Offer']");
    //    By makeOrderBtn = By.xpath("//button[normalize-space()='Make Offer']");
    By homeloader = By.xpath("//*[@class='inline w-14 h-14 text-gray-200 animate-spin dark:text-gray-600 fill-primary']");
    String ExcelPRojectname;

    public void ProjectToBid() throws InterruptedException {
        Thread.sleep(5000);
        ExcelPRojectname = readLastValue(0, "Project details");

        System.out.println("This is the project name : " + ExcelPRojectname);
        List<WebElement> projectNames = driver.findElements(By.xpath("//div[@class='rounded-3xl p-4 border border-gray-300 flex flex-col']/div/div/h3"));
        System.out.println(projectNames.size());
        boolean flag = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (WebElement ele : projectNames) {
            String projectSingleName = ele.getText();
            if (projectSingleName.equals(ExcelPRojectname)) {
                System.out.println("Got the name : " + projectSingleName);

                waitForLoaderToDisappear(homeloader);
                driver.findElement(StartBidding).click();
                waitForLoaderToDisappear(homeloader);
                String name = driver.findElement(verifyProjectNameInDetails).getText();
                Assert.assertEquals(name, ExcelPRojectname);

                List<WebElement> bidButton = driver.findElements(bidNowBtn);
                System.out.println("The bid button is size :" + bidButton);
                try {
                    WebElement bidNow = wait.until(ExpectedConditions.presenceOfElementLocated(bidNowBtn));
                    click(bidNowBtn);
                    driver.findElement(bidvalueInput).sendKeys("15000");
                    click(makeOrderBtn);
                    click(OkayBtn);

                } catch (TimeoutException e) {
                    System.out.println("Bid Now button not found within timeout. Navigating back.");
                    driver.navigate().back();
                }
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Project not found: " + ExcelPRojectname);
        }


    }

    public void consumerMyProjectBuyAndSell() throws InterruptedException {
        Thread.sleep(2000);
        waitForLoaderToDisappear(homeloader);
        waitForElement(MyProjectTab);
        click(MyProjectTab);
        String targetProjectName = readLastValue(0, "Project details");;
        System.out.println("Project name is :  "+ targetProjectName);//ExcelPRojectname;  // Project to find
        boolean projectFound = false;

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        while (!projectFound) {
            List<WebElement> projectNames = driver.findElements(TabProjectNames);

            for (WebElement ele : projectNames) {
                if (ele.getText().trim().equals(targetProjectName)) {
                    System.out.println("Project Found: " + targetProjectName);
                    ele.click(); // Click on the project
                    Thread.sleep(5000);
                    projectFound = true;
                    break;
                }
            }
//            if (!projectFound) {
//                System.out.println("Project not found. Scrolling horizontally...");
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                js.executeScript("document.querySelector('.your-scroll-container').scrollLeft += 300");
//                try {
//                    Thread.sleep(1000); // Wait for scrolling effect
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        }
    }

    public void viewProposal() throws InterruptedException {
        waitForElement(ViewProposalsbtn);
        click(ViewProposalsbtn);
        waitForElement(AccpectBtn);
        click(AccpectBtn);
        click(DoneButton);

    }

}
