package Exercise;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_09_Textbox_TextArea_P2 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String firstName = "Luvie", lastName = "Casa", employeeID, newFirstName = "Momo", newLastName = "Kobe";

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Add_Employee(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        employeeID = driver.findElement(By.name("employeeId")).getAttribute("value");
        driver.findElement(By.id("btnSave")).click();

        WebElement txtEmpFirstName = driver.findElement(By.id("personal_txtEmpFirstName"));
        WebElement txtEmpLastName = driver.findElement(By.id("personal_txtEmpLastName"));
        WebElement txtEmployeeId = driver.findElement(By.id("personal_txtEmployeeId"));

        Assert.assertEquals(firstName, txtEmpFirstName.getAttribute("value"));
        Assert.assertEquals(lastName, txtEmpLastName.getAttribute("value"));
        Assert.assertEquals(employeeID, txtEmployeeId.getAttribute("value"));

        Assert.assertFalse(txtEmpFirstName.isEnabled());
        Assert.assertFalse(txtEmpLastName.isEnabled());
        Assert.assertFalse(txtEmployeeId.isEnabled());

    }

    @Test
    public void TC_02_Edit_Employee() throws InterruptedException {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();

        driver.findElement(By.xpath("//table[@id='resultTable']//tbody//tr//a[text()='" + employeeID + "']")).click();
        driver.findElement(By.cssSelector("input#btnSave")).click();

        WebElement txtEmpFirstName = driver.findElement(By.id("personal_txtEmpFirstName"));
        WebElement txtEmpLastName = driver.findElement(By.id("personal_txtEmpLastName"));


        txtEmpFirstName.clear();
        txtEmpFirstName.sendKeys(newFirstName);
        txtEmpLastName.clear();
        txtEmpLastName.sendKeys(newLastName);

        Assert.assertTrue(txtEmpFirstName.isEnabled());
        Assert.assertTrue(txtEmpLastName.isEnabled());

        driver.findElement(By.cssSelector("input#btnSave")).click();

        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), newFirstName);
        Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), newLastName);

        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
    }

}
