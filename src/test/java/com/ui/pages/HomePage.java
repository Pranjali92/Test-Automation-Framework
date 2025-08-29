package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Utility.BrowserUtility;
import com.Utility.JSONUtility;
import com.Utility.LoggerUtility;
import com.constants.Browser;
import static com.constants.Env.*;

public final class HomePage extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());

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

	public static void quit() {
//		if (driver.get() != null) {
//			driver.get().quit();
//		}
//	
	}

}
