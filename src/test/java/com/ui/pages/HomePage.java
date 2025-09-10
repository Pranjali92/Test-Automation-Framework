package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utility.BrowserUtility;
import com.Utility.JSONUtility;
import com.Utility.LoggerUtility;
import com.constants.Browser;
import static com.constants.Env.*;

import java.time.Duration;

public final class HomePage extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());
//	WebDriverWait wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(20));

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());

	}

	public HomePage(WebDriver driver) {
		super(driver);// To call the parent class constructor from the child constructor
		goToWebsite(JSONUtility.readJSON(QA).getUrl());

	}

	public LoginPage goToLoginPage() {// this is page function. and in page function u cannot use void return type
		logger.info("Trying to click on sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginpage = new LoginPage(getDriver());
		return loginpage;
	}

//	public LoginPage goToLoginPage() {
//        logger.info("Trying to click on Sign in link...");
//
//        // ✅ Explicit wait here
//        WebElement signInLink = wait.until(
//                ExpectedConditions.elementToBeClickable(SIGN_IN_LINK_LOCATOR)
//        );
//
//        signInLink.click();
//        logger.info("Clicked on Sign in link");
//
//        return new LoginPage(getDriver());
//    }

//	public static void quit() {
//		if (getDriver()!= null) {
//			driver.get().quit();
//		}
//
//	}
	
	public static void quit() {
        WebDriver currentDriver = getDriver();  // fetch driver from ThreadLocal
        if (currentDriver != null) {
            try {
                currentDriver.quit();  // quits the browser
            } catch (Exception e) {
                System.err.println("Error quitting driver: " + e.getMessage());
            } finally {
                removeDriver();  // remove ThreadLocal reference
            }
        }
    }

}
