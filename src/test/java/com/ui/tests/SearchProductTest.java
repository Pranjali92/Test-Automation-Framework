package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.MyAccountPage;

@Listeners({ com.ui.listeners.TestListener.class })

public class SearchProductTest extends TestBase {

	private MyAccountPage myAccountPage;
	private static final String SEARCH_TERM = "Printed Dress";

	@BeforeMethod(description = "Valid user logs into the application")
	public void setup() {
		myAccountPage = homepage.goToLoginPage().doLoginWith("wawawi3265@devdigs.com", "Password");

	}

	@Test(description = "Verify if the logged in user is able to search for product and correct product search results are displayed", groups = {
			"e2e", "smoke", "sanity" })

	public void verifyproductSearchTest() {
		boolean actualresult = myAccountPage.SearchItem(SEARCH_TERM).isSearchTermPresentInItemList(SEARCH_TERM);
		Assert.assertEquals(actualresult, true);
	}

}
