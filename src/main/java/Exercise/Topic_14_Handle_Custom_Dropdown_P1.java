package Exercise;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class Topic_14_Handle_Custom_Dropdown_P1 {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        //Luon khoi tao sau driver -> no can gia tri driver de khoi tao explicitWait len
        //Wait cho cac element theo dieu kien co san: visible/ invisible/ presence/ clickable/....
        explicitWait = new WebDriverWait(driver, 15);

        //Ep kieu tuong minh trong java
        jsExecutor = (JavascriptExecutor) driver;

        //Wait cho viec "tim" element: findElement/ findElements
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test
    public void TC_01_JQuery(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

//        String parentLocator = "number-button", childLocator = "ul#number-menu>li>div", expectedTextItem = "19";
        selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div","10");
        Assert.assertEquals(driver.findElement(By.id("number-button")).getText(), "10");

        selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div","19");
        Assert.assertEquals(driver.findElement(By.id("number-button")).getText(), "19");


    }

    @Test
    public void TC_02_ReactJS(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInCustomDropdown("i.dropdown", "div.item>span","Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

        selectItemInCustomDropdown("i.dropdown", "div.item>span","Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
    }

    @Test
    public void TC_03_VueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
    }

    @Test
    public void TC_04_DefaultDropdown(){
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInCustomDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option","6");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='6']")).isSelected());

        selectItemInCustomDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option","1996");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']/option[text()='1996']")).isSelected());
    }

    @Test
    public void TC_05_EditableDropdown(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInCustomDropdown("input.search", "div.item>span","Bahamas");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Bahamas");
    }

    public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem)  {
        //Click vao dropdown
        driver.findElement(By.cssSelector(parentLocator)).click();
        sleepInSecond(3);

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

        List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));

        //Duyet qua tung item
//        allDropdownItems.get(0).getText();
//        allDropdownItems.get(1).getText();
//        allDropdownItems.get(2).getText();
//        allDropdownItems.get(3).getText();

        //Duyet ngan gon bang Vong lap
        for (WebElement item : allDropdownItems) {
            String actualTextItem = item.getText();
            //Thay item can chon thi click vao -> so sanh voi item mong muon sau do click vao
            if (actualTextItem.equals(expectedTextItem)) {
//                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                item.click();
                sleepInSecond(1);
                //Chua thoat ra khoi vong lap
                break;
            }
        }
    }

    public void enterItemInCustomDropdown(String editableLocator, String childLocator, String expectedTextItem)  {

        driver.findElement(By.cssSelector(editableLocator)).sendKeys(expectedTextItem);
        sleepInSecond(3);

        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));

        for (WebElement item : allDropdownItems) {
            String actualTextItem = item.getText();
            if (actualTextItem.equals(expectedTextItem)) {
//                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }


    public void sleepInSecond(long TimeInSecond){
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
