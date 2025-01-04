package utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class CommonUtils {

	private static Properties prop = new Properties();
	public static int EXPLICIT_WAIT_TIME;
	public static int IMPLICIT_WAIT_TIME;
	public static int DEFAULT_WAIT_TIME;
	public static String APPLICATION_NAME;
	public static String BASE_PKG;
	public static String APP_ACTIVITY;
	public static String APP_PASSWORD;
	private static String APPIUM_PORT;
	public static String AUTOMATION_INSTRUMENTATION;
	public static String BROWSER_NAME;
	public static String PLATFORM_NAME;
	public static String NEW_COMMAND_TIMEOUT;
	public static String PLATFORM_VERSION;
	public static String DEVICE_READY_TIMEOUT;
	public static String DEVICE_NAME;
	public static String APP;
	public static String UDID;
	private static DesiredCapabilities capabilities = new DesiredCapabilities();
	private static URL serverUrl;
	private static AppiumDriver driver;
	public static AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();


	public static void loadConfigProp(String propertyFileName) throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/CommonFiles/" + propertyFileName);
		prop.load(fis);

		EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait"));
		IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait"));
		DEFAULT_WAIT_TIME = Integer.parseInt(prop.getProperty("default.wait"));
		APPLICATION_NAME = prop.getProperty("application.path");
		BASE_PKG = prop.getProperty("base.pkg");
		APP_ACTIVITY = prop.getProperty("application.activity");
		APPIUM_PORT = prop.getProperty("appium.server.port");
		AUTOMATION_INSTRUMENTATION = prop.getProperty("automation.instumentation");
		DEVICE_NAME = prop.getProperty("device.name");
		BROWSER_NAME = prop.getProperty("browser.name");
		PLATFORM_NAME = prop.getProperty("platform.name");
		PLATFORM_VERSION = prop.getProperty("platform.version");
		NEW_COMMAND_TIMEOUT = prop.getProperty("new.command.timeout");
		DEVICE_READY_TIMEOUT = prop.getProperty("device.ready.timeout");

	}

	public static void setCapabilities() {
//		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, CommonUtils.BROWSER_NAME);
//		capabilities.setCapability("appium:platformVersion", CommonUtils.PLATFORM_VERSION);
		capabilities.setCapability("platformName", CommonUtils.PLATFORM_NAME);
		capabilities.setCapability("appium:deviceName","eb9a2293");
		capabilities.setCapability("appium:automationName", "UIAutomator2");
		capabilities.setCapability("appium:newCommandTimeout", CommonUtils.NEW_COMMAND_TIMEOUT);
		capabilities.setCapability("appium:appActivity", CommonUtils.APP_ACTIVITY);
		capabilities.setCapability("appium:appPackage", CommonUtils.BASE_PKG);
		capabilities.setCapability("appium:appWaitForLaunch",true);
		capabilities.setCapability("appium:appWaitActivity","*");
		capabilities.setCapability("appium:appWaitDuration",50000);
	}

	public static AppiumDriver getDriver() throws MalformedURLException {
//		serviceBuilder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(200))
//				.build().start();
	//	serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
		serverUrl = new URL("http://127.0.0.1:4723");
		driver = new AndroidDriver(serverUrl, capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	
	
	public static void loadIOSConfigProp(String propertyFileName) throws IOException
	 {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/"+propertyFileName);
		prop.load(fis);
		
		EXPLICIT_WAIT_TIME = Integer
				.parseInt(prop.getProperty("explicit.wait"));
		IMPLICIT_WAIT_TIME = Integer
				.parseInt(prop.getProperty("implicit.wait"));
		DEFAULT_WAIT_TIME = Integer.parseInt(prop.getProperty("default.wait"));
		//APPLICATION_NAME = prop.getProperty("application.path");
		UDID = prop.getProperty("udid");
		APP = prop.getProperty("application.app");
		APPIUM_PORT = prop.getProperty("appium.server.port");
		AUTOMATION_INSTRUMENTATION=prop.getProperty("automation.instumentation");
		DEVICE_NAME=prop.getProperty("device.name");
		BROWSER_NAME=prop.getProperty("browser.name");
		PLATFORM_NAME=prop.getProperty("platform.name");
		PLATFORM_VERSION=prop.getProperty("platform.version");
		NEW_COMMAND_TIMEOUT=prop.getProperty("new.command.timeout");
		DEVICE_READY_TIMEOUT=prop.getProperty("device.ready.timeout");

}
	public static void setIOSCapabilities() {
		capabilities.setCapability("browserName",
				CommonUtils.BROWSER_NAME);
		capabilities.setCapability("browserVersion",
				CommonUtils.PLATFORM_VERSION);
		capabilities.setCapability("platformName",
				CommonUtils.PLATFORM_NAME);
		capabilities.setCapability("appium:deviceName",
				CommonUtils.DEVICE_NAME);

		capabilities.setCapability("appium:newCommandTimeout",
				CommonUtils.NEW_COMMAND_TIMEOUT);

		capabilities.setCapability("appium:deviceName",
				CommonUtils.UDID);
	}
	
	public static AppiumDriver getIOSDriver() throws MalformedURLException {
		serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");		
		driver = new IOSDriver(serverUrl, capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

}
