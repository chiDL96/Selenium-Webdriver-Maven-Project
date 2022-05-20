package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Web_Browser {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver",projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void TC_01_Method(){
        //Dung de close browser/ tab
        //Neu chi co 1 tab: close browser
        //Neu nhu co nhieu hon 1 tab: close tab dang active
        driver.close();

        //Dung de dong browser luon
        //Khong quan tam co bao nhieu tab
        driver.quit();

        //Mo 1 URL ra
        driver.get("https://kenh14.vn/");

        //Dung de tim ra 1 Element
        driver.findElement(By.xpath(""));

        //Dung de tim nhieu element
        driver.findElements(By.xpath(""));

        //Lay ra URL cua page hien tai
        driver.getCurrentUrl();

        //Lay ra title cua page hien tai
        driver.getTitle();

        //Dung de xu ly window/ tab
        //Lay ra ID cua window/ tab dang active
        driver.getWindowHandle();

        //Lay ra ID cua tat ca cac window/ tab dang co
        driver.getWindowHandles();

        //Cookie: luu lai phien dang nhap/ session/ du lieu duyet web
        driver.manage().getCookies();

        //Framework - logs
        //driver.manage().logs().get();

        //Cho cho findElement/ findElements
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Cho cho 1 page load thanh cong
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);


        //Cho cho 1 doan code Javascript duoc thuc thi thanh cong
        //asynchronous script - bat dong bo
        //synchronous script - dong bo
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);

        //Full het toan man hinh
        driver.manage().window().fullscreen();

        //Mo rong cua so
        driver.manage().window().maximize();

        //Thuc te k dung: test GUI/ responsive (font/size/color/position/location/..)
        //Set vi tri cua browser so voi do phan giai man hinh (Resolution)
        driver.manage().window().getPosition();
        driver.manage().window().setPosition(new Point((0,0)));

        //Mo browser voi kich thuoc la bao nhieu...
        //Test responsive
        driver.manage().window().getSize();
        driver.manage().window().setSize(new Dimension(1920,1080));

        //Tracking history tot hon
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.navigate().to("https://kenh14.vn/");

        //Alert
        driver.switchTo().alert();

        //Frame/ Iframe
        driver.switchTo().frame(0)

        //Window/ tab
        driver.switchTo().window("");


    }


}
