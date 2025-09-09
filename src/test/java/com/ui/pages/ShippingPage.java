package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Utility.BrowserUtility;

public class ShippingPage extends BrowserUtility {
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.name("processCarrier");
	private static final By TERMS_OF_SERVICE_CHECKBOX = By.id("uniform-cgv");
	
	public ShippingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public PaymentPage goToPaymentPage() {
		clickOn(TERMS_OF_SERVICE_CHECKBOX);
		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new PaymentPage(getDriver());
	}

}
