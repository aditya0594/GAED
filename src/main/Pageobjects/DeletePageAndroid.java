package androidpageobjectss;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DeletePageAndroid extends TestBase {

    //delete account locators
    By Delete_account_btn = By.id("com.hp.impulse.sprocket:id/img_delete");
    By Cancel_account_bt = By.id("com.hp.impulse.sprocket:id/bt_cancel");
    By Confirm_btn = By.id("com.hp.impulse.sprocket:id/bt_confirm_delete");
    //com.hp.impulse.sprocket:id/tv_detail
    By Content_delete_pop = By.id("com.hp.impulse.sprocket:id/tv_detail");
    By Account_deleted_ok_btn = By.id("com.hp.impulse.sprocket:id/bt_ok");
    By Account_deleted= By.id("com.hp.impulse.sprocket:id/tv_alert");

    public void delete_account() throws InterruptedException {
        waitForElement(Delete_account_btn);
        driver.findElement(Delete_account_btn).click();
        driver.findElement(Cancel_account_bt).click();
        driver.findElement(Delete_account_btn).click();
        driver.findElement(Confirm_btn).click();
        Thread.sleep(5000);
        // waitForElement(Account_deleted_ok_btn);
        // Thread.sleep(5000);
        // Assert.assertTrue((driver.findElement(Account_deleted_ok_btn)).isEnabled(), "Popup is visible.");
        //driver.findElement(Account_deleted_ok_btn).click();
        //WebDriverWait w = new WebDriverWait(driver,3);
        // presenceOfElementLocated condition
        //  w.until(ExpectedConditions.presenceOfElementLocated (Account_deleted_ok_btn));
        // driver.findElement(Account_deleted_ok_btn).click();


    }
    public void delete_account_ok_popup() throws InterruptedException {
        Thread.sleep(5000);
        waitForElement(Account_deleted_ok_btn);
        Thread.sleep(5000);
        Assert.assertTrue((driver.findElement(Account_deleted_ok_btn)).isEnabled(), "Popup is visible.");
        driver.findElement(Account_deleted_ok_btn).click();
    }

}
