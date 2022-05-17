package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_And_Css {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String firstName, lastName, emailAddress, password;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        firstName = "Mingyu";
        lastName = "Kim";
        emailAddress = "mingyu" + generateRandomNumber() + "@gmail.com";
        password = "12345678";
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @Test
    public void TC_01_LoginWithEmptyEmailPassword(){

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("(//li[@class='first']/a[@title='My Account'])[2]")).click();
        driver.findElement(By.xpath("//button[@type='submit' and @title='Login']")).click();

        WebElement emailError = driver.findElement(By.xpath("//input[@type='email' and @title='Email Address']/following-sibling::div"));
        WebElement passwordError = driver.findElement(By.xpath("//input[@type='password' and @title='Password']/following-sibling::div"));

        Assert.assertEquals(emailError.getText(),"This is a required field.");
        Assert.assertEquals(passwordError.getText(),"This is a required field.");

    }

    @Test
    public void TC_02_LoginWithInvalidEmail(){

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("(//li[@class='first']/a[@title='My Account'])[2]")).click();

        WebElement txtEmail = driver.findElement(By.xpath("//input[@type='email' and @title='Email Address']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@type='password' and @title='Password']"));

        txtEmail.sendKeys("123434234@12312.123123");
        txtPassword.sendKeys("123456");

        driver.findElement(By.xpath("//button[@type='submit' and @title='Login']")).click();

        WebElement emailError = driver.findElement(By.xpath("//*[@id='advice-validate-email-email']"));
        Assert.assertEquals(emailError.getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_LoginWithInvalidPassword(){

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("(//li[@class='first']/a[@title='My Account'])[2]")).click();

        WebElement txtEmail = driver.findElement(By.xpath("//input[@type='email' and @title='Email Address']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@type='password' and @title='Password']"));

        txtEmail.sendKeys("automation@gmail.com");
        txtPassword.sendKeys("123");

        driver.findElement(By.xpath("//button[@type='submit' and @title='Login']")).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_LoginWithIncorrectEmailPassword(){

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("(//li[@class='first']/a[@title='My Account'])[2]")).click();

        WebElement txtEmail = driver.findElement(By.xpath("//input[@type='email' and @title='Email Address']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@type='password' and @title='Password']"));

        txtEmail.sendKeys("automation@gmail.com");
        txtPassword.sendKeys("123123123");

        driver.findElement(By.xpath("//button[@type='submit' and @title='Login']")).click();

        WebElement errorMess = driver.findElement(By.xpath("//li[@class='error-msg']//li"));

        Assert.assertEquals(errorMess.getText(),"Invalid login or password.");

    }

    @Test
    public void TC_05_CreateANewAccount(){
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("(//li[@class='first']/a[@title='My Account'])[2]")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();

        WebElement messSuccess = driver.findElement(By.xpath("//li[@class='success-msg']"));

        //so sanh tuyet doi
        Assert.assertEquals(messSuccess.getText(),"Thank you for registering with Main Website Store.");

        //so sanh tuong doi
        String contactInfoTxt = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfoTxt.contains(emailAddress));
        Assert.assertTrue(contactInfoTxt.contains(firstName));
        Assert.assertTrue(contactInfoTxt.contains(lastName));

        driver.findElement(By.xpath("(//a/span/following-sibling::span)[3]")).click();
        driver.findElement(By.xpath("//li/a[@title='Log Out']")).click();

    }

    public int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(9999);
    }

    @Test
    public void TC_06_LoginWithEmailPassword(){

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("(//li[@class='first']/a[@title='My Account'])[2]")).click();
        WebElement txtEmail = driver.findElement(By.xpath("//input[@type='email' and @title='Email Address']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@type='password' and @title='Password']"));

        txtEmail.sendKeys(emailAddress);
        txtPassword.sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit' and @title='Login']")).click();

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(emailAddress));
    }
}
