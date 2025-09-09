package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.constants.Size.*;
import com.ui.pages.SearchPage;

public class ProductCheckoutTest extends TestBase {
	private static final String SEARCH_TERM = "Printed Summer Dress";
	private SearchPage searchPage;

	@BeforeMethod(description = "User logs into the application and searching for a Product")
	public void setup() {
		searchPage = homepage.goToLoginPage().doLoginWith("wawawi3265@devdigs.com", "Password").SearchItem(SEARCH_TERM);

	}

	@Test(description = "Verify if the logged in User is able to buy a dress", groups = { "e2e", "smoke", "sanity" })
	public void CheckoutTest() {
		String result = searchPage.clickOnTheProductAtIndex(1).changeSize(L).addItemToCart().proceedToCheckout()
				.goToConfirmAddressPage().goToShippingPage().goToPaymentPage().makePaymentByWire();
		Assert.assertTrue(result.contains("complete"));

	}

}
