package Pageobjects;
import baseClass.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;

public class GalleryPageAndroid extends TestBase {

    static By Gallery_each_image = By.id("Gallery_each_image");
    static By AlbumContainer = By.id("com.hp.impulse.sprocket:id/ll_album_container");
    static By AllPhotos_click = By.id("com.hp.impulse.sprocket:id/album_name");
    static By Allphotos1 = By.xpath("//android.widget.FrameLayout[1]/android.widget.ImageView");
    static By Allphotos2 = By.xpath("//android.widget.FrameLayout[2]/android.widget.ImageView");
    static By Allphotos3 = By.xpath("//android.widget.FrameLayout[3]/android.widget.ImageView");
    static By Select_func = By.id("com.hp.impulse.sprocket:id/action_select_images");
    static By imageSelCount = By.xpath("//android.widget.FrameLayout[3]/android.widget.TextView");
    static  By Clear_btn = By.id("com.hp.impulse.sprocket:id/action_select_images");
    static By Collage_btn = By.xpath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView");
    static By Collage_image_ver = By.xpath("//android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView");
    static By popup_ok = By.id("com.hp.impulse.sprocket:id/button_positive");
    static By Swipe_to_insta= By.xpath("//android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[14]/android.widget.ImageView[1]");
    static By Next_for_print_preview= By.xpath("//*[@id=\"screenshotContainer\"]/div/div/div/div/div/div[96]");
    static By Collage_layout_1 = By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.ImageView");
    static By Collage_layout_2 = By.xpath("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ImageView");
    static By Collage_layout_3 = By.xpath("//android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.ImageView");
    static By Collage_layout_4 = By.xpath("//android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.ImageView");
    public static void gallery_album() {
        driver.findElement(AlbumContainer).click();
        String album_Name = driver.findElement(AllPhotos_click).getText();
        Assert.assertEquals("All", album_Name);
    }

    public static void gallery_album_Scroll() throws InterruptedException {
       // TouchAction touchAction = new TouchAction(driver);
        driver.findElement(AlbumContainer).click();
        // Perform a scroll by initiating a press at (100, 500), moving to (100, 100), and releasing
        //Slide_touch(502,1731,511,548);



    }


    public static void gallery_images() {
        driver.findElement(AlbumContainer).click();
        driver.findElement(AllPhotos_click).click();
        waitForElement(Allphotos1);
        driver.findElement(Allphotos1).isDisplayed();

    }
    public static void Select_Func() {
        driver.findElement(Select_func).click();
        driver.findElement(Allphotos1).click();
        driver.findElement(Allphotos2).click();
        driver.findElement(Allphotos3).click();
        String image_Sel_Count =  driver.findElement(imageSelCount).getText();
        System.out.println("Selected Count : "+ image_Sel_Count);
        Assert.assertEquals("3", image_Sel_Count);
    }
    public static void Select_Clear_func() {
        driver.findElement(Select_func).click();
        driver.findElement(Allphotos1).click();
        driver.findElement(Allphotos2).click();
       driver.findElement(Allphotos3).click();
        String CLear_btn_text = driver.findElement(Clear_btn).getText();
        System.out.println("Clear bt : "+ CLear_btn_text);
        Assert.assertEquals("Clear", CLear_btn_text);
        driver.findElement(Clear_btn).click();
    }
    public static void Verify_Collage_btn() {
        driver.findElement(Select_func).click();
        driver.findElement(Allphotos1).click();
        driver.findElement(Allphotos2).click();
        driver.findElement(Allphotos3).click();

        String Collage_btn_text = driver.findElement(Collage_btn).getText();
        System.out.println("Collage btn : " + Collage_btn_text);
        Assert.assertEquals("COLLAGE", Collage_btn_text);
        driver.findElement(Collage_btn).click();
    }
    public static void Verify_Collage_Image() {
        driver.findElement(Select_func).click();
        driver.findElement(Allphotos1).click();
        driver.findElement(Allphotos2).click();
        driver.findElement(Allphotos3).click();
        driver.findElement(Collage_btn).click();
        driver.findElement(popup_ok).click();
        String Collage_btn_text = driver.findElement(Collage_image_ver).getText();
        System.out.println(" Collage tab image verify  " + Collage_btn_text);
        Assert.assertEquals("Collage", Collage_btn_text);
    }
    public static void Verify_Collage_Multi_Image() throws InterruptedException, IOException {
        driver.findElement(Select_func).click();
        driver.findElement(Allphotos1).click();
        driver.findElement(Allphotos2).click();
        driver.findElement(Allphotos3).click();
        //Tap_screen(568, 2140);
       waitForElement(Collage_image_ver);
       driver.findElement(Collage_image_ver).click();
        driver.findElement(popup_ok).click();
        if(driver.findElement(Collage_layout_2).isDisplayed()){
            System.out.println("Collage layout is visible");
            //captureScreenShots("layout is visible");
        }
        else {
            System.out.println("Collage layout is NOT visible");
        }
    }
    public static void Verify_Collage_Multi_layout_switch_Image() throws InterruptedException, IOException {
        driver.findElement(Select_func).click();
        driver.findElement(Allphotos1).click();
        driver.findElement(Allphotos2).click();
        driver.findElement(Allphotos3).click();
       // Tap_screen(568, 2140);
        waitForElement(Collage_image_ver);
        driver.findElement(Collage_image_ver).click();
        driver.findElement(popup_ok).click();
        driver.findElement(Collage_layout_1).click();
        //Thread.sleep(2000);
       // captureScreenShots("First Layout is displayed");
        Thread.sleep(2000);
        driver.findElement(Collage_layout_2).click();
        Thread.sleep(2000);
      //  captureScreenShots("Second Layout is displayed");
        driver.findElement(Collage_layout_3).click();
        Thread.sleep(2000);
       // captureScreenShots("Third Layout is displayed");
        driver.findElement(Collage_layout_4).click();
        Thread.sleep(2000);
     //   captureScreenShots("Fourth Layout is displayed");

    }
}