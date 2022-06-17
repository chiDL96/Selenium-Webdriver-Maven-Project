package Exercise;

import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_21_Handle_Frame_Iframe {

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
//        driver.close();
    }

    @Test
    public void TC_01_Iframe() {
        //A
        driver.get("https://kyna.vn/");
        //A->B
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
        //B
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
        //B->A
        driver.switchTo().defaultContent();
        //A->C
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#cs-live-chat > iframe")));
        //C
        driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("John");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456789");
        //C->A
        driver.switchTo().defaultContent();
        //A
        driver.findElement(By.xpath("//button[@class='search-button']/preceding-sibling::input")).sendKeys("Excel");
        driver.findElement(By.cssSelector("button.search-button")).click();

        List<WebElement> result = driver.findElements(By.xpath("//div[@class='content']/h4"));
        for (WebElement totalResult : result) {
            Assert.assertTrue(totalResult.getText().contains("Excel"));
        }
    }

    @Test
    public void TC_02_Automation_Blog() {
        driver.get("https://automationfc.com/2018/10/15/java-webdriver-14-verify-html5-validation-message/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.youtube-player")));

        driver.findElement(By.cssSelector("button.ytp-large-play-button")).click();

        driver.switchTo().defaultContent();

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page iframe")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.lfloat a")).getText(), "Automation FC");
    }

    @Test
    public void TC_03_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

    }

}
