package Exercise;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;


public class Topic_32_Wait_Fluent {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver;
    int sumTime = 30;
    int pollTime = 5;


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

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        fluentDriver = new FluentWait<WebDriver>(driver);
        clickToElement(By.cssSelector("div#start>button"));
        isElementDisplayed(By.cssSelector("div#finish>h4"));
    }

    public WebElement findElement(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(sumTime))
                .pollingEvery(Duration.ofSeconds(pollTime))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }

    public void clickToElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public WebElement waitElementVisible(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(sumTime))
                .pollingEvery(Duration.ofSeconds(pollTime))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementDisplayed(By locator) {
        WebElement element = waitElementVisible(locator);
        FluentWait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(sumTime))
                .pollingEvery(Duration.ofSeconds(pollTime))
                .ignoring(NoSuchElementException.class);

        boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                boolean flag = element.isDisplayed();
                return flag;
            }
        });
        return element.isDisplayed();
    }


    @Test
    public void TC_03() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countDown = driver.findElement(By.id("javascript_countdown_time"));

        FluentWait<WebElement> wait = new FluentWait<WebElement>(countDown)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        Boolean countDownSecond = wait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return countDown.getText().endsWith("00");
            }
        });


    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }
}
