package Exercise;

import com.beust.ah.A;
import jdk.swing.interop.SwingInterOpUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test
    public void TC_01_Dispaly(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement txtMail = driver.findElement(By.id("mail"));
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

        WebElement txtEdu = driver.findElement(By.id("edu"));
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

        WebElement txtMail = driver.findElement(By.id("mail"));
        if (txtMail.isEnabled()){
            System.out.println("Email textbox is enabled");
        }
        else System.out.println("Emaiil textbox is disabled");


        WebElement ageUnder18 = driver.findElement(By.id("under_18"));
        if (ageUnder18.isEnabled()){
            System.out.println("Age under 18 radio button is enabled");
        }
        else System.out.println("Age under 18 radio button is disabled");

        WebElement txtEdu = driver.findElement(By.id("edu"));
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

        WebElement slider01 = driver.findElement(By.id("slider-1"));
        if (slider01.isEnabled()){
            System.out.println("Slider 01 is enabled");
        }
        else System.out.println("Slider 01 is disabled");

        WebElement passwordDisabled = driver.findElement(By.id("disable_password"));
        if (passwordDisabled.isEnabled()){
            System.out.println("Password is enabled");
        }
        else System.out.println("Password is disabled");

        WebElement radioButtonDisabled = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
        if (radioButtonDisabled.isEnabled()){
            System.out.println("Radio button is enabled");
        }
        else System.out.println("Radio button is disabled");

        WebElement textAreaDisabled = driver.findElement(By.xpath("//textarea[@id='bio']"));
        if (textAreaDisabled.isEnabled()){
            System.out.println("Textarea is enabled");
        }
        else System.out.println("Textarea is disabled");

        WebElement job3Disabled = driver.findElement(By.id("job3"));
        if (job3Disabled.isEnabled()){
            System.out.println("Job3 is enabled");
        }
        else System.out.println("Job3 is disabled");

        WebElement checkboxDisabled = driver.findElement(By.id("check-disbaled"));
        if (checkboxDisabled.isEnabled()){
            System.out.println("Checkbox is enabled");
        }
        else System.out.println("Checkbox is disabled");

        WebElement slider02Disabled = driver.findElement(By.id("slider-2"));
        if (slider02Disabled.isEnabled()){
            System.out.println("Slider02 is enabled");
        }
        else System.out.println("Slider02 is disabled");

    }


    @Test
    public void TC_03_Selected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement ageUnder18 = driver.findElement(By.id("under_18"));
        WebElement javaLanguage = driver.findElement(By.id("java"));
        ageUnder18.click();
        javaLanguage.click();

        Assert.assertTrue(ageUnder18.isSelected());
        Assert.assertTrue(javaLanguage.isSelected());

        if (ageUnder18.isEnabled()){
            System.out.println("Age under is selected");
        }
        else System.out.println("Age under is de-selected");

        if (javaLanguage.isEnabled()){
            System.out.println("Java Checkbox is selected");
        }
        else System.out.println("Java Checkbox is de-selected");

        javaLanguage.click();

        if (javaLanguage.isSelected()){
            System.out.println("Java Checkbox is selected");
        }
        else System.out.println("Java Checkbox is de-selected");

    }

    @Test
    public void TC_04_Mailchimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#new_username")).sendKeys("automationFC");


        WebElement password = driver.findElement(By.id("new_password"));
        WebElement signupButton = driver.findElement(By.xpath("//button[@id='create-account']"));

        //Lowercase
        password.sendKeys("auto");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());
        //Uppercase
        password.clear();
        password.sendKeys("AUTO");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());
        //Number
        password.clear();
        password.sendKeys("9");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());
        //Special char
        password.clear();
        password.sendKeys("%");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());
        //Min character
        password.clear();
        password.sendKeys("12345678");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());

        //Combine
        password.clear();
        password.sendKeys("Auto123@");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());

        //Checkbox

        WebElement checkbox = driver.findElement(By.id("marketing_newsletter"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());

    }
}
