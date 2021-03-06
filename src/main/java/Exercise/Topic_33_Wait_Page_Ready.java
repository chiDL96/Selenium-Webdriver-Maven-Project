package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_33_Wait_Page_Ready {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/BrowserDrivers/geckodriver");
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 30);
        action = new Actions(driver);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_OrangeHRM_Implicit() {
        driver.get("https://api.orangehrm.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='project']//h1")).getText(), "OrangeHRM REST API Documentation");
    }

    @Test
    public void TC_02_OrangeHRM_Explicit() {
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get("https://api.orangehrm.com/");
        //Wait cho loadiing bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='project']//h1")).getText(), "OrangeHRM REST API Documentation");
    }

    @Test
    public void TC_03_OrangeHRM_UI_Page_Ready() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.id("txtUsername")).sendKeys("admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        //wait page ready
        Assert.assertTrue(isPageLoadedSuccess());
        Assert.assertTrue(driver.findElement(By.id("div_graph_display_emp_distribution")).isDisplayed());

        //wait cho  loading icon  bien mat
//        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.loadmask"))));
//        Assert.assertTrue(driver.findElement(By.id("div_graph_display_emp_distribution")).isDisplayed());
    }


    @Test
    public void TC_04_Blog_Page_Ready() {
        driver.get("https://blog.testproject.io/");
        if (driver.findElement(By.xpath("//div[@id='mailch-bg']")).isDisplayed()) {
            driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
        }

        action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
        driver.findElement(By.cssSelector("section#search-2 span.glass")).click();

        Assert.assertTrue(isPageLoadedSuccess());

        List<WebElement> firstAllPostTitle = driver.findElements(By.cssSelector("h3.post-tiitle>a"));
        for (WebElement postTitle : firstAllPostTitle) {
            Assert.assertTrue(postTitle.getText().contains("Selenium"));
        }
    }


    //jQuery + javasccript
    public Boolean isPageLoadedSuccess() {
        explicitWait = new WebDriverWait(driver, 30);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
//git
}
