package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_18_User_Interaction_P2 {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor ;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test
    public void TC_01_Drag_And_Drop_HTML5(){
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement sourceRectangle = driver.findElement(By.id("column-a"));
        WebElement targetRectangle = driver.findElement(By.id("column-b"));

    }

    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
