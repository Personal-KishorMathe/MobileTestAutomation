package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.core.Util;

public class WebSignUpPage {
	private WebDriver Wdriver = null;
	Util util = new Util();

	@FindBy(id = "outlined-basic-Name")
	public static WebElement name;
	@FindBy(id = "outlined-basic-Surname")
	public static WebElement surname;
	@FindBy(id = "outlined-basic-Email")
	public static WebElement email;
	@FindBy(name = "row-radio-buttons-group")
	public static WebElement smsRadio;
	@FindBy(id = "outlined-basic-+999")
	public static WebElement countryCode;
	@FindBy(id = "outlined-basic-999 999 999")
	public static WebElement contactNumber;
	@FindBy(name = "consentCheckbox")
	public static List<WebElement> consent;

	public WebSignUpPage(WebDriver driver) {
		this.Wdriver = driver;
		PageFactory.initElements(Wdriver, this);
	}

	public void enterFirstName(String Name) {
		util.enterValueInTextField(name, Name);
	}

	public void enterLastName(String surName) {
		util.enterValueInTextField(surname, surName);
	}

	public void selectActivationMedium() {
		util.selectRadioButton(smsRadio);
	}

	public void enterEmail(String Email) {
		util.enterValueInTextField(email, Email);
	}

	public void enterCountryCode(String code) {
		util.enterValueInTextField(countryCode, code);
	}

	public void enterContactNumber(String number) {
		util.enterValueInTextField(contactNumber, number);
	}

	public void selectConsent() {
		for (WebElement e : consent) {
			e.click();
		}
	}
}
