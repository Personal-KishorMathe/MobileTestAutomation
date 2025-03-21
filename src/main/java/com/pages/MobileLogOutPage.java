package com.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.core.ReadPropertyFile;
import com.core.Util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MobileLogOutPage {
	private RemoteWebDriver driver = null;
	Util util = new Util();
	@AndroidFindBy(id = "imageImageView")
	@iOSXCUITFindBy(iOSNsPredicate = "label == \"Help & Support\"")
	public static WebElement helpTab;
	
	@AndroidFindBy(id = "imageImageView")
	@iOSXCUITFindBy(iOSNsPredicate = "label == \"Email Avon\"")
	public static WebElement avonEmailTab;
	
	@AndroidFindBy(id = "com.avon.avonon:id/buttonPositive")
	public static WebElement continueLogout;
	@AndroidFindBy(id = "com.avon.avonon:id/buttonNegative")
	public static WebElement cancelLogout;
	
	public MobileLogOutPage(RemoteWebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(new AppiumFieldDecorator(driver2), this);
	}
	public void verifyLogOutScreen() throws IOException {
		if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
		Assert.assertTrue(helpTab.isDisplayed(), "User is not successfully logged out");
		Assert.assertFalse(avonEmailTab.isDisplayed(), "User is successfully logged out"); //deliberately failed
		}else {
			util.clickButton(continueLogout);
		}
	}
}
