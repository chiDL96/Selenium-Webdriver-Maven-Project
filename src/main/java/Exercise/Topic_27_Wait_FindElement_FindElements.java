package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_27_Wait_FindElement_FindElements {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @Test
    public void TC_01_Find_Element() {
        //Co duy nhat 1 element
        //Neu nhu element xuat hien ngay -> tra ve element do
        //Neu nhu element chua  xuat hien thi cu moi 0.5s tim lai element cho den khi nao het tgian timeout cua implicitWait thi thoi
        //Tra ve duy nhat 1 element
        System.out.println("Start time " + getCurrentTime());
        driver.findElement(By.xpath("//input[@id='email']"));
        System.out.println("End time " + getCurrentTime());

        //Khong co element nao
        //No se tim di tim lai cho den khi het timeout
        //Sau khi het timeout thi danh fail ca testcase nay
        //K chay cac step con lai
        //Throw/ Log ra 1 exception (Ngoai le): No such element
        System.out.println("Start time " + getCurrentTime());
        driver.findElement(By.xpath("//input[@id='automation']"));
        System.out.println("End time " + getCurrentTime());

        //Co nhieu hon 1 element
        //Lay ra element dau tien de thao tac
        driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
    }

    @Test
    public void TC_02_Find_Elements() {
        int elementNumber = 0;
        //Co duy nhat 1 element  - //Co nhieu hon 1 element
        //Neu nhu element xuat hien ngay -> tra ve element do k can cho het timeout
        //Neu nhu element  chua xuat hien -> cu moi 0.5s  se tim lai cho  den khi het timeout thi thoi
        elementNumber = driver.findElements(By.xpath("//input[@id='email']")).size();
        System.out.println("1 element " + elementNumber);

        elementNumber = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
        System.out.println("N element " + elementNumber);
        //Khong co element nao
        //No se tim di tim lai cho den khi het timeout
        //Sau khi hêt timeout k danh fail step nay
        //Van chay tiep cac step tiep theo
        System.out.println("Start time " + getCurrentTime());
        driver.findElements(By.xpath("//input[@id='automation']"));
        System.out.println("End time " + getCurrentTime());
    }



    public String getCurrentTime() {
        Date date = new Date();
        return date.toString();
    }
}
