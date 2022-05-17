package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class Topic_02_Xpath_And_Css {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void TC_01_RegisterWithEmptyData() {
        //click button Submit
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //error message element
        WebElement txtFirstnameError = driver.findElement(By.id("txtFirstname-error"));
        WebElement txtEmailError = driver.findElement(By.id("txtEmail-error"));
        WebElement txtCEmailError = driver.findElement(By.id("txtCEmail-error"));
        WebElement txtPasswordError = driver.findElement(By.id("txtPassword-error"));
        WebElement txtCPasswordError = driver.findElement(By.id("txtCPassword-error"));
        WebElement txtPhoneError = driver.findElement(By.id("txtPhone-error"));

        Assert.assertEquals(txtFirstnameError.getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(txtEmailError.getText(), "Vui lòng nhập email");
        Assert.assertEquals(txtCEmailError.getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(txtPasswordError.getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(txtCPasswordError.getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(txtPhoneError.getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_RegisterWithInvalidEmail() {

        //textbox element
        WebElement txtFirstname = driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        WebElement txtEmail = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        WebElement txtCEmail = driver.findElement(By.xpath("//input[@id='txtCEmail']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        WebElement txtCPassword = driver.findElement(By.xpath("//input[@id='txtCPassword']"));
        WebElement txtPhone = driver.findElement(By.xpath("//input[@id='txtPhone']"));

        txtFirstname.sendKeys("Mingyu");
        txtEmail.sendKeys("123@@");
        txtCEmail.sendKeys("123@@");
        txtPassword.sendKeys("12345678");
        txtCPassword.sendKeys("12345678");
        txtPhone.sendKeys("098122344452");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement txtEmailError = driver.findElement(By.id("txtEmail-error"));
        WebElement txtCEmailError = driver.findElement(By.id("txtCEmail-error"));

        Assert.assertEquals(txtEmailError.getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(txtCEmailError.getText(), "Email nhập lại không đúng");
    }


    @Test
    public void TC_03_RegisterWithIncorrectEmail() {

        WebElement txtFirstname = driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        WebElement txtEmail = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        WebElement txtCEmail = driver.findElement(By.xpath("//input[@id='txtCEmail']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        WebElement txtCPassword = driver.findElement(By.xpath("//input[@id='txtCPassword']"));
        WebElement txtPhone = driver.findElement(By.xpath("//input[@id='txtPhone']"));

        txtFirstname.sendKeys("Mingyu");
        txtEmail.sendKeys("mingyu@gmail.com");
        txtCEmail.sendKeys("123@gmail.com");
        txtPassword.sendKeys("12345678");
        txtCPassword.sendKeys("12345678");
        txtPhone.sendKeys("098122344452");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement txtCEmailError = driver.findElement(By.id("txtCEmail-error"));

        Assert.assertEquals(txtCEmailError.getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_RegisterWithInvalidPassword() {

        WebElement txtFirstname = driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        WebElement txtEmail = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        WebElement txtCEmail = driver.findElement(By.xpath("//input[@id='txtCEmail']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        WebElement txtCPassword = driver.findElement(By.xpath("//input[@id='txtCPassword']"));

        txtFirstname.sendKeys("Mingyu");
        txtEmail.sendKeys("mingyu@gmail.com");
        txtCEmail.sendKeys("mingyu@gmail.com");
        txtPassword.sendKeys("12345");
        txtCPassword.sendKeys("12345");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement txtPasswordError = driver.findElement(By.id("txtPassword-error"));
        WebElement txtCPasswordError = driver.findElement(By.id("txtCPassword-error"));

        Assert.assertEquals(txtPasswordError.getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(txtCPasswordError.getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void TC_05_RegisterWithIncorrectConfirmPassword() {

        WebElement txtFirstname = driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        WebElement txtEmail = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        WebElement txtCEmail = driver.findElement(By.xpath("//input[@id='txtCEmail']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        WebElement txtCPassword = driver.findElement(By.xpath("//input[@id='txtCPassword']"));

        txtFirstname.sendKeys("Mingyu");
        txtEmail.sendKeys("mingyu@gmail.com");
        txtCEmail.sendKeys("mingyu@gmail.com");
        txtPassword.sendKeys("123456");
        txtCPassword.sendKeys("654321");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement txtCPasswordError = driver.findElement(By.id("txtCPassword-error"));

        Assert.assertEquals(txtCPasswordError.getText(), "Mật khẩu bạn nhập không khớp");


    }

    @Test
    public void TC_06_RegisterWithInvalidPhoneNumber() {

        WebElement txtFirstname = driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        WebElement txtEmail = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        WebElement txtCEmail = driver.findElement(By.xpath("//input[@id='txtCEmail']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        WebElement txtCPassword = driver.findElement(By.xpath("//input[@id='txtCPassword']"));
        WebElement txtPhone = driver.findElement(By.xpath("//input[@id='txtPhone']"));

        txtFirstname.sendKeys("Mingyu");
        txtEmail.sendKeys("mingyu@gmail.com");
        txtCEmail.sendKeys("mingyu@gmail.com");
        txtPassword.sendKeys("123456");
        txtCPassword.sendKeys("654321");
        txtPhone.sendKeys("098111129");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement txtPhoneError = driver.findElement(By.id("txtPhone-error"));
        Assert.assertEquals(txtPhoneError.getText(), "Số điện thoại phải từ 10-11 số.");

        txtPhone.clear();
        txtPhone.sendKeys("12345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(txtPhoneError.getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");

    }
}


