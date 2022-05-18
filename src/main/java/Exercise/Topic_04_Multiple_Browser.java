package Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Multiple_Browser {


    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @Test
    public void TC_01_Chrome(){

        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://kenh14.vn/");
        driver.close();
    }

    @Test
    public void TC_02_Firefox(){
        System.setProperty("webdriver.gecko.driver", projectPath + "/BrowserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://kenh14.vn/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.close();
    }

    @Test
    public void TC_03_EdgeChromium(){
        System.setProperty("webdriver.edge.driver", projectPath + "/BrowserDrivers/msedgedriver");
        driver = new EdgeDriver();
        driver.get("https://kenh14.vn/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.close();
    }

}
