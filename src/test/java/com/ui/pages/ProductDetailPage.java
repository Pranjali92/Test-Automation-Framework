package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Utility.BrowserUtility;
import com.constants.Size;

public class ProductDetailPage extends BrowserUtility {

	private static final By SIZE_DROP_DOWN_LOCATOR = By.id("group_1");
	private static final By ADD_TO_CART_BUTTON_LOCATOR = By.name("Submit");
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//a[@title=\"Proceed to checkout\"]");

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public ProductDetailPage changeSize(Size size) {
		selectFromDropdown(SIZE_DROP_DOWN_LOCATOR, size.toString());
		ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
		return productDetailPage;
	}

	public ProductDetailPage addItemToCart() {
		clickOn(ADD_TO_CART_BUTTON_LOCATOR);
		ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
		return productDetailPage;
	}

	public ShoppingCartPage proceedToCheckout() {

		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(getDriver());
		return shoppingCartPage;
	}
}
