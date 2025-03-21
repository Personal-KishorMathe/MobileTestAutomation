package com.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class MobileBaseClass {

	public static AppiumDriver driver = null;
	static ReadPropertyFile rd = null;

	public static AppiumDriver init() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("appium:platformName", "Android");
		caps.setCapability("appium:Package", "com.avon.avonon.uat");
		caps.setCapability("appium:Activity", "com.avon.avonon.presentation.screens.splash.SplashActivity");

		try {
			driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("welcome kishor to aws world");

		try {
			rd = new ReadPropertyFile();
			caps.setCapability("platformName", ReadPropertyFile.getProperty("platformName"));
			caps.setCapability("appium:deviceName", ReadPropertyFile.getProperty("deviceName"));
			caps.setCapability("appium:deviceOrientation", ReadPropertyFile.getProperty("deviceOrientation"));
			caps.setCapability("appium:platformVersion", ReadPropertyFile.getProperty("platformVersion"));
			caps.setCapability("appium:automationName", ReadPropertyFile.getProperty("automationName"));
			caps.setCapability("app", ReadPropertyFile.getProperty("app"));
			DesiredCapabilities sauceOptions = new DesiredCapabilities();
			sauceOptions.setCapability("build", ReadPropertyFile.getProperty("build"));
			sauceOptions.setCapability("name", ReadPropertyFile.getProperty("name"));
			caps.setCapability("sauce:options", sauceOptions);
			System.out.println("Sauce Lab connected succefully");
			URL url = new URL(ReadPropertyFile.getProperty("url"));

			if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("Android")) {
				driver = new AndroidDriver(url, caps);
			} else if (ReadPropertyFile.getProperty("platformName").equalsIgnoreCase("iOS")) {
				driver = new IOSDriver(url, caps);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public static AppiumDriver getDriver() {
		return driver;
	}

	public static void QuitDriver() {
		driver.quit();
	}
}
