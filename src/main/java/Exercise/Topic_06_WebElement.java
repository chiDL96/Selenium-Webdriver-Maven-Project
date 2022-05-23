package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebElement {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://kenh14.vn/");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void TC_01_Define_Element(){
        //Muon thao tac voi 1 element thi minh phai di tim element do
        //Sau do moi ap dung cac hanh vi cho element

        //Muon thao tac truc tiep len element thi k can khai bao bien
        driver.findElement(By.id("email")).click();

        //Thao tac tu 2 lan tro len thi phai khai bao bien de tranh lap lai
        WebElement txtUsername = driver.findElement(By.id("email"));
        txtUsername.clear();
        txtUsername.sendKeys("abc");
        txtUsername.submit();

    }

    @Test
    public void TC_02_Element_Method(){
        WebElement element = driver.findElement(By.id(""));

        //Xoa du lieu trong field cho phep nhap
        //Textbox/ TextArea/ Editable Dropdown
        element.clear();

        //Nhap du lieu trong nhung field cho phep nhap

        element.sendKeys("");
//        element.sendKeys(Keys.ENTER);

        //Element cha: findElement(By.classname("footer"))
        //Element tiep theo cua con: findElement(By.cssSelector("a[title='My Account']"))
        driver.findElement(By.className("footer")).findElement(By.cssSelector("a[title='My Account']"));
        driver.findElement(By.xpath("//div[@class='footer]//a[@title='My Account']"));

        String placeholderSearchbox = driver.findElement(By.id("search")).getAttribute("placeholder");
        //Search entire store here...

        Assert.assertEquals(placeholderSearchbox, "Search entire store here...");

        //GUI: Font/ Size/ Color/ Pixel/....

        element.getCssValue("background-color");

        //GUI: Position/ Location/ Size of element
        element.getSize();
        element.getLocation();
        element.getRect();

        //Framework: Attach screenshot to record
        element.getScreenshotAs(OutputType.FILE);

        element = driver.findElement(By.xpath("//div[@id='abc']"));
        String txtbox = element.getTagName();

        //Truyen 1 bien vao trong 1 chuoi
        driver.findElement(By.xpath("//" + txtbox + "[@id='abc']"));

        //Lay ra text cua element hien tai
        //Text cua nhung element con ben trong
        element.getText();

        //Mong muon 1 element hien thi/ khong hien thi
        //Hien thi: nguoi dung nhin thayy day duoc, co kich thuoc cu the (chieu rong/ chieu cao)
        //Ap dung cho tat ca cac loai element: ...
        element.isDisplayed();

        //Mong muon 1 element co the thao tac duoc len hoac ko
        //Nguoc lai voi disable
        //Thao tac duoc: Enable
        //Khong thao tac duoc: Disabled
        //Ap dung cho tat ca cac loai element

        element.isEnabled();

        //Mong muon 1 element da duoc chon hay chua
        //Ap dung voi 1 vai loai element: Checkbox/ radio button/ dropdown (default)

        element.isSelected();

        //Click vao 1 element
        //Button/ Link

        element.click();


    }
}
