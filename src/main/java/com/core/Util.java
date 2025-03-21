package com.core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestException;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;

public class Util {
	static Properties p;
	static FileReader reader;
	static RemoteWebDriver driver = null;

	public static void takeScreenshotOnFailure(Scenario scenario) {
		byte[] srcFile2=null;
		File dest=null;
		
				driver = MobileBaseClass.getDriver();
		
			try {
				TakesScreenshot scrShot = ((TakesScreenshot) driver);
				File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
				srcFile2 = scrShot.getScreenshotAs(OutputType.BYTES);
				String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
				dest = new File(
						System.getProperty("user.dir") + "\\target\\Screenshots\\" + scenario + timestamp + ".png");
				FileUtils.copyFile(SrcFile, dest);
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scenario.attach(srcFile2, "image/png", scenario.getName());
		}

	public void navigateToURL(String URL, AndroidDriver driver) {
		System.out.println("Navigating to: " + URL);
		System.out.println("Thread id = " + Thread.currentThread().getId());

		try {
			driver.navigate().to(URL);
		} catch (Exception e) {
			System.out.println("URL did not load: " + URL);
			throw new TestException("URL did not load");
		}
	}

	public void clearField(WebElement element) {
		try {
			element.clear();
		} catch (Exception e) {
			System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
		}
	}

	public static String getText(WebElement element) {

		return element.getText();

	}

//	public void explictWait(AppiumDriver driver, By ele) {
//		WebElement wait = new Webdriver(driver, 10)
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
//	}

	public void enterValueInTextField(WebElement accountNumber, String Value) {
		accountNumber.sendKeys(Value);

	}

	public void clickButton(WebElement loginButton) {
		loginButton.click();

	}

	public void selectElementByText(RemoteWebDriver driver, String country) {
		WebElement element = scrollAndroidElement(driver, country);
		element.click();
	}

	public void selectRadioButton(WebElement element) {
		element.click();
	}

	public WebElement scrollAndroidElement(RemoteWebDriver driver, String text) {
		WebElement element = (WebElement) driver
				.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"));
		return element;
	}

	public void selectWheelValue(WebElement wheel, String country) {
		wheel.sendKeys(country);

	}

	public void scrollToElement(RemoteWebDriver driver, WebElement logout, String direction, String text) {
		if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
			HashMap scrollObject = new HashMap();
			scrollObject.put("element", logout);
			scrollObject.put("direction", direction);
			driver.executeScript("mobile: scroll", scrollObject);
		} else {
			scrollAndroidElement(driver, text);
		}
	}

	public void clickButtonWithText(WebDriver driver, String buttontext) {
		driver.findElement(By.xpath("//button[text()='" + buttontext + "']")).click();
	}

}
