package Tests;

import base.setup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ApiDemo;

public class ApiDemoTest extends setup {

    ApiDemo apidemo;

    @Test(priority = 1)
    public void validateButtonTest() {
        
        apidemo = new ApiDemo(driver);
        Log.info("First Test started");
        Assert.assertTrue(apidemo.validatebutton());
    }

    @Test(priority=2)
    public void validateText()
    {
        Log.info("Second test started");
    }
}
