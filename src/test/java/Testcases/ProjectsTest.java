package Testcases;

import Pageobjects.HomePage;
import Pageobjects.LoginConsumer;
import Pageobjects.Projects;
import baseClass.TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Optional;

public class ProjectsTest extends TestBase {

    public ProjectsTest() throws IOException {

    }
    HomePage homepage = new HomePage();
    LoginConsumer login = new LoginConsumer();
    Projects project = new Projects();

    @Test(priority = 3,enabled = true)
    public void Create_Project() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        project.projectClick();
        project.projectCreate();
        test.pass("Create project mandatory field validation ");
    }
    @Test(priority = 3,enabled = true)
    public void otp() throws InterruptedException {
        ExtentTest test = extent.createTest("Create project mandatory field validation ");
        homepage.homepage();
        login.LoginConsumerSuceessful();
        // Get the DevTools session
        // **Start DevTools in Parallel (Capture OTP)**
        /*DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        new Thread(() -> {
            devTools.addListener(Network.responseReceived(), response -> {
                RequestId requestId = response.getRequestId();
                Response res = response.getResponse();

                // **Filter the "Verify OTP" API Response**
                if (res.getUrl().contains("https://qa.gaedkeeper.com/qa/api/v1/user/send-otp")) { // Adjust based on API URL
                    System.out.println("Captured OTP Response from API: " + res.getUrl());

                    // Fetch response body
                    Optional<Network.GetResponseBodyResponse> responseBody =
                            Optional.ofNullable(devTools.send(Network.getResponseBody(requestId)));

                    if (responseBody.isPresent()) {
                        String body = responseBody.get().getBody();
                        System.out.println("Full Response: " + body);

                        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
                        if (jsonObject.has("otp")) {
                            String extractedOTP = jsonObject.get("data.code").getAsString();
                            System.out.println("Extracted OTP: " + extractedOTP);

                            // **Enter OTP in UI**

                        }
                    }
                }
            });
        }).start(); // Run the thread in parallel

        // **Wait for OTP Capture & Submission**
        Thread.sleep(5000);*/

    }




}
