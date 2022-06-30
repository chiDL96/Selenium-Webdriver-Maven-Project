package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_24_Handle_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String uploadFolderPath = File.separator + "UploadFiles" + File.separator;
    String image1 =  "test1.jpeg";
    String image2 =  "test2.jpeg";
    String image3 =  "test3.jpeg";


    String image1FilePath = projectPath + uploadFolderPath + image1;
    String image2FilePath = projectPath + uploadFolderPath + image2;
    String image3FilePath = projectPath + uploadFolderPath + image3;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void TC_01_One_File_One_Time(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By btnUploadImage = By.xpath("//input[@type='file']");

        //selenium sendkeys method
        driver.findElement(btnUploadImage).sendKeys(projectPath + uploadFolderPath + image1);
        driver.findElement(btnUploadImage).sendKeys(projectPath + uploadFolderPath + image2);
        driver.findElement(btnUploadImage).sendKeys(projectPath + uploadFolderPath + image3);

        //verify add image
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image1 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image2 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image3 +"']")).isDisplayed());

        //Click button upload
        List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for ( WebElement btn: uploadButtons) {
            btn.click();
            sleepInSecond(1);
        }

        //verify upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image3 + "']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File_One_Time(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By btnUploadImage = By.xpath("//input[@type='file']");

        //selenium sendkeys method upload 3 file
        driver.findElement(btnUploadImage).sendKeys(image1FilePath + "\n" + image2FilePath + "\n" + image3FilePath);

        //verify add image
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image1 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image2 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image3 +"']")).isDisplayed());

        //Click button upload
        List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for ( WebElement btn: uploadButtons) {
            btn.click();
            sleepInSecond(1);
        }

        //verify upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image3 + "']")).isDisplayed());
    }

    @Test
    public void TC_03_Upload_File(){
        driver.get("https://gofile.io/uploadFiles");
        By btnUploadImage = By.xpath("//input[@type='file']");

        //selenium sendkeys method upload 3 file
        driver.findElement(btnUploadImage).sendKeys(image1FilePath + "\n" + image2FilePath + "\n" + image3FilePath);
        sleepInSecond(5);

        Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());

        driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and contains(text(), '" + image1 + "')]/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and contains(text(), '" + image2 + "')]/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and contains(text(), '" + image3 + "')]/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());


    }


    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
