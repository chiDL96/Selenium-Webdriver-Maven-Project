package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Topic_26_Wait_Element_Status {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @Test
    public void TC_01_Visible() {
        //Co tren UI va co trong DOM/HTML
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@id='email']"))));
    }

    @Test
    public void TC_02_Invisible_In_Dom() {
        driver.get("https://www.facebook.com/");
        //Ko co  tren   UI,  co trong DOM/HTML  (k  bat buoc)
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
        //Tim trong 1s
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
    }

    @Test
    public void TC_03_Invisible_Not_In_DOM() {
        driver.get("https://www.facebook.com/");
        //Ko  co tren UI, k co trong DOM (k  bat buoc)
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        //Tim  trong 15s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
        //Tim het  5s (anh huong cua implicitWait)
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
    }

    @Test
    public void TC_04_Presence() {
        driver.get("https://www.facebook.com/");
        //Co trong DOM, va co  tren  UI -> cung pass
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));

        //Co trong DOM, va k co tren UI -> cung pass
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    }

    @Test
    public void TC_05_Staleness() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        //Element dag co trong DOM
        WebElement confirmEmailAdressTxtbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        //Closed popup de element mat khoi DOM
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        //Wait confirmEmailAdressTxtbox k con trong DOM
        //Vi 1 li do nao do ma minh muon wait no k con trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailAdressTxtbox));
    }
}

