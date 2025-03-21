package com.stepDefinition;

import com.pages.*;
import com.core.*;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
@Listeners(com.core.TestStatusListener.class)
public class MobileSteps {
	RemoteWebDriver driver = null;
	public MobileLoginPage login = null;
	public MobileHomePage home = null;
	public MobileLogOutPage logout = null;
	MobileBaseClass base = new MobileBaseClass();

	@Given("^User is on Welcome page$")
	public void avon_user_is_on_Login_page() {
		driver = MobileBaseClass.init();;
		login = new MobileLoginPage(driver);
		home = new MobileHomePage(driver);
		logout = new MobileLogOutPage(driver);

	}

	@When("^user selects \"([^\"]*)\" and \"([^\"]*)\" from dropdown$")
	public void avon_user_is_on_Login_page(String Market, String language) throws InterruptedException, IOException {
		login.selectMarketPlace(Market);
		login.selectLanguage(language);
		login.pressContinue();

	}

	@When("^User enters valid \"([^\"]*)\" and \"([^\"]*)\" on Login Page$")
	public void user_enters_valid_and_in_login_page(String accountno, String password) throws IOException {
		login.enterAccountNumber(accountno);
		login.enterPasswordNumber(password);
		login.clickLoginButton();
	}

	@And("^User enters the \"([^\"]*)\" PIN code$")
	public void drawPin(String pinCode) {
		login.enterPin(pinCode);
	}

	@And("^User confirms the \"([^\"]*)\" PIN code$")
	public void confirmPin(String pinCode) {
		login.enterPin(pinCode);
	}

	@Then("^Avon Home Page is displayed$")
	public void homePageDisplayed() {
		login.skippNotifications();
		home.verifyHomeScreen();

	}

	@When("^user opens Menu icon$")
	public void expandMenu() {
		home.verifyHomeScreen();

	}

	@And("^user click on \"([^\"]*)\" button$")
	public void avon_home_page_is_displayed(String buttonText) {
		home.openMenu();
		home.clickOnLogOutButton(buttonText);

	}

	@Then("^Verify user is logged out$")
	public void loggedOutPage() throws IOException {
		logout.verifyLogOutScreen();

	}

	@After
	public void closeSession(Scenario scenario) throws IOException {
		
		Util.takeScreenshotOnFailure(scenario);
		ReadPropertyFile rd = new ReadPropertyFile();
		if (ReadPropertyFile.getProperty("platformName").contains("Windows")
				|| ReadPropertyFile.getProperty("platformName").contains("mac")) {
			WebBaseClass.QuitDriver();
		} else {
			MobileBaseClass.QuitDriver();
		}
	}
	

}
