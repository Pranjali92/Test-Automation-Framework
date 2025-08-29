package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Utility.BrowserUtility;
import com.Utility.LambdaTestUtility;
import com.Utility.LoggerUtility;
import com.constants.Browser;

public class TestBase {

	protected HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;
	private WebDriver lambdaDriver;

	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	@BeforeMethod(description = "Load the HomePage of the website")
	public void Setup(@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadless, ITestResult result) {

		this.isLambdaTest = isLambdaTest;

		if (isLambdaTest) {

			lambdaDriver = LambdaTestUtility.initializeLambdaSession(browser, result.getMethod().getMethodName());
			homepage = new HomePage(lambdaDriver);
		} else {
			// running the test on local machine
			logger.info("Load the HomePage of the website");
			homepage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);

		}

	}

	public BrowserUtility getInstance() {
		return homepage;
	}

	@AfterMethod(description = "Tear Down the Browser")
	public void tearDown() {

		if (isLambdaTest) {
			LambdaTestUtility.quitSession();

		} else {
			HomePage.quit();
		}
	}

}
