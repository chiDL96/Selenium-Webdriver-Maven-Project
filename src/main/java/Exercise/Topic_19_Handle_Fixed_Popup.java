package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_19_Handle_Fixed_Popup {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test
    public void TC_01_Fixed_In_DOM_Ngoai_Ngu_24h(){
        driver.get("https://ngoaingu24h.vn/");

        By popupLogin = By.xpath("//div[@id='modal-login-v1'][1]");
        Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());

        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());

        driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']")).sendKeys("automationFC");
        driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("12345678");
        driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_In_DOM_Kyna(){
        driver.get("https://kyna.vn/");
        WebElement loginPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));
        Assert.assertFalse(loginPopup.isDisplayed());

        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSecond(3);
        Assert.assertTrue(loginPopup.isDisplayed());
        driver.findElement(By.id("user-login")).sendKeys("automation");
        driver.findElement(By.id("user-password")).sendKeys("automation");
        driver.findElement(By.id("btn-submit-login")).click();

        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#k-popup-account-login div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

        driver.findElement(By.cssSelector("div#k-popup-account-login button.k-popup-account-close")).click();
        sleepInSecond(3);
        Assert.assertFalse(loginPopup.isDisplayed());
    }

    @Test
    public void TC_03_Fixed_Not_In_DOM(){
        driver.get("https://tiki.vn/");

        List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Overlay"));

        Assert.assertEquals(loginPopup.size(), 0);

        driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Overlay")).isDisplayed());

        loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Overlay"));
        Assert.assertEquals(loginPopup.size(), 1);

        driver.findElement(By.cssSelector("img.close-img")).click();
        sleepInSecond(3);

        loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Overlay"));
        Assert.assertEquals(loginPopup.size(), 0);
    }
    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
