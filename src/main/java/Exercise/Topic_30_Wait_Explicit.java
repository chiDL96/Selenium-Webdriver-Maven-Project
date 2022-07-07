package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_30_Wait_Explicit {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    //Wait ro rang: voi cac dieu kien/status cu the
    WebDriverWait explicitWait;

    String folderPath = "/UploadFiles/";
    String img1 = "test1.jpeg";
    String img2 = "test2.jpeg";
    String img3 = "test3.jpeg";
    String img1FolderUpload = projectPath + folderPath + img1;
    String img2FolderUpload = projectPath + folderPath + img2;
    String img3FolderUpload = projectPath + folderPath + img3;

    By loadingIcon = By.cssSelector("div#loading");
    By helloTxt = By.cssSelector("div#finish h4");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
//        explicitWait = new WebDriverWait(driver, 15);

        //Wait ngam dinh: k co 1 element/ dieu kien/ status nao ro rang
        //Ngam dinh cho viec tim element
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Invisible() {
        //Doi 1 element bien mat, de element can verify hien thi
        explicitWait = new WebDriverWait(driver, 5);

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start button")).click();

        //Loading icon bien mat sau 5s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Visible() {
        //Doi element can verify hien thi
        explicitWait = new WebDriverWait(driver, 10);

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start button")).click();

        //Hello text element hien thi sau 10s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloTxt));

        Assert.assertEquals(driver.findElement(helloTxt).getText(), "Hello World!");
    }

    @Test
    public void TC_03_Number() {
        explicitWait = new WebDriverWait(driver, 10);

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start button")).click();

        //Hello text element = 1
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(helloTxt, 1));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_04_Ajax_Loading() {
        explicitWait = new WebDriverWait(driver, 30);

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //Doi cho datepiicker xuat hien
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));

        WebElement datePickerTxt = driver.findElement(By.id("ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel"));
        //Kiem tra txt No selected ... xuat hien
        Assert.assertEquals(datePickerTxt.getText(), "No Selected Dates to display.");

        //Wait cho so 11 hien len sau do click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();

        //Wait cho loading bien nmat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));

        //Sau khi click chon thi element datePickerTxt k the lay bang bien da khai bao o tren
        //Do  do phai gan lai
        datePickerTxt = driver.findElement(By.id("ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel"));

        //Kiem tra xem da click dung doi tuong chua
        Assert.assertTrue(datePickerTxt.getText().contains("11"));

        //Wait cho ngay da dc selected thanh cong
        WebElement txtSelected = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='11']")));
        Assert.assertTrue(txtSelected.isDisplayed());
    }


    @Test
    public void TC_05_Upload_File() {
        driver.get("https://gofile.io/uploadFiles");

        WebElement btnUplooad = driver.findElement(By.xpath("//input[@type='file']"));
        btnUplooad.sendKeys(img1FolderUpload + "\n" + img2FolderUpload + "\n" + img3FolderUpload);

        explicitWait = new WebDriverWait(driver, 20);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div.progress-bar"))));

        WebElement messSuccess = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));

        Assert.assertTrue(messSuccess.isDisplayed());

        driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();


        WebElement btnDownload1 = driver.findElement(By.xpath("//span[text()='" + img1 + "']/parent::a/parent::div//following-sibling::div//button[@id='contentId-download']"));
        WebElement btnDownload2 = driver.findElement(By.xpath("//span[text()='" + img2 + "']/parent::a/parent::div//following-sibling::div//button[@id='contentId-download']"));
        WebElement btnDownload3 = driver.findElement(By.xpath("//span[text()='" + img3 + "']/parent::a/parent::div//following-sibling::div//button[@id='contentId-download']"));

        Assert.assertFalse(btnDownload1.isDisplayed());
        Assert.assertFalse(btnDownload2.isDisplayed());
        Assert.assertFalse(btnDownload3.isDisplayed());
    }
}
