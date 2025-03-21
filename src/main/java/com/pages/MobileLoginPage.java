package com.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import com.core.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.core.ReadPropertyFile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MobileLoginPage {
	private RemoteWebDriver driver = null;
	Util util = new Util();
	
	@AndroidFindBy(id = "imageImageView")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"headerLabel\"])[1])")
	public static WebElement imageImageView;

	@AndroidFindBy(id = "marketSelectMarketButton")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name == \"textField\"`][1]")
	public static WebElement marketSelectMarketButton;

	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypePickerWheel']")
	public static WebElement wheel;

	@AndroidFindBy(id = "marketSelectLangButton")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name == \"textField\"`][2]")
	public static WebElement marketSelectLangButton;

	@AndroidFindBy(id = "avonButton")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"doneButton\"]")
	public static WebElement PressContinue;

	@AndroidFindBy(id = "com.avon.avonon:id/loginAccountTextField")
	@iOSXCUITFindBy(xpath = "//*[@value = \"Account Number\"]")
	public static WebElement accountNumber;

	@iOSXCUITFindBy(accessibility = "CONTINUE")
	public static WebElement doneAccount;

	@AndroidFindBy(id = "loginPasswordTextField")
	@iOSXCUITFindBy(xpath = "//*[@value = \"Password\"]")
	public static WebElement password;

	@AndroidFindBy(id = "com.avon.avonon:id/avonButton")
	@iOSXCUITFindBy(accessibility = "loginButton")
	public static WebElement loginButton;

	@AndroidFindBy(className = "android.widget.TextView")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Avon\"`]")
	public static WebElement welcomeText;

	@iOSXCUITFindBy(accessibility = "Done")
	public static WebElement doneIos;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"app logo\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'IndicatorView')]")
	public List<WebElement> pin;

	@AndroidFindBy(xpath = "//*[@text='ENABLE FINGER PRINT']")
	@iOSXCUITFindBy(accessibility = "enableButton")
	public static WebElement faceId;

	@iOSXCUITFindBy(accessibility = "SKIP FOR NOW")
	public static WebElement skippNotification;

	public MobileLoginPage(RemoteWebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(new AppiumFieldDecorator(driver2), this);

		ReadPropertyFile rd = new ReadPropertyFile();
	}

	public void pressContinue() {
		PressContinue.click();
	}

	public void selectMarketPlace(String country) throws IOException {
		if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
			marketSelectMarketButton.click();
			util.selectWheelValue(wheel, country);
		} else {
			marketSelectMarketButton.click();
			util.selectElementByText(driver, country);
		}
	}

	public void selectLanguage(String lang) throws IOException {
		if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
			marketSelectLangButton.click();
			util.selectWheelValue(wheel, lang);
			doneIos.click();
		} else {
//			marketSelectLangButton.click();
//			util.selectElementByText(driver, lang);
		}
	}

	public void verifyAccountPageDisplayed() throws IOException {
		if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
			String normalBtn2 = welcomeText.getText();
			Assert.assertEquals(normalBtn2, "Avon");
			System.out.println("Welcome screen found");
		} else {
			String normalBtn2 = welcomeText.getText();
			Assert.assertEquals(normalBtn2, "Welcome");
			System.out.println("Welcome screen found");
		}

	}

	public void enterAccountNumber(String accountNo) {
		util.enterValueInTextField(accountNumber, accountNo);

	}

	public void enterPasswordNumber(String passWord) throws IOException {
		if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
			util.clickButton(doneAccount);
		}
		util.enterValueInTextField(password, passWord);

	}

	public void clickLoginButton() {
		util.clickButton(loginButton);
	}

	public void enterPin(String pinVal) {
		String[] str = pinVal.split(",");

		if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
			int i = 0;
			for (WebElement e : pin) {
				e.sendKeys(str[i]);
				i++;
			}
		} else {
			AndroidDriver dr = (AndroidDriver) driver;
			new WebDriverWait(driver, Duration.ofSeconds(60))
					.until(ExpectedConditions.elementToBeClickable(pin.get(0)));
			for (int i = 0; i < 4; i++) {
				typeNumPad(dr, str[i]);
			}

		}

	}

	private void typeNumPad(AndroidDriver dr, String digit) {
		switch (digit) {
		case "0":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_0));
			break;
		case "1":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
			break;
		case "2":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			break;
		case "3":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
			break;
		case "4":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			break;
		case "5":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
			break;
		case "6":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_6));
			break;
		case "7":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_7));
			break;
		case "8":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
			break;
		case "9":
			dr.pressKey(new KeyEvent(AndroidKey.DIGIT_9));
			break;
		}
	}

	public void skippNotifications() {
		util.clickButton(faceId);
			if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
				util.clickButton(skippNotification);
			}
	}

}
