package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_31_Wait_Mix_Implicit_Explicit {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Element_Found(){
        driver.get("https://www.facebook.com/");
        By emailIDBy = By.id("email");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("Start implicit: " + getTimeNow());
        Assert.assertTrue(driver.findElement(emailIDBy).isDisplayed());
        System.out.println("End implicit: " + getTimeNow());


        explicitWait = new WebDriverWait(driver, 15);
        System.out.println("Start explicit: " + getTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
        System.out.println("End explicit: " + getTimeNow());
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit(){
        driver.get("https://www.facebook.com/");
        By fakeIDBy = By.id("fake");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("Start implicit: " + getTimeNow());
        Assert.assertFalse(driver.findElement(fakeIDBy).isDisplayed());
        System.out.println("End implicit: " + getTimeNow());
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit() {
        driver.get("https://www.facebook.com/");
        By fakeIDBy1 = By.id("fake1");
        explicitWait = new WebDriverWait(driver, 15);
        System.out.println("Start explicit: " + getTimeNow());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(fakeIDBy1));
        System.out.println("End explicit: " + getTimeNow());
    }

    @Test
    public void TC_04_Element_Not_Found_Mix() {
        //implicir < explicit
        //implicir = explicit
        //implicir > explicit
        driver.get("https://www.facebook.com/");
        By fakeIDBy1 = By.id("fake1");

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("Start implicit: " + getTimeNow());
        try {
            Assert.assertTrue(driver.findElement(fakeIDBy1).isDisplayed());
        } catch (Exception e) {
            System.out.println("End implicit: " + getTimeNow());
        }

        explicitWait = new WebDriverWait(driver, 15);
        System.out.println("Start explicit: " + getTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(fakeIDBy1));
        } catch (Exception e) {
            System.out.println("End explicit: " + getTimeNow());
        }

    }

    public String getTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}

