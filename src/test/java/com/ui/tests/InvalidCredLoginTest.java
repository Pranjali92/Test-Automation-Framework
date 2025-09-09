package com.ui.tests;

import static org.testng.Assert.*;
import org.apache.logging.log4j.Logger;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Utility.LoggerUtility;
//import com.constants.Env;
//import com.ui.pages.HomePage;
import com.ui.pojo.User;

@Listeners({ com.ui.listeners.TestListener.class })
public class InvalidCredLoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(getClass());
	private static final String INVALID_EMAIL_ADDRESS = "pranjalinirmal@gmail.com";
	private static final String INVALID_PASSWORD = "Test12344";

	@Test(description = "Verify if the proper error message is shown for the user when they enter invalid credentials", groups = {
			"e2e", "sanity", "smoke" })
	public void loginTest() {
		assertEquals(homepage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
				.getErrorMessage(), "Authentication failed.");

	}

}
