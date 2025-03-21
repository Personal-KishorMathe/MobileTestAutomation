package com.pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.core.ReadPropertyFile;
import com.core.Util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MobileHomePage {
	private RemoteWebDriver driver = null;
	Util util = new Util();
	
	@FindBy(name = "web.element")
	@AndroidFindBy(xpath = "//*[@text='PLACE AN ORDER']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"PLACE AN ORDER\"`]")
	public static WebElement placeOrder;

	@AndroidFindBy(xpath = "//*[@text='Log out']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"menu_tr_menu_logout_sectionHeader\"]/preceding-sibling::XCUIElementTypeOther[@name=\"menu_tr_menu_help_sectionHeader\"]")
	public static WebElement logout;

	@AndroidFindBy(xpath = "(//android.view.View//*[@class='android.widget.ImageView'])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell//XCUIElementTypeButton[@name=\"menuButton\"]")
	public static WebElement menuLines;

	public MobileHomePage(RemoteWebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(new AppiumFieldDecorator(driver2), this);
	
	}

	public void verifyHomeScreen() {
		Assert.assertTrue(placeOrder.isDisplayed(), "User is not on Home Page");
	}

	public void openMenu() {
		util.clickButton(menuLines);
	}

	public void clickOnLogOutButton(String text) {
		util.scrollToElement(driver, logout, "down", text);
		util.clickButton(logout);

	}
	public void enterValue() {
	util.enterValueInTextField(placeOrder, "test");
		
	}

}
