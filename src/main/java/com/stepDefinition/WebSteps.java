package com.stepDefinition;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.util.Assert;
import com.core.MobileBaseClass;
import com.core.ReadPropertyFile;
import com.core.Util;
import com.core.WebBaseClass;
import com.pages.MobileHomePage;
import com.pages.WebSignUpPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.netty.util.Signal;
@Listeners(com.core.TestStatusListener.class)
public class WebSteps {
	WebDriver driver =null;
	public WebSignUpPage signUp = null;
	WebBaseClass base = new WebBaseClass();
	Util util = new Util();

	@Given("^Launch the application$")
	public void avon_user_is_on_Login_page() throws IOException {
		driver= WebBaseClass.init();
		signUp = new WebSignUpPage(driver);
	}

	@When("^user enters Contact information like \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enterContactDetails(String firstName, String lastName, String Email, String code, String Numbr) {
		signUp.enterFirstName(firstName);
		signUp.enterLastName(lastName);
		signUp.enterEmail(Email);
		signUp.selectActivationMedium();
		signUp.enterCountryCode(code);
		signUp.enterContactNumber(Numbr);
	}

	@And("select user consent checboxes")
	public void selectCheckBox() {
		signUp.selectConsent();
		//org.testng.Assert.assertEquals(driver.getTitle(), "Welcome");

	}

	@And("^user click on button with text \"([^\"]*)\"$")
	public void clickButton(String buttonText) {
		util.clickButtonWithText(driver, buttonText);
	}

}
