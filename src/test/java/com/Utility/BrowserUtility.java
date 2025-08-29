package com.Utility;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

//	public BrowserUtility(String browserName) {
//		logger.info("Launching browser" + browserName);
//		if (browserName.equalsIgnoreCase("chrome")) {
//			driver.set(new ChromeDriver());
//		} else if (browserName.equalsIgnoreCase("edge")) {
//			driver.set(new EdgeDriver());
//		}
//
//		else {
//			logger.info("Invalid Browser Name...Please enter chrome or edge");
//			System.err.println("Invalid Browser Name...Please enter chrome or edge");
//		}
//	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser" + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
//				options.addArguments("--headless=old");
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE)
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("disable-gpu");
//				System.setProperty("selenium.manager.log.level", "DEBUG");
				driver.set(new EdgeDriver(options));
			} else {
//				System.setProperty("selenium.manager.log.level", "DEBUG");
				driver.set(new EdgeDriver());
			}

		else if (browserName == Browser.FIREFOX)
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing click");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now enter text" + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Returning the visible text" + element.getText());
		return element.getText();

	}

	public String takeScreenShot(String name) {

		TakesScreenshot screenShot = (TakesScreenshot) driver.get();
		File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timestamp = format.format(date);
		String path = "./Screenshots/" + name + "-" + timestamp + ".png";
		File screenShotFile = new File(path);

		try {
			FileUtils.copyFile(screenShotData, screenShotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
