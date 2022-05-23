package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_08_Web_Element_P2 {
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
    public void TC_01_Dispaly(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement txtMail = driver.findElement(By.xpath("//input[@id='mail']"));
        if (txtMail.isDisplayed()){
            txtMail.sendKeys("Automation testing");
            System.out.println("Email textbox is displayed");
        }
        else System.out.println("Emaiil textbox is not displayed");


        WebElement ageUnder18 = driver.findElement(By.xpath("//label[@for='under_18']"));
        if (ageUnder18.isDisplayed()){
            ageUnder18.click();
            System.out.println("Age under 18 radio button is displayed");
        }
        else System.out.println("Age under 18 radio button is not displayed");

        WebElement txtEdu = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if (txtEdu.isDisplayed()){
            txtEdu.sendKeys("Automation testing");
            System.out.println("Education textarea is displayed");
        }
        else System.out.println("Education textarea is not displayed");


        WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (user5Text.isDisplayed()){
            System.out.println("User 5 is displayed");
        }
        else System.out.println("User 5 Text is not displayed");
    }

    @Test
    public void TC_02_Enable(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement txtMail = driver.findElement(By.xpath("//input[@id='mail']"));
        if (txtMail.isEnabled()){
            System.out.println("Email textbox is enabled");
        }
        else System.out.println("Emaiil textbox is disabled");


        WebElement ageUnder18 = driver.findElement(By.xpath("//label[@for='under_18']"));
        if (ageUnder18.isEnabled()){
            System.out.println("Age under 18 radio button is enabled");
        }
        else System.out.println("Age under 18 radio button is disabled");

        WebElement txtEdu = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if (txtEdu.isEnabled()){
            System.out.println("Education textarea is enabled");
        }
        else System.out.println("Education textarea is disabled");

        WebElement job1Select = driver.findElement(By.xpath("//select[@id='job1']"));
        if (job1Select.isEnabled()){
            System.out.println("Job1 is enabled");
        }
        else System.out.println("Job1 is disabled");

        WebElement job2Select = driver.findElement(By.xpath("//select[@id='job2']"));
        if (job2Select.isEnabled()){
            System.out.println("Job2 is enabled");
        }
        else System.out.println("Job2 is disabled");

        WebElement developmentCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
        if (developmentCheckbox.isEnabled()){
            System.out.println("Development checkbox is enabled");
        }
        else System.out.println("Development checkbox is disabled");

        
    }


    @Test
    public void TC_03_Selected(){

    }

    @Test
    public void TC_04_MailChimp(){

    }
}
