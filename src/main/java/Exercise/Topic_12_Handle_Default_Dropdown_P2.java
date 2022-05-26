package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_12_Handle_Default_Dropdown_P2 {
    WebDriver driver;
    Select select;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01(){
        driver.get("https://www.rode.com/wheretobuy");

        select = new Select(driver.findElement(By.id("country")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Vietnam");
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();

        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
    }
}
