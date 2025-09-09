package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Utility.FakeAdressUtility;
import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;

public class AddNewFirstAddressTest extends TestBase {

	private MyAccountPage myAccountPage;  
	private AddressPOJO address;

	@BeforeMethod(description = "Valid First Time user logs into the application")
	public void setup() {
		myAccountPage = homepage.goToLoginPage().doLoginWith("wawawi3265@devdigs.com", "Password");
		address = FakeAdressUtility.getFakeAddress();
	}

	@Test
	public void addNewAddress() {
		String newAddress=myAccountPage.goToAddAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
	}

}
