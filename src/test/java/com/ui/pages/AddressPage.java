package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Utility.BrowserUtility;
import com.ui.pojo.AddressPOJO;

public class AddressPage extends BrowserUtility {

	private static final By COMPANY_TEXTBOX__LOCATOR = By.id("company");
	private static final By ADDRESS1_TEXTBOX__LOCATOR = By.id("address1");
	private static final By ADDRESS2_TEXTBOX__LOCATOR = By.id("address2");
	private static final By CITY_TEXTBOX__LOCATOR = By.id("city");
	private static final By POSTCODE_TEXTBOX__LOCATOR = By.id("postcode");
	private static final By PHONE_TEXTBOX__LOCATOR = By.id("phone");
	private static final By MOBILE_TEXTBOX__LOCATOR = By.id("phone_mobile");
	private static final By OTHER_INFORMATION_TEXTBOX_LOCATOR = By.id("other");
	private static final By ADDRESS_ALIAS_TEXTBOX_LOCATOR = By.id("alias");
	private static final By STATE_DROPDOWN_LOCATOR = By.id("id_state");
	private static final By SAVE_ADDRESS_LOCATOR = By.id("submitAddress");
	private static final By ADDRESS_HEADING=By.tagName("h3");
	public AddressPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String saveAddress(AddressPOJO addressPOJO) {
		enterText(COMPANY_TEXTBOX__LOCATOR, addressPOJO.getCompany());
		enterText(ADDRESS1_TEXTBOX__LOCATOR, addressPOJO.getAddressLine1() );
		enterText(ADDRESS2_TEXTBOX__LOCATOR, addressPOJO.getAddressLine2());
		enterText(CITY_TEXTBOX__LOCATOR, addressPOJO.getCity());
		enterText(POSTCODE_TEXTBOX__LOCATOR, addressPOJO.getPostCode());
		enterText(PHONE_TEXTBOX__LOCATOR, addressPOJO.getHomePhoneNumber());
		enterText(MOBILE_TEXTBOX__LOCATOR, addressPOJO.getMobileNumber());
		enterText(OTHER_INFORMATION_TEXTBOX_LOCATOR, addressPOJO.getOtherinformation());
		clearText(ADDRESS_ALIAS_TEXTBOX_LOCATOR);
		enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR, addressPOJO.getAddressAlias());
		selectFromDropdown(STATE_DROPDOWN_LOCATOR, addressPOJO.getState());
		clickOn(SAVE_ADDRESS_LOCATOR);
		String newAddress=getVisibleText(ADDRESS_HEADING);
		return newAddress;
	}

}
