package com.Utility;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private Logger logger = LoggerUtility.getLogger(getClass());
	private WebDriverWait wait;

	public static WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
	}
	
	 public static void removeDriver() {
	        driver.remove(); // clean up ThreadLocal
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
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else if (browserName == Browser.EDGE)
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("disable-gpu");
//				System.setProperty("selenium.manager.log.level", "DEBUG");
				driver.set(new EdgeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
//				System.setProperty("selenium.manager.log.level", "DEBUG");
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}

		else if (browserName == Browser.FIREFOX)
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new FirefoxDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
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
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing click");
		element.click();
	}
	
	public void clickOnCheckBox(By locator) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing click");
		element.click();
	}


	public void clickOn(WebElement element) {

		logger.info("Element found and now performing click");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now enter text" + textToEnter);
		element.sendKeys(textToEnter);
	}

	public void clearText(By textBoxLocator) {
		logger.info("Finding element with the locator" + textBoxLocator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
//		WebElement element = driver.get().findElement(textBoxLocator);
		logger.info("Element found and now clearing text box field");
		element.clear();
	}

	public void selectFromDropdown(By dropDownLocator, String optionToSelect) {
		logger.info("Finding element with the locator" + dropDownLocator);
		WebElement element = driver.get().findElement(dropDownLocator);
		Select select = new Select(element);
		select.selectByVisibleText(optionToSelect);
		logger.info("Selecting the option " + optionToSelect);
	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now enter special key" + keyToEnter);
		element.sendKeys(keyToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Returning the visible text" + element.getText());
		return element.getText();

	}

	public String getVisibleText(WebElement element) {

		logger.info("Returning the visible text" + element.getText());
		return element.getText();

	}

	public List<String> getAllVisibleText(By locator) {
		logger.info("Finding all elements with the locator" + locator);
		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Printing the List of Elements");
		List<String> visibleTextList = new ArrayList<String>();
		for (WebElement element : elementList) {
			System.out.println(getVisibleText(element));
		}
		return visibleTextList;

	}

	public List<WebElement> getAllElements(By locator) {
		logger.info("Finding all elements with the locator" + locator);
		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Printing the List of Elements");

		return elementList;

	}

//	public String takeScreenShot(String name) {
//
//		TakesScreenshot screenShot = (TakesScreenshot) driver.get();
//		File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);
//		Date date = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
//		String timestamp = format.format(date);
//		String path = "./Screenshots/" + name + "-" + timestamp + ".png";
//		File screenShotFile = new File(path);
//
//		try {
//			FileUtils.copyFile(screenShotData, screenShotFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return path;
//	}
	
	public String takeScreenShot(String name) {

	    // Ensure the Screenshots folder exists
	    File screenshotsFolder = new File("./Screenshots/");
	    if (!screenshotsFolder.exists()) {
	        screenshotsFolder.mkdirs();
	    }

	    TakesScreenshot screenShot = (TakesScreenshot) driver.get();
	    File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);
	    Date date = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
	    String timestamp = format.format(date);
	    String path = "./Screenshots/" + name + "-" + timestamp + ".png";
	    File screenShotFile = new File(path);

	    try {
	        FileUtils.copyFile(screenShotData, screenShotFile);
	        System.out.println("Screenshot saved: " + path);  // optional confirmation
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return path;
	}


}
