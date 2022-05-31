package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_14_Button_Radio_Checkbox {
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
        driver.quit();
    }

    @Test
    public void tc_01_Button(){
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        Assert.assertFalse(driver.findElement(By.className("fhs-btn-register")).isDisplayed());

        driver.findElement(By.id("login_username")).sendKeys("chidl@unstatic.co");
        driver.findElement(By.id("login_password")).sendKeys("123456");

        WebElement btnLogin = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertTrue(btnLogin.isEnabled());
        btnLogin.getCssValue("")
    }
}
