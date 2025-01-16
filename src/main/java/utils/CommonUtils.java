package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class CommonUtils {


    DesiredCapabilities caps = new DesiredCapabilities();
    String path;
    public AppiumDriver<MobileElement> driver;

    public void setup(String platformName, String deviceName, String uri) throws MalformedURLException {

/*

        System.out.println("Session is creating");
		path = System.getProperty("user.dir");
    	caps.setCapability("platformName", platformName);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("app", path+"//app//HP600AndMaintenanceRealeaseBuildDate.19.05.2022.apk");
        //path+"//app//HP600AndMaintenanceRealeaseBuildDate.17.10.2022v2.82.7.apk"
		caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("fullReset", "false");
        caps.setCapability("udid","RZ8NA1P2S8D");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
        driver = new AndroidDriver<MobileElement>(new URL(uri), caps); //uri : http://127.0.0.1:4723/wd/hub
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Hide the keyboard
        caps.setCapability("unicodeKeyboard", true);
        caps.setCapability("resetKeyboard", true);
    }

*/


    }

}