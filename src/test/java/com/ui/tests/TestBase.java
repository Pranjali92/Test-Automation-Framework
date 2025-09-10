package com.ui.tests;

import static com.constants.Browser.*;

import java.util.Set;

import com.ui.pages.HomePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;

import com.Utility.BrowserUtility;
import com.Utility.ExtentReporterUtility;
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
			@Optional("false") boolean isHeadless, ITestResult result) {

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

//	@AfterMethod(description = "Tear Down the Browser")
//	public void tearDown() {
//
//		if (isLambdaTest) {
//			LambdaTestUtility.quitSession();
//
//		} else {
//			HomePage.quit();
//		}
//	}

	@AfterMethod(description = "Tear Down the Browser" , alwaysRun = true)
	public void tearDown() {
		System.out.println(">>> Starting tearDown on thread: " + Thread.currentThread().getId());
		try {
			if (isLambdaTest) {
				LambdaTestUtility.quitSession(); // quits LambdaTest session
			} else {
				HomePage.quit(); // quits local headless browser
			}
			System.out.println("tearDown completed: Browser quit successfully");
		} catch (Exception e) {
			System.err.println("Error during tearDown: " + e.getMessage());
		}
//		finally {
//	        BrowserUtility.removeDriver();
//	        System.out.println(">>> Driver removed for thread: " + Thread.currentThread().getId());
//	    }
		
		System.out.println("Active threads at tearDown:");
	    for (Thread t : Thread.getAllStackTraces().keySet()) {
	        System.out.println(" - " + t.getName() + " (state: " + t.getState() + ")");
	    }
	}

//	@AfterSuite
//	public void cleanUpSuite() {
//		try {
//			// Flush ExtentReports
//			ExtentReporterUtility.flushReport();
//			System.out.println("ExtentReports flushed and closed");
//
//			// Remove ThreadLocal WebDriver
//			BrowserUtility.removeDriver();
//			System.out.println("ThreadLocal WebDriver cleaned up");
//
//		} catch (Exception e) {
//			System.err.println("Error during suite cleanup: " + e.getMessage());
//		}
//	}
	


	@AfterSuite
	public void cleanUpSuite() {
		 System.out.println(">>> ENTERED @AfterSuite <<<");
	    try {
	        BrowserUtility.removeDriver();
	        System.out.println("ThreadLocal WebDriver cleaned up");

	        LogManager.shutdown();
	        System.out.println("Log4j shutdown completed");

	        // 🔎 List all active threads
	        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
	        System.out.println("=== Live Threads at Suite End ===");
	        for (Thread t : threadSet) {
	            System.out.println("Thread: " + t.getName() 
	                + " | Daemon: " + t.isDaemon() 
	                + " | State: " + t.getState());
	        }
	        System.out.println("================================");

	    } catch (Exception e) {
	        System.err.println("Error during suite cleanup: " + e.getMessage());
	    } finally {
	        new Thread(() -> {
	            try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
	            System.exit(0);
	        }).start();
	    }
	}


}
