package Exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_17_User_Interaction {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    Select select;
    String projectPath = System.getProperty("user.dir");
    String os = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
//        driver.close();
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


        WebElement toySelected = driver.findElement(By.xpath("//strong[text()='Mô Hình Nhân Vật']"));
        Assert.assertTrue(toySelected.isDisplayed());

    }

    @Test
    public void TC_04_Demo_JsExecutor(){
        driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
        WebElement banner = driver.findElement(By.xpath("//div[@class='div-cost-estimates']"));
        WebElement carType = driver.findElement(By.id("selectize-input"));
        WebElement carTypeSelected = driver.findElement(By.xpath("//a[text()='CITY RS (Đỏ)']"));
        By city = By.id("province");
        String citySelected = "Hà Nội";
        By area = By.id("registration_fee");
        String areaSelected = "Khu vực I";

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", banner);
        jsExecutor.executeScript("arguments[0].click();", carType);
        jsExecutor.executeScript("arguments[0].click();", carTypeSelected);

        select = new Select(driver.findElement(city));
        select.selectByVisibleText(citySelected);

        select = new Select(driver.findElement(area));
        select.selectByVisibleText(areaSelected);
    }


    @Test
    public void TC_05_Click_And_Hover_Element(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
//        allNumbers.get(0).click();
        action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(10)).release().perform();
        allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumbers.size(), 9);
    }

    @Test
    public void TC_06_Hold_Control_And_Click_Multiple_Element(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        if (os.contains("Mac")){
            action.keyDown(Keys.COMMAND).perform();
        } else {
            action.keyDown(Keys.CONTROL).perform();
        }
        action.click(allNumbers.get(1)).click(allNumbers.get(7)).click(allNumbers.get(11)).perform();

        action.keyUp().perform();
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
