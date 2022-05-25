package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Textbox_TextArea {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAdress, userID, password, customerName, genderOutput;
    String dateOfBirthInput, dateOfBirthOutput, addressInput, adressOutput, city, state, pinNumber, phoneNumber;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        emailAdress = "JoeMoe" + generateNumber() + "@gmail.com";
        customerName = "JoeMoe";
        genderOutput = "female";
        dateOfBirthInput = "06/09/1966";
        dateOfBirthOutput = "1966-06-09";
        addressInput = "34st ChinaTower, Hong Kong";
        adressOutput = "34st ChinaTower, Hong Kong";
        city = "Hong Kong";
        state = "HK";
        pinNumber = "143255";
        phoneNumber = "0984737282";

    }

//    @AfterClass
//    public void afterClass(){
//        driver.close();
//    }

    @Test
    public void TC_01_Register(){
        driver.get("http://demo.guru99.com/v4");

        driver.findElement(By.xpath("//a[text()='here']")).click();

        WebElement txtboxEmail = driver.findElement(By.name("emailid"));
        txtboxEmail.sendKeys(emailAdress);

        driver.findElement(By.name("btnLogin")).click();

        userID =  driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

    }

    @Test
    public void TC_02_Login(){
        driver.get("http://demo.guru99.com/v4");

        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("btnLogin")).click();


        Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(),"Manger Id : " + userID);
    }

    @Test
    public void TC_03_Create_New_Customer(){
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        driver.findElement(By.name("name")).sendKeys(customerName);
        driver.findElement(By.xpath("//input[@value='f']")).click();
        driver.findElement(By.id("dob")).sendKeys(dateOfBirthInput);
        driver.findElement(By.id("addr")).sendKeys(addressInput);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pinNumber);
        driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
        driver.findElement(By.name("emailid")).sendKeys(emailAdress);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("sub")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[5]/td[text()='Customer Name']/following-sibling::td")), customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[6]/td[text()='Gender']/following-sibling::td")), genderOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[7]/td[text()='Birthdate']/following-sibling::td")), dateOfBirthOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[8]/td[text()='Address']/following-sibling::td")), adressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[9]/td[text()='City']/following-sibling::td")), city);


    }

    public int generateNumber(){
        Random random = new Random();
        return random.nextInt(9999);
    }
}
