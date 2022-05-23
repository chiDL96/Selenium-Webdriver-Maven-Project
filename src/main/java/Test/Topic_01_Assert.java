package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
    WebDriver driver;

    @Test
    public void TC_01(){
        //3 ham assert hay dung
        //Kiem tra tinh dung dan cua du lieu

        //1. Kiem tra du lieu minh mong muon la DUNG
        //Email textbox hien thi

        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());

        //2. Kiem tra du lieu minh mong muon la SAI
        //Email textbox k hien thi

        Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());

        //3. Kiem tra du lieu mong muon va du lieu thuc te BANG NHAU

        Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
    }
}
