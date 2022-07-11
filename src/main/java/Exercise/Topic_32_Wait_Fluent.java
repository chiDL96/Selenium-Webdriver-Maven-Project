package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;


public class Topic_32_Wait_Fluent {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        fluentDriver = new FluentWait<WebDriver>(driver);
        driver.findElement(By.cssSelector("div#start>button")).click();
        //sau khi bam loading icon xuat hien va mat di sau 5s
        //icon loading bien mat = hello  world text xuat  hien

        fluentDriver.withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
                    }
                });
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }
}
