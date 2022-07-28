package testNG.tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
    //Thu vien assert: Kiem tra tinh dung dan cua du lieu
    //Mong doi no dung/ sai/ dau vao va dau ra nhu nhau
    //Bang null/ khac null
    @Test
    public void TC_01() {
        String addressCity = "Ho Chi Minh city";

        Assert.assertTrue(addressCity.contains("city"));
        Assert.assertFalse(addressCity.contains("Ha Noi"));
        Assert.assertEquals(addressCity, "Ho Chi Minh city");
    }
}
