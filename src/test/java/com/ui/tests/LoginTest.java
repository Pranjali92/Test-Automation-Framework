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
public class LoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(getClass());

	@Test(description = "Verifies if the valid user is able to login into the application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProviders.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {
		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Pranjali Nirmal");
	}

//	@Test(description = "Verifies if the valid user is able to login into the application", groups = { "e2e",
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProviders.class, dataProvider = "LoginTestCSVDataProvider")
//	public void loginCSVTest(User user) {
//		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
//				"Pranjali Nirmal");
//	}
//
//	@Test(description = "Verifies if the valid user is able to login into the application", groups = { "e2e",
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProviders.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
//	public void loginexcelTest(User user) {
//
//		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
//				"Pranjali Nirmal");
//
//	}

}
