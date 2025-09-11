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
        this.driver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public static void removeDriver() {
        driver.remove();
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching browser: " + browserName);
        if (browserName == Browser.CHROME) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            driver.set(new ChromeDriver(options));
        } else if (browserName == Browser.EDGE) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
            }
            driver.set(new EdgeDriver(options));
        } else if (browserName == Browser.FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver.set(new FirefoxDriver(options));
        }
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }

    public void goToWebsite(String url) {
        logger.info("Visiting the website: " + url);
        driver.get().get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    private void retryAction(Runnable action, String actionDescription) {
        int attempts = 0;
        boolean success = false;
        while (attempts < 3 && !success) {
            try {
                action.run();
                success = true;
            } catch (Exception e) {
                attempts++;
                logger.warn("Attempt " + attempts + " failed for action: " + actionDescription + " | " + e.getMessage());
                if (attempts >= 3) {
                    logger.error("Action failed after " + attempts + " attempts: " + actionDescription, e);
                    throw e;
                }
                try {
                    Thread.sleep(500); // small wait before retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void clickOn(By locator) {
        retryAction(() -> {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        }, "Click on element: " + locator);
    }

    public void clickOnCheckBox(By locator) {
        retryAction(() -> {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.click();
        }, "Click on checkbox: " + locator);
    }

    public void clickOn(WebElement element) {
        retryAction(element::click, "Click on WebElement");
    }

    public void enterText(By locator, String textToEnter) {
        retryAction(() -> {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(textToEnter);
        }, "Enter text '" + textToEnter + "' into element: " + locator);
    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        retryAction(() -> {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.sendKeys(keyToEnter);
        }, "Enter special key '" + keyToEnter + "' into element: " + locator);
    }

    public void clearText(By textBoxLocator) {
        retryAction(() -> {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
            element.clear();
        }, "Clear text in element: " + textBoxLocator);
    }

    public void selectFromDropdown(By dropDownLocator, String optionToSelect) {
        retryAction(() -> {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownLocator));
            Select select = new Select(element);
            select.selectByVisibleText(optionToSelect);
        }, "Select '" + optionToSelect + "' from dropdown: " + dropDownLocator);
    }

    public String getVisibleText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public String getVisibleText(WebElement element) {
        return element.getText();
    }

    public List<String> getAllVisibleText(By locator) {
        List<WebElement> elementList = driver.get().findElements(locator);
        List<String> visibleTextList = new ArrayList<>();
        for (WebElement element : elementList) {
            visibleTextList.add(getVisibleText(element));
        }
        return visibleTextList;
    }

    public List<WebElement> getAllElements(By locator) {
        return driver.get().findElements(locator);
    }

    public String takeScreenShot(String name) {
        File screenshotsFolder = new File("./Screenshots/");
        if (!screenshotsFolder.exists()) screenshotsFolder.mkdirs();

        TakesScreenshot screenShot = (TakesScreenshot) driver.get();
        File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("HH-mm-ss").format(new Date());
        String path = "./Screenshots/" + name + "-" + timestamp + ".png";
        File screenShotFile = new File(path);
        try {
            FileUtils.copyFile(screenShotData, screenShotFile);
            System.out.println("Screenshot saved: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static void quit() {
        if (driver.get() != null) driver.get().quit();
    }
}
