package com.core;

import java.net.URL;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.appium.java_client.AppiumDriver;

public class WebBaseClass {
	public static RemoteWebDriver webdriver = null;
	public static AbstractDriverOptions options = null;

	public static RemoteWebDriver init(){
		try {
			ReadPropertyFile rd = new ReadPropertyFile();
			if (ReadPropertyFile.getProperty("BrowserName").equalsIgnoreCase("Chrome")) {
				options = new ChromeOptions();

			} else if (ReadPropertyFile.getProperty("BrowserName").equalsIgnoreCase("Safari")) {
				options = new SafariOptions();
			}
			DesiredCapabilities sauceOptions = new DesiredCapabilities();
			sauceOptions.setCapability("name", ReadPropertyFile.getProperty("name"));
			options.setCapability("sauce:options", sauceOptions);
			options.setCapability("platform", ReadPropertyFile.getProperty("platformName"));
			options.setCapability("version", ReadPropertyFile.getProperty("browserVersion"));
			webdriver = new RemoteWebDriver(new URL(ReadPropertyFile.getProperty("sauceLabUrl")), options);
			webdriver.manage().window().maximize();
			webdriver.get(ReadPropertyFile.getProperty("applicationUrl"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return webdriver;
	}
	public static RemoteWebDriver getDriver(){
		return webdriver;
	}
	public static void QuitDriver(){
		webdriver.quit();
	}
}
