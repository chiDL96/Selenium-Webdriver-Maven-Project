package Exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;

public class Topic_20_Handle_Random_Popup {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test
    public void TC_01_Random_In_DOM_KMplayer(){
        driver.get("https://www.kmplayer.com/home");
        WebElement popupLayer = driver.findElement(By.cssSelector("div.pop-layer"));
        WebElement btnClosePopup = driver.findElement(By.cssSelector("area#btn-r"));
        checkPopupInDOMDisplay(popupLayer, btnClosePopup);

    }

    @Test
    public void TC_02_Random_Not_In_DOM_Dehieu(){
        driver.get("https://dehieu.vn/");
        List<WebElement> popup = driver.findElements(By.cssSelector("div.popup-content"));
        if (popup.size() > 1 && popup.get(0).isDisplayed()){
            driver.findElement(By.cssSelector("div.popup-content button.close")).click();
        }

    }

    @Test
    public void TC_03_Random_Not_In_DOM_Dehieu_SetSize(){
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get("https://dehieu.vn/");
        sleepInSecond(3);
        List<WebElement> popup = driver.findElements(By.cssSelector("div.popup-content"));
        if (popup.size() > 0 && popup.get(0).isDisplayed()) {

            driver.findElement(By.id("popup-name")).sendKeys("abc");
            driver.findElement(By.id("popup-email")).sendKeys("abc@gmail.coom");
            driver.findElement(By.id("popup-phone")).sendKeys("0282882929");

            sleepInSecond(3);
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.popup-content button.close")));
            sleepInSecond(3);
        }

    }


    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkPopupInDOMDisplay(WebElement popupElement, WebElement btnClose){
        if (popupElement.isDisplayed()){
            jsExecutor.executeScript("arguments[0].click();", btnClose);
        }
    }
}
