package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_15_Custom_Radio_Checkbox {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        jsExecutor = (JavascriptExecutor) driver;
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void TC_01_Custom_Checkbox(){
        driver.get("https://material.angular.io/components/checkbox/examples");
        //Cach 1:
        //Su dung the span de click, sau do dung the input de verify
//        By checkedCheckboxText = By.xpath("//span[text()='Checked']");
//        driver.findElement(checkedCheckboxText).click();
//
//        By checkedCheckbox = By.xpath("//span[text()='Checked']//preceding::input");
//        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());


        //Cach 2:
        //Su dung the input de click (javascript executor - k quan tam element bi che/ bi an), sau do su dung input de verify
        By checkedCheckbox1 = By.xpath("//span[text()='Checked']//preceding::input");
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(checkedCheckbox1));
        Assert.assertTrue(driver.findElement(checkedCheckbox1).isSelected());

        By beforeRadio = By.xpath("//span[text()='Before']/preceding-sibling::span/input");
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(beforeRadio));
        Assert.assertTrue(driver.findElement(beforeRadio).isSelected());

    }


    @Test
    public void TC_02_Custom_Radio(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By hanoiRadio = By.xpath("//div[@aria-label='Hà Nội']");
        driver.findElement(hanoiRadio).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(hanoiRadio).getAttribute("aria-checked"),"true");

        By miQuangCheckbox = By.xpath("//div[@aria-label='Mì Quảng']");
        driver.findElement(miQuangCheckbox).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(miQuangCheckbox).getAttribute("aria-checked"),"true");
    }

    public void sleepInSecond(long TimeInSecond){
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
