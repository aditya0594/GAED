package Pageobjects;


import baseClass.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuyAndSell extends TestBase {

    LoginConsumer LoginUser = new LoginConsumer();
    SignUp signup =  new SignUp();
     By BuySelltab =  By.xpath("//a[@class='text-sm 3xl:text-lg text-white rounded-full py-2 p-4 cursor-pointer group-hover:text-primary']");
    By SellProjectButton =  By.xpath("//span[normalize-space()='Sell Project']");
    By HomeButton = By.xpath("//a[@class='text-sm text-white rounded-full py-2 p-4 hover:bg-white hover:text-primary']");
    public void validate_BuySell_buttons() throws InterruptedException {
        String  BuyandSelltitle =  driver.findElement(BuySelltab).getText();
        Assert.assertEquals("Buy/Sell",BuyandSelltitle);
        driver.findElement(BuySelltab).click();
    }
    public void VerifySellProject(){String  sellProjecttitle =  driver.findElement(SellProjectButton).getText();
        Assert.assertEquals("Sell Project",sellProjecttitle);
        driver.findElement(SellProjectButton).click();}
    public void validate_BuySell_buttons_without_signin() throws InterruptedException {

        String  sellProjecttitle =  driver.findElement(SellProjectButton).getText();
        Assert.assertEquals("Sell Project",sellProjecttitle);
        driver.findElement(SellProjectButton).click();
    }
    public void validate_SellProject_button(){
        String  sellProjecttitle =  driver.findElement(SellProjectButton).getText();
        Assert.assertEquals("Sell Project",sellProjecttitle);
        driver.findElement(SellProjectButton).click();
    }
    By NextBtn = By.xpath("//span[@class='text-sm font-medium text-white']");
    By SaveAsDraftbtn = By.xpath("//span[normalize-space()='Save as draft']");
    By Backbtn = By.xpath("//span[normalize-space()='Back']");
    By ProjectpageTitle = By.xpath("//span[@class='bg-white absolute left-0 top-6 pr-8']");
    By invalidProjectTileFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[1]/div[1]/span[1]");
    By invalidProjectTypeFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[2]/div[1]/span[1]");
    By invalidProjectAddressFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[3]/div[1]/span[1]");
    By invalidProjectBidsFieldtext = By.xpath("//body/div[@id='root']/div[@class='App']/div/div/div/section[@class='px-4 xl:px-24 2xl:px-48 pt-8']/div[@class='flex mt-1 flex-col']/form/div/div[@class='h-full']/div[@class='mb-6']/div/div[@class='grid grid-cols-4 gap-4 mb-5']/div[4]/div[1]/span[1]");
    By ProjectTileField = By.xpath("//span[@class='bg-white absolute left-0 top-6 pr-8']");
    public void  validate_next_saveAsDraft_back(){

        driver.findElement(SellProjectButton).click();
        boolean nextbtn = driver.findElement(NextBtn).isDisplayed();
        Assert.assertTrue(nextbtn);

        boolean saveAsbtn = driver.findElement(SaveAsDraftbtn).isDisplayed();
        Assert.assertTrue(saveAsbtn);

        boolean backbtn = driver.findElement(Backbtn).isDisplayed();
        Assert.assertTrue(backbtn);

    }
    public void invalidate_Stepper1_ProjectFields_button(){

        driver.findElement(SellProjectButton).click();
       driver.findElement(NextBtn).click();
       String projectDetailsTitle = driver.findElement(ProjectpageTitle).getText();

       Assert.assertEquals("Project Details",projectDetailsTitle);
        String ProjectTileError = driver.findElement(invalidProjectTileFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectTileError);

        String ProjectTypeerror = driver.findElement(invalidProjectTypeFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectTypeerror);

        String ProjectAddressError = driver.findElement(invalidProjectAddressFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectAddressError);

        String ProjectbidsError = driver.findElement(invalidProjectBidsFieldtext).getText();
        Assert.assertEquals("Field is Required.",ProjectbidsError);

    }
    By ProjectTitleField = By.xpath("//input[@name='projectTitle']");
    By projectTypeField = By.xpath("//div[@class='css-19bb58m']");
    By projectTypeFieldText = By.id("react-select-2-input");
    By ProjectAddressField = By.xpath("//input[@name='location']");
    By proectAddressGoogleField = By.xpath("//section[@class='px-4 xl:px-24 2xl:px-48 pt-8']//li[1]");
    By Bid_Validity = By.xpath("//input[@name='bidValidity']");
    By SendProjecttext = By.id("react-select-2-input");


    public void vaild_Stepper1_ProjectFields_button() throws InterruptedException {

        driver.findElement(SellProjectButton).click();
        String project_Name = ProjectTileText();
        write_excel(0,1,project_Name);
        System.out.println("Project Name " + project_Name);
        driver.findElement(ProjectTitleField).sendKeys(project_Name);

        driver.findElement(projectTypeField).click();
        Thread.sleep(2000);
        driver.findElement(projectTypeFieldText).sendKeys("Solar"+ Keys.TAB);
        tab(projectTypeFieldText);
        //driver.findElement(By.id("react-select-2-input")).sendKeys(Keys.TAB);

      // driver.findElement(ProjectAddressField).click();
        //Thread.sleep(2000);
        signup.addressDropdown("Chtra");
        Thread.sleep(1000);
        tab(ProjectAddressField);
        driver.findElement(Bid_Validity).sendKeys("90");
        ////section[@class='px-4 xl:px-24 2xl:px-48 pt-8']//li[1]
        driver.findElement(NextBtn).click();
    }

    By AgreementType = By.xpath("//div[@class='text-sm text-black-100 w-full css-b62m3t-container']");
    By Cost_Sharing = By.xpath("//*[@name='costSharing']");
    By Average_Monthly = By.xpath("//*[@name='averageMonthlyPayment']");
    By Capacity = By.xpath("//*[@name='capacity']");
    By Tenure_Length= By.xpath("//input[@name='tenureLength']");

    By Tenure_Age = By.xpath("//input[@name='tenureAge']");
    By Module_Specification = By.xpath("//div[contains(@class,'css-1klm6dm-control')]//div[contains(@class,'css-19bb58m')]");
    By Module_Value_specification = By.id("react-select-7-input");
    By Module_Brand = By.xpath("//input[@name='moduleBrand']");
    By Module_value_Brand = By.xpath("//input[@name='moduleBrand']");
    By Grid_Connection = By.xpath("//div[contains(@class,'css-1klm6dm-control')]//div[contains(@class,'css-19bb58m')]");
    By Grid_value_Connection = By.id("react-select-8-input");
    By Current_Tenancy = By.xpath("//input[@name='currentTenancy']");
    //By AgreementType = By.xpath("//div[@class=' css-1klm6dm-control']//div[@class=' css-19bb58m']");
  //  By AgreementType = By.xpath("//div[@class=' css-1klm6dm-control']//div[@class=' css-19bb58m']");
  //  By AgreementType = By.xpath("//div[@class=' css-1klm6dm-control']//div[@class=' css-19bb58m']");

    public void vaild_Stepper2_ProjectFields_button() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='text-sm text-black-100 w-full css-b62m3t-container']")).click();
        waitForElement(AgreementType);
        driver.findElement(AgreementType).click();
        Thread.sleep(1000);
        driver.findElement(By.id("react-select-3-input")).sendKeys("PPA Agreement");
        driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.TAB);
        Thread.sleep(1000);

        click(Cost_Sharing);
        driver.findElement(Cost_Sharing).sendKeys("90");
        tab(Cost_Sharing);
        Thread.sleep(1000);

      click(Average_Monthly);
        driver.findElement(Average_Monthly).sendKeys("50");
        tab(Average_Monthly);

        Thread.sleep(1000);
        click(Capacity);
        driver.findElement(Capacity).sendKeys("5000");
        tab(Capacity);

        Thread.sleep(1000);
        click(Tenure_Length);
        driver.findElement(Tenure_Length).sendKeys("50");
        tab(Tenure_Length);

        Thread.sleep(1000);
        click(Tenure_Age);
        driver.findElement(Tenure_Age).sendKeys("50");
        tab(Tenure_Age);

        //Amorphous Silicon Solar Cells
        driver.findElement(Module_Specification).click();
        Thread.sleep(1000);
        driver.findElement(Module_Value_specification).sendKeys("Amorphous Silicon Solar Cells");
        Thread.sleep(1000);
        tab(Module_Value_specification);


        Thread.sleep(1000);
        click(Module_Brand);
        driver.findElement(Module_Brand).sendKeys("Module brand");
        tab(Module_Brand);

        driver.findElement(Grid_Connection).click();
        Thread.sleep(1000);
        driver.findElement(Grid_value_Connection).sendKeys("Hybrid Systems");
        tab(Grid_value_Connection);

        Thread.sleep(1000);
        click(Current_Tenancy);
        driver.findElement(Current_Tenancy).sendKeys("1");
        tab(Current_Tenancy);

        Thread.sleep(1000);
        By installation_Date = By.xpath("//*[@class='text-gray-400 absolute w-4 h-4 top-2 right-4 cursor-pointer']");
        driver.findElement(installation_Date).click();
        Thread.sleep(1000);
        //Date selection
        WebElement dateDropdown = driver.findElement(By.xpath("//*[@class='react-datepicker__month-select']"));
        Select select_date = new Select(dateDropdown);
        select_date.selectByValue("3");
        Thread.sleep(1000);
        //Year selection
        WebElement yearDropdown = driver.findElement(By.xpath("//*[@class='react-datepicker__year-select']"));
        Select select_year = new Select(yearDropdown);
        select_year.selectByValue("1994");

        //Day click
        driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day react-datepicker__day--013') or contains(@class, 'react-datepicker__day react-datepicker__day--013 react-datepicker__day--selected')]")).click();



        //Temporary Occupation Permit
        By Temporary_Occupation_Permit = By.xpath("//span[@aria-label='Temporary Occupation Permit (TOP) Date refers to the date when the temporary occupation permit for the project was issued. Please enter the date in the specified format (MM/DD/YYYY).']//*[name()='svg']//*[name()='path' and contains(@d,'M148 288h-')]");
        driver.findElement(Temporary_Occupation_Permit).click();

        //Date selection
        WebElement dateDropdown1 = driver.findElement(By.xpath("//*[@class='react-datepicker__month-select']"));
        Select select_date1 = new Select(dateDropdown1);
        select_date1.selectByValue("3");
        Thread.sleep(1000);
        //Year selection
        WebElement yearDropdown1 = driver.findElement(By.xpath("//*[@class='react-datepicker__year-select']"));
        Select select_year1 = new Select(yearDropdown1);
        select_year1.selectByValue("1994");

        //Day click
        driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day react-datepicker__day--013') or contains(@class, 'react-datepicker__day react-datepicker__day--013 react-datepicker__day--selected')]")).click();


        By Selling_price = By.xpath("//*[@name='askingPrice']");
        Thread.sleep(1000);
        click(Selling_price);
        driver.findElement(Selling_price).sendKeys("1");
        tab(Selling_price);


        driver.findElement(NextBtn).click();

    }
    By image_upload= By.xpath("//div[@class='flex flex-col items-center justify-center pt-5 pb-6']");
    public void vaild_Stepper3_ProjectFields_button() throws InterruptedException {

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



        for(int i=0; i<=4;i++){
            WebElement project_image = driver.findElement(By.xpath("//input[@id='dropzone-file']"));
            project_image.sendKeys(FinalPAth);
        }
        Thread.sleep(2000);

        By youtube_url= By.xpath("//input[@name='youtubeUrl']");
        By youtube_url_submit= By.xpath("//*[contains(text(),'Submit')]");
        By Project_Save= By.xpath("//span[@class='text-sm font-medium text-white']");

        driver.findElement(youtube_url).sendKeys("https://www.youtube.com/watch?v=sYIGiuycD-k");
        driver.findElement(youtube_url_submit).click();
        driver.findElement(Project_Save).click();




    }
    @Override
    public String getTitle(){

        return null;
    }




}
