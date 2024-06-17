package utils;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static baseClass.TestBase.driver;

public class utility extends TestBase {

    public static Object property(String key)throws IOException {
        FileInputStream file = new FileInputStream("src/resources/config.properties");
        Properties prop = new Properties();
        prop.load(file);
        return prop.get(key);
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


}
