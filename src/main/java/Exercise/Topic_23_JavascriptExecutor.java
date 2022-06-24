package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_23_JavascriptExecutor {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void Example(){

        //Selenium: WebElement click
        driver.findElement(By.xpath(" ")).click();

        //Selenium: Actions click

        //Selenium: Javascript click

    }

    @Test
    public void TC_01(){
        driver.get("http://live.techpanda.org/");
        //Click WebElement
        //driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();

        //Click jsExecutor
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
    }

    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
