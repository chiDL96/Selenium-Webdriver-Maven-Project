package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_Handle_Default_Dropdown_P1 {
    WebDriver driver;
    Select select;
    String firstName, lastName, day, month, year, companyName, emailAdress, password;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        firstName = "Mio";
        lastName = "Sakura";
        day = "6";
        month = "September";
        year = "1996";
        companyName = "LoveM";
        emailAdress = "sakura" + generateNumber() + "@gmail.com";
        password = "12345678";

    }

    @Test
    public void TC_01_NopEcommerce(){
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        driver.findElement(By.cssSelector("#gender-female")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        //Day
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        select.selectByVisibleText(day);

        //Kiem tra da duoc chon hay chua
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "6");

        //Kiem tra 1 dropdown co phai la multiple hay k
        Assert.assertFalse(select.isMultiple());

        //Month
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText(month);

        //Year
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText(year);

        driver.findElement(By.id("Email")).sendKeys(emailAdress);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(), "Your registration completed" );

        driver.findElement(By.className("ico-account")).click();

        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
    }

    public int generateNumber(){
        Random random = new Random();
        return random.nextInt(9999);
    }

}
