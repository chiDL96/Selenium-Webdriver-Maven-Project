package Exercise;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Handle_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        //Luon khoi tao sau driver -> no can gia tri driver de khoi tao explicitWait len
        //Wait cho cac element theo dieu kien co san: visible/ invisible/ presence/ clickable/....
        explicitWait = new WebDriverWait(driver, 15);

        //Wait cho viec "tim" element: findElement/ findElements
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test
    public void TC_01(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        String parentLocator = "number-button", childLocator = "ul#number-menu>li>div", expectedTextItem = "19";
        try {
            selectItemInCustomDropdown(parentLocator, childLocator, expectedTextItem);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(driver.findElement(By.id("number-button")).getText(), expectedTextItem);
    }

    public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) throws InterruptedException {
        //Click vao dropdown
        driver.findElement(By.id(parentLocator)).click();

        Thread.sleep(Long.parseLong("1000"));
        //Cho cho tat ca cac item con ben trong duoc load ra
        //By locator phai dai dien cho tat ca cac item con
        //Lay cai locator den cai the chua text item

        //Case1: click step 1 o tren xong -> xo ra no load het luon thi k can wait nua
        //Case2: du lieu nhieu thi n load vai nghin record -> lau, mat tgian
        //Chua load ra het thi lam sao ma bam vao de chon duoc (step duoi)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        //Tim item mong muon, neu nhu k hien thi thi cuon xuong de tim -> xu dung vong lap de lay het tat ca item roi duyet qua tung cai de getText
        //Khai bao tung element thi rat la nhieu, tong cong bao nhieu cai,..
        //Do do, minh se lay het tat ca cac item va luu vao 1 List Element

        List<WebElement> allDropdownItems = driver.findElements(By.cssSelector("ul#number-menu>li>div"));

        //Duyet qua tung item
        allDropdownItems.get(0).getText();
        allDropdownItems.get(1).getText();
        allDropdownItems.get(2).getText();
        allDropdownItems.get(3).getText();

        //Duyet ngan gon bang Vong lap
        for (WebElement item : allDropdownItems) {
            String actualTextItem = item.getText();
            //Thay item can chon thi click vao -> so sanh voi item mong muon sau do click vao
            if (actualTextItem.equals(expectedTextItem)) {
                item.click();
                Thread.sleep(Long.parseLong("1000"));
                //Chua thoat ra khoi vong lap
                break;
            }
        }
    }
}
