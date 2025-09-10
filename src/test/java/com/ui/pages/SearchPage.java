package com.ui.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Utility.BrowserUtility;

public final class SearchPage extends BrowserUtility {

	private static final By ITEM_LISTING_TITLE_LOCATOR = By.xpath("//span[@class =\"lighter\"]");
	private static final By ALL_ITEM_LIST_NAME = By.xpath("//h5[@itemprop=\"name\"]/a");

	public SearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return getVisibleText(ITEM_LISTING_TITLE_LOCATOR);
	}

//	public boolean isSearchTermPresentInItemList(String searchTerm) {
//		List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(" "));
//     List<String> itemNamesList = getAllVisibleText(ALL_ITEM_LIST_NAME);
//
//		boolean result = itemNamesList.stream()
//				.anyMatch(name -> (keywords.stream().anyMatch(name.toLowerCase()::contains)));
//		return result;
//		
//		
//	}
	
	public boolean isSearchTermPresentInItemList(String searchTerm) {
	    // Get all item names from search results
	    List<String> itemNamesList = getAllVisibleText(ALL_ITEM_LIST_NAME);

	    // Debug print - to verify what items are captured
	    System.out.println("Search Results: " + itemNamesList);

	    // Check if any product name contains the full search term (case-insensitive, trimmed)
	    boolean result = itemNamesList.stream()
	            .map(String::toLowerCase)
	            .map(String::trim)
	            .anyMatch(name -> name.contains(searchTerm.toLowerCase().trim()));

	    return result;
	}

	
	public ProductDetailPage clickOnTheProductAtIndex(int index) {
		clickOn(getAllElements(ALL_ITEM_LIST_NAME).get(index));
		ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
		return productDetailPage;
		 
	}

}
