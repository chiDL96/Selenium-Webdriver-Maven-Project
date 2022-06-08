package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_17_User_Interaction {
    WebDriver driver;
    Actions action;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test
    public void TC_01_Hover_To_Element_p1(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTxtbox = driver.findElement(By.cssSelector("input#age"));
        action.moveToElement(ageTxtbox).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_To_Element_p2(){
        driver.get("http://www.myntra.com/");
        WebElement kidLink = driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"));
        action.moveToElement(kidLink).perform();
        sleepInSecond(2);

        WebElement kidFootwearLink = driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']/following-sibling::div//a[text()='Footwear']"));
        action.click(kidFootwearLink).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Footwear']")).isDisplayed());
    }

    @Test
    public void TC_03_Hover_To_Element_p3(){
        driver.get("https://www.fahasa.com/");
        if (driver.findElement(By.xpath("//iframe[@id='preview-notification-frame']")).isDisplayed()) {
            closeIframe("//iframe[@id='preview-notification-frame']","#NC_IMAGE1");
        }
        sleepInSecond(2);

        String windowID = driver.getWindowHandle();
        driver.switchTo().window(windowID);
        WebElement menuIcon = driver.findElement(By.cssSelector("span.icon_menu"));
        action.moveToElement(menuIcon).perform();
        sleepInSecond(2);

        action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi']"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Mô Hình Nhân Vật']"))).click().perform();

        Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Mô Hình Nhân Vật']")).isDisplayed());

    }


    public void sleepInSecond(long TimeInSecond){
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeIframe(String frame, String btnClose) {
        driver.switchTo().frame(driver.findElement(By.xpath(frame)));
        driver.findElement(By.cssSelector(btnClose)).click();
    }

}
