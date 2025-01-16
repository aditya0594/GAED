package Testcases;

import Pageobjects.GalleryPageAndroid;
import Pageobjects.HomePage;
import androidpageobjectss.DeletePageAndroid;
import baseClass.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

public class GalleryPageTest extends TestBase {
    HomePage LandingPage= new HomePage();
    DeletePageAndroid DeletePage = new DeletePageAndroid();

    public GalleryPageTest() throws IOException {
    }


    @Test(priority = 1, enabled = true)

    public void Gallery_verify_album() throws InterruptedException {
       // LandingPage.get_started_screen_signup();
        GalleryPageAndroid.gallery_album_Scroll();
    }

  /*  @Test(priority = 2, enabled = true)
    public void Gallery_verify_image() throws InterruptedException {
        LandingPage.get_started_screen_signup();
        GalleryPageAndroid.gallery_images();
    }
    @Test(priority = 3, enabled = true)
    public void Verify_Select_Functionality() throws InterruptedException {
        LandingPage.get_started_screen_signup();
        GalleryPageAndroid.Select_Func();
    }
    @Test(priority = 4, enabled = true)
    public void Verify_Select_Clear() throws InterruptedException {
        LandingPage.get_started_screen_signup();
        GalleryPageAndroid.Select_Clear_func();
    }
    @Test(priority = 5, enabled = true)
    public void Collage_Btn_functionality() throws InterruptedException {
        LandingPage.get_started_screen_signup();
        GalleryPageAndroid.Verify_Collage_btn();
    }
    @Test(priority = 6, enabled = true)
    public void Collage_Img_functionality() throws InterruptedException {
        LandingPage.get_started_screen_signup();
        GalleryPageAndroid.Verify_Collage_Image();
    }
    @Test(priority =7, enabled = true)
    public void Collage_multi_Img_functionality() throws InterruptedException, IOException {
        LandingPage.get_started_screen_signup();
        GalleryPageAndroid.Verify_Collage_Multi_Image();
    }
    @Test(priority =8, enabled = true)
    public void Verify_Collage_Multi_layoutswitch_Image() throws InterruptedException, IOException {
        LandingPage.get_started_screen_signup();
        GalleryPageAndroid.Verify_Collage_Multi_layout_switch_Image();
    }
*/
}
