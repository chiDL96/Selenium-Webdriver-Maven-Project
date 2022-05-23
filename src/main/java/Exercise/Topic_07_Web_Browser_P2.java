package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Web_Browser_P2 {

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
        driver.close();
    }

    @Test
    public void TC_01_Verify_URL() throws InterruptedException {

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title() throws InterruptedException {

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate_Function(){

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }

    @Test
    public void TC_04_Page_Source(){
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        String loginPageSource = driver.getPageSource();
        Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        String createAccountPageSource = driver.getPageSource();
        Assert.assertTrue(createAccountPageSource.contains("Create an Account"));
    }
}
