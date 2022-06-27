package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.EclipseInterface;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_23_JavascriptExecutor {
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String emailAdressTest = "JoeMoe" + generateNumber() + "@gmail.com";

    String emailAdress, userID, password, customerName, genderOutput, passwordTest;
    String dateOfBirthInput, dateOfBirthOutput, addressInput, adressOutput, city, state, pinNumber, phoneNumber;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();

        emailAdress = "JoeMoe" + generateNumber() + "@gmail.com";
        passwordTest = "12345678";
        customerName = "JoeMoe";
        genderOutput = "female";
        dateOfBirthInput = "06/09/1966";
        dateOfBirthOutput = "1966-06-09";
        addressInput = "34 ChinaTower Hong Kong";
        adressOutput = "34 ChinaTower Hong Kong";
        city = "Hong Kong";
        state = "HK";
        pinNumber = "13" + generateNumber();
        phoneNumber = "92029" + generateNumber();

        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void Example(){

        //Selenium: WebElement click
        driver.findElement(By.xpath(" ")).click();

        //Selenium: Actions click

        //Selenium: Javascript click

    }

    @Test
    public void TC_01_JavascriptExecutor() {
//        driver.get("http://live.techpanda.org/");
        //Click WebElement
        //driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();

        //Click jsExecutor
//        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
        navigateToUrlByJS("http://live.techpanda.org/");
        String homePageURL = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(homePageURL, "live.techpanda.org");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(3);
        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div//button");
        sleepInSecond(3);
        String shoppingCard = getInnerText();
        Assert.assertTrue(shoppingCard.contains("Samsung Galaxy was added to your shopping cart."));
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSecond(3);
        String customerPageTitle = (String) executeForBrowser("return document.title;");
        Assert.assertEquals(customerPageTitle, "Customer Service");
        scrollToElementOnDown("//input[@id='newsletter']");
        sleepInSecond(3);
        sendkeyToElementByJS("//input[@id='newsletter']", emailAdressTest);
        sleepInSecond(3);
        clickToElementByJS("//button[@title='Subscribe']");
        Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://demo.guru99.com/v4/");
        sleepInSecond(5);
        String guruPageURL = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(guruPageURL, "demo.guru99.com");
    }

    @Test
    public void TC_02_Verify_HTML5_Validation_Message_P1() {
        navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
        clickToElementByJS("//input[@name='submit-btn']");
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='fname']", "Dam Dao");
        clickToElementByJS("//input[@name='submit-btn']");
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='pass']", "12345678");
        clickToElementByJS("//input[@name='submit-btn']");
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='em']", "!@!@567");
        clickToElementByJS("//input[@name='submit-btn']");
        sleepInSecond(3);
    }


    @Test
    public void TC_03_Verify_HTML5_Validation_Message_P2() {
        navigateToUrlByJS("https://www.pexels.com/vi-vn/join-contributor/");
        By txtboxUserName = By.id("user_first_name");
        By txtboxEmail = By.id("user_email");
        By btnRegister = By.xpath("//button[contains(text(), 'Tạo tài khoản mới')]");

        driver.findElement(btnRegister).click();
        sleepInSecond(3);

        Assert.assertEquals(getElementValidationMessageBy(txtboxUserName), "Please fill out this field.");
        sleepInSecond(3);

        driver.findElement(txtboxUserName).sendKeys("automation");
        driver.findElement(btnRegister).click();
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessageBy(txtboxEmail), "Please fill out this field.");

    }

    @Test
    public void TC_04__Verify_HTML5_Validation_Message_P3() {
        navigateToUrlByJS("https://warranty.rode.com/");
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");
    }

    @Test
    public void TC_05_Remove_Attribute() {
        driver.get("http://demo.guru99.com/v4");
        driver.findElement(By.xpath("//a[text()='here']")).click();
        WebElement txtboxEmail = driver.findElement(By.name("emailid"));
        txtboxEmail.sendKeys(emailAdress);
        driver.findElement(By.name("btnLogin")).click();
        userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

        driver.get("http://demo.guru99.com/v4");
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);

        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        driver.findElement(By.name("name")).sendKeys(customerName);
        driver.findElement(By.xpath("//input[@value='f']")).click();

        //Remove attribute
        removeAttributeInDOM("//input[@id='dob']", "type");
        sleepInSecond(4);
        driver.findElement(By.id("dob")).sendKeys(dateOfBirthInput);

        driver.findElement(By.name("addr")).sendKeys(addressInput);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pinNumber);
        driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
        driver.findElement(By.name("emailid")).sendKeys(emailAdress);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("sub")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[5]/td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[6]/td[text()='Gender']/following-sibling::td")).getText(), genderOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[7]/td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirthOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[8]/td[text()='Address']/following-sibling::td")).getText(), adressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[9]/td[text()='City']/following-sibling::td")).getText(), city);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[10]/td[text()='State']/following-sibling::td")).getText(), state);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[11]/td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[12]/td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[13]/td[text()='Email']/following-sibling::td")).getText(), emailAdress);

    }


    @Test
    public void TC_06_Image_Loaded() {
        navigateToUrlByJS("https://automationfc.github.io/basic-form/");
        //Check image k load dc thi tra false
        Assert.assertFalse(isImageLoaded("//img[@alt='broken_01']"));
    }

    @Test
    public void TC_07_Create_An_Account() {
        navigateToUrlByJS("http://live.techpanda.org/");
        clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
        clickToElementByJS("//a[@title='Create an Account']");

        sendkeyToElementByJS("//input[@id='firstname']", customerName);
        sendkeyToElementByJS("//input[@id='lastname']", customerName + "ABC");
        sendkeyToElementByJS("//input[@id='email_address']", emailAdressTest);
        sendkeyToElementByJS("//input[@id='password']", passwordTest);
        sendkeyToElementByJS("//input[@id='confirmation']", passwordTest);

        clickToElementByJS("// button[@title='Register']");

        Assert.assertTrue(areExpectedTextInInnerText("Thank you for registering with Main Website Store."));

        clickToElementByJS("//a[text()='Log Out']");
        sleepInSecond(10);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");
    }

    public void sleepInSecond(long TimeInSecond) {
        try {
            Thread.sleep(TimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int generateNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public String getElementValidationMessageBy(By byLocator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(byLocator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
