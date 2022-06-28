package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_24_Handle_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
//        driver.quit();
    }

    @Test
    public void TC_01_One_File_One_Time(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        WebElement btnAddImage = driver.findElement(By.xpath("//input[@type='file']"));
        WebElement btnUpload = driver.findElement(By.xpath("//button[@class='btn btn-primary start']"));

        //selenium sendkeys method
        btnAddImage.sendKeys(projectPath + "/UploadFiles/test1.jpeg");
        sleepInSecond(5);

    }

    @Test
    public void TC_01_Multiple_File_One_Time(){

    }

    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
