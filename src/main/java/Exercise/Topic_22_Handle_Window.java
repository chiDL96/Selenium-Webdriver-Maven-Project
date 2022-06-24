package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_22_Handle_Window {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Naukri_Tab_ID() {
        //Link A
        driver.get("https://www.naukri.com/");
        String homePageID = driver.getWindowHandle();
        System.out.println(homePageID);
        //Click vao Link B
        driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
        //Switch sang link B
        switchToWindowByID(homePageID);

        System.out.println(driver.getCurrentUrl());

        //Switch ve Link A
        driver.switchTo().window(homePageID);
    }

    @Test
    public void TC_02_Naukri_Tab_Title() {
        //Link A
        driver.get("https://www.naukri.com/");

        //Click vao Link B
        driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
        sleepInSecond(3);
        System.out.println("naukri.com: " + driver.getCurrentUrl());

        switchToWindowByLink("browse-jobs");
        System.out.println("browse-jobs: " + driver.getCurrentUrl());

        //Switch ve lai A
        switchToWindowByTitle("Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com");
        System.out.println("naukri.com: " + driver.getCurrentUrl());
        sleepInSecond(3);

        //Switch sang C (Register)
        driver.findElement(By.xpath("//div[text()='Register']")).click();
        sleepInSecond(3);

        switchToWindowByLink("/registration/createAccount");
        System.out.println("registration: " + driver.getCurrentUrl());

        //Switch ve lai A
        switchToWindowByTitle("Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com");
        System.out.println("naukri.com: " + driver.getCurrentUrl());
    }

    @Test
    public void TC_03_Cambridge_Dictionary_Tab_Title() {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
        sleepInSecond(3);
        switchToWindowByTitle("Login");
        driver.findElement(By.xpath("//div[@id='login_content']//input[@value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']/following-sibling::span")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']/following-sibling::span")).getText(), "This field is required");

        driver.findElement(By.xpath("//div[@id='login']//input[@name='username']")).sendKeys("automationfc.com@gmail.com");
        driver.findElement(By.xpath("//div[@id='login']//input[@name='password']")).sendKeys("Automation000***");
        sleepInSecond(5);
        driver.findElement(By.xpath("//div[@id='login_content']//input[@value='Log in']")).click();
        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");

        sleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("header#header span.cdo-username")).getText(), "Automation FC");
    }

    @Test
    public void TC_04_AutomationFC() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String automationParentPage = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        switchToWindowByLink("https://www.google.com.vn/");
        Assert.assertEquals(driver.getTitle(), "Google");
        sleepInSecond(3);

        switchToWindowByID(automationParentPage);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        switchToWindowByLink("https://www.facebook.com/");
        Assert.assertTrue(driver.getTitle().contains("Facebook"));

        switchToWindowByID(automationParentPage);

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        switchToWindowByLink("https://tiki.vn/");
        Assert.assertTrue(driver.getTitle().contains("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh"));

        closeAllWindowWithoutParent(automationParentPage);
        driver.switchTo().window(automationParentPage);
        Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
    }

    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToWindowByID(String currentWindowId) {
        Set<String> allWindowId = driver.getWindowHandles();
        for (String id : allWindowId) {
            if (id.equals(currentWindowId)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void switchToWindowByTitle(String expectedTitle) {
        Set<String> allWindowId = driver.getWindowHandles();
        for (String id : allWindowId) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void switchToWindowByLink(String expectedRelativeLink) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualLink = driver.getCurrentUrl();
            if (actualLink.contains(expectedRelativeLink)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(String parentWindowId) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentWindowId)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentWindowId);
        }
    }

}
