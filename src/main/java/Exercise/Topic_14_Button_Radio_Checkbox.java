package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Button_Radio_Checkbox {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void tc_01_Button(){
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButtonBy = By.cssSelector("button.fhs-btn-login");

        Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());

        driver.findElement(By.id("login_username")).sendKeys("chidl@unstatic.co");
        driver.findElement(By.id("login_password")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
        //getCssValue tra ra rgba, con tren web dag tra ra rgb
        String loginButtonBackgroundColor = driver.findElement(loginButtonBy).getCssValue("background-color");

        System.out.println(loginButtonBackgroundColor);
        String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColor).asHex().toUpperCase();
        String loginButtonBackgroundColorRGBA = Color.fromString(loginButtonBackgroundColor).asRgba();
        sleepInSecond(1);
        Assert.assertEquals(loginButtonBackgroundColorHexa, "#C92127");
        Assert.assertEquals(loginButtonBackgroundColorRGBA, "rgba(201, 33, 39, 0.22)");
    }

    @Test
    public void TC_02_Default_Radio_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        //checkbox
        By RheumaticCheckbox = By.xpath("//input[@value='Rheumatic Fever']");
        By asthmaCheckbox = By.xpath("//input[@value='Asthma']");
        By lungCheckbox = By.xpath("//input[@value='Lung Disease']");

        driver.findElement(RheumaticCheckbox).click();
        driver.findElement(asthmaCheckbox).click();
        driver.findElement(lungCheckbox).click();

        Assert.assertTrue(driver.findElement(RheumaticCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(asthmaCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(lungCheckbox).isSelected());

        driver.findElement(RheumaticCheckbox).click();
        driver.findElement(asthmaCheckbox).click();
        driver.findElement(lungCheckbox).click();

        Assert.assertFalse(driver.findElement(RheumaticCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(asthmaCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(lungCheckbox).isSelected());

        //radio
        By fourDayRadio = By.xpath("//input[@value='3-4 days']");
        By fiveCupDayRadio = By.xpath("//input[@value='5+ cups/day']");
        By twoPackRadio = By.xpath("//input[@value='1-2 packs/day']");

        driver.findElement(fourDayRadio).click();
        driver.findElement(fiveCupDayRadio).click();
        driver.findElement(twoPackRadio).click();

        Assert.assertTrue(driver.findElement(fourDayRadio).isSelected());
        Assert.assertTrue(driver.findElement(fiveCupDayRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoPackRadio).isSelected());

    }

    @Test
    public void TC_03_Select_All_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckBoxs = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : allCheckBoxs) {
            if (!checkbox.isSelected()){
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @Test
    public void TC_04_Default_Checkbox(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualzoneAirContion = By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
        driver.findElement(dualzoneAirContion).click();
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(dualzoneAirContion).isSelected());
        driver.findElement(dualzoneAirContion).click();
        Assert.assertFalse(driver.findElement(dualzoneAirContion).isSelected());


        //checkbox bi disable
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']//preceding-sibling::input")).isEnabled());
    }

    @Test
    public void TC_04_Default_RadioButton_p2(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By radioBtn = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        driver.findElement(radioBtn).click();
        sleepInSecond(1);
        if (driver.findElement(radioBtn).isSelected()){
            System.out.println("radio button da dc chon");
        } else driver.findElement(radioBtn).click();

    }

    public void sleepInSecond(long TimeInSecond){
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
