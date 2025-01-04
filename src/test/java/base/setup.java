package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class setup {

    public Logger Log = LogManager.getLogger(this.getClass());
    public static AndroidDriver driver;
    public static ThreadLocal<AndroidDriver> tldriver = new ThreadLocal<AndroidDriver>();
    @BeforeMethod
    public void start() throws InterruptedException, MalformedURLException {
        Log.info("this is start of my project");
        // this helpd top run the appium with out manual interventions
//        serviceBuilder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(200))
//                .build().start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        //options.setApp("/Users/UTKARSH/OneDrive/Documents/Appium/android-app.apk");
        options.setDeviceName("eb9a2293");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.ApiDemos");
        options.setAppWaitForLaunch(true);
        options.setIgnoreHiddenApiPolicyError(true);
        options.setAppWaitActivity("*");
        options.setAppWaitDuration(Duration.ofMillis(50000));
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        //tldriver.set(driver);
        Thread.sleep(3000);

    }

//    public static AndroidDriver getDriver()
//    {
//        return tldriver.get();
//    }

    @AfterMethod
    public void teardown() {
        Log.info("this is teardown of my project");
        driver.quit();
//        serviceBuilder.build().stop();
    }

    public static String getScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);// temp dir
        String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
                + ".png";
        File destination = new File(path);
        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String abpath = destination.getAbsolutePath();
        System.out.println(abpath);
        return abpath;
    }


}
