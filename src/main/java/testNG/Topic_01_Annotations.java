package testNG;

import org.testng.annotations.*;

public class Topic_01_Annotations {

    @Test(groups = "user")
    public void TC_01_Register() {
        System.out.println("Register Function");
    }

    @Test(groups = "user")
    public void TC_02_Login() {
        System.out.println("Login Function");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After method");
    }

    @BeforeGroups
    public void beforeGroup() {
        System.out.println("Before group");
    }

    @AfterGroups
    public void afterGroup() {
        System.out.println("After group");
    }

    @BeforeSuite
    public void beforeSuit() {
        System.out.println("Before suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After test");
    }
}
