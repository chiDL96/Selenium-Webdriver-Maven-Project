package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
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

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButtonBy = By.cssSelector("button.fhs-btn-login");

        Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());

        driver.findElement(By.id("login_username")).sendKeys("chidl@unstatic.co");
        driver.findElement(By.id("login_password")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
        //getCssValue tra ra rgba, con tren web dag tra ra rgb
        String loginButtonBackgroundColor = driver.findElement(loginButtonBy).getCssValue("background-color");

        System.out.println(loginButtonBackgroundColor);
        String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColor).asHex().toUpperCase();
        String loginButtonBackgroundColorRGBA = Color.fromString(loginButtonBackgroundColor).asRgba();
        sleepInSecond(1);
        Assert.assertEquals(loginButtonBackgroundColorHexa, "#C92127");
        Assert.assertEquals(loginButtonBackgroundColorRGBA, "rgba(201, 33, 39, 0.22)");
    }

    public void sleepInSecond(long TimeInSecond){
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
