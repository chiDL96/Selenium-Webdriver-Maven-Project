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

import java.util.concurrent.TimeUnit;

public class Topic_30_Wait_Explicit {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    //Wait ro rang: voi cac dieu kien/status cu the
    WebDriverWait explicitWait;

    By loadingIcon = By.cssSelector("div#loading");
    By helloTxt = By.cssSelector("div#finish h4");
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
//        explicitWait = new WebDriverWait(driver, 15);

        //Wait ngam dinh: k co 1 element/ dieu kien/ status nao ro rang
        //Ngam dinh cho viec tim element
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.close();
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

}
