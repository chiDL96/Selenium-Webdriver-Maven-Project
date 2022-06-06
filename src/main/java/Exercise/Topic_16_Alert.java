package Exercise;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class Topic_16_Alert {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Alert alert;

    @BeforeClass

    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void TC_01_Accept_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(3);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(3);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(3);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String text = "AutomationFC";
        alert.sendKeys(text);

        sleepInSecond(3);

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + text);
    }

    @Test
    public void  TC_04_Authentication_Alert_Trick(){
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        String username = "admin";
        String password = "admin";
        String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";

        driver.get(url);

        //So sanh tuyet doi
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(), "Congratulations! You must have the proper credentials.");
        //So sanh tuong doi
        Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations"));
    }

    @Test
    public void TC_05_Authentication_Alert_Navigate_Form_Other_Page(){
        String username = "admin";
        String password = "admin";

        driver.get("http://the-internet.herokuapp.com/");

        String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        String[] authenLinkArray = basicAuthenLink.split("//");
        //http
        //the-internet.herokuapp.com/basic_auth
        String newAuthenLink = authenLinkArray[0] + "//" +  username + ":" + password + "@" + authenLinkArray[1];

        driver.get(newAuthenLink);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations"));
    }
    public void sleepInSecond(long TimeInSecond){
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
