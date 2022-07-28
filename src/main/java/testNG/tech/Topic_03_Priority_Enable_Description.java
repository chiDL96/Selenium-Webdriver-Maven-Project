package testNG.tech;

import org.testng.annotations.Test;

public class Topic_03_Priority_Enable_Description {
    @Test(priority = 1, enabled = false)
    public void B() {
        System.out.println("B");
    }

    @Test(description = "regression test")
    public void A() {
        System.out.println("A");
    }

    @Test(priority = 3)
    public void C() {
        System.out.println("C");
    }
}
