package com.Utility;

import java.io.File;
import java.io.IOException;
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

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
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

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching browser: " + browserName);
        if (browserName == Browser.CHROME) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
            }
            driver.set(new ChromeDriver(options));
        } else if (browserName == Browser.EDGE) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new", "disable-gpu");
            }
            driver.set(new EdgeDriver(options));
        } else if (browserName == Browser.FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless=old");
            }
            driver.set(new FirefoxDriver(options));
        } else {
            logger.error("Invalid Browser Name. Please use Chrome, Edge, or Firefox.");
            throw new IllegalArgumentException("Invalid browser: " + browserName);
        }

        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }

    public void goToWebsite(String url) {
        logger.info("Navigating to website: " + url);
        driver.get().get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing browser window");
        driver.get().manage().window().maximize();
    }

    // Click methods
    public void clickOn(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click element: " + locator, e);
            throw e;
        }
    }

    public void clickOnCheckBox(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.click();
            logger.info("Clicked on checkbox: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click checkbox: " + locator, e);
            throw e;
        }
    }

    public void clickOn(WebElement element) {
        try {
            element.click();
            logger.info("Clicked on WebElement");
        } catch (Exception e) {
            logger.error("Failed to click WebElement", e);
            throw e;
        }
    }

    // Text input methods
    public void enterText(By locator, String textToEnter) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(textToEnter);
            logger.info("Entered text '" + textToEnter + "' into element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to enter text into element: " + locator, e);
            throw e;
        }
    }

    public void clearText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            logger.info("Cleared text from element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to clear text from element: " + locator, e);
            throw e;
        }
    }

    public void selectFromDropdown(By locator, String optionToSelect) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(element);
            select.selectByVisibleText(optionToSelect);
            logger.info("Selected '" + optionToSelect + "' from dropdown: " + locator);
        } catch (Exception e) {
            logger.error("Failed to select from dropdown: " + locator, e);
            throw e;
        }
    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.sendKeys(keyToEnter);
            logger.info("Entered special key '" + keyToEnter + "' into element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to enter special key into element: " + locator, e);
            throw e;
        }
    }

    // Get text
    public String getVisibleText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            logger.info("Retrieved text: " + text + " from element: " + locator);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: " + locator, e);
            throw e;
        }
    }

    public String getVisibleText(WebElement element) {
        try {
            String text = element.getText();
            logger.info("Retrieved text: " + text + " from WebElement");
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from WebElement", e);
            throw e;
        }
    }

    public List<String> getAllVisibleText(By locator) {
        List<String> visibleTextList = new ArrayList<>();
        try {
            List<WebElement> elements = driver.get().findElements(locator);
            for (WebElement element : elements) {
                visibleTextList.add(getVisibleText(element));
            }
            return visibleTextList;
        } catch (Exception e) {
            logger.error("Failed to get all visible texts from elements: " + locator, e);
            throw e;
        }
    }

    public List<WebElement> getAllElements(By locator) {
        try {
            List<WebElement> elements = driver.get().findElements(locator);
            logger.info("Retrieved " + elements.size() + " elements for locator: " + locator);
            return elements;
        } catch (Exception e) {
            logger.error("Failed to get all elements: " + locator, e);
            throw e;
        }
    }

    // Screenshots
    public String takeScreenShot(String name) {
        File screenshotsFolder = new File("./Screenshots/");
        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdirs();
        }

        TakesScreenshot screenShot = (TakesScreenshot) driver.get();
        File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("HH-mm-ss").format(new Date());
        String path = "./Screenshots/" + name + "-" + timestamp + ".png";
        File screenShotFile = new File(path);

        try {
            FileUtils.copyFile(screenShotData, screenShotFile);
            logger.info("Screenshot saved: " + path);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + path, e);
        }
        return path;
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
