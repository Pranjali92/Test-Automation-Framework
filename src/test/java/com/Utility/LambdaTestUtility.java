package com.Utility;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

	public static WebDriver initializeLambdaSession(String browser, String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("browserVersion", "latest");
		Map<String, Object> ltOptions = new HashMap();
		ltOptions.put("user", "pranjalinirmal92");
		ltOptions.put("accessKey", "LT_OtkLvWFJCboqyD2rfvbr6Wrgsc8p3IexVw2qeEmpYEJimlU");
		ltOptions.put("build", "Selenium 4");
		ltOptions.put("name", testName);
		ltOptions.put("platformName", "Windows 10");
		ltOptions.put("seCdp", true);
		ltOptions.put("selenium_version", "4.23.0");
		capabilities.setCapability("LT:Options", ltOptions);
		capabilitiesLocal.set(capabilities);
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
		} catch (Exception e) {
			throw new RuntimeException("❌ Failed to initialize LambdaTest session", e);
		}
		driverLocal.set(driver);
		return driverLocal.get();
	}

	public static void quitSession() {
		if (driverLocal.get() != null) {
			driverLocal.get().quit();
		}
	}

}
