package com.hmrc.tests.steps;

import com.hmrc.tests.pages.MyStorePortalPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;


public class MyStorePortalSteps extends CommonSteps {

	
	public MyStorePortalPage registrationPage;
	public static String email_id;
	public static String password;
	
	public MyStorePortalSteps(){

		registrationPage = new MyStorePortalPage(driver);
	}
		
	@Given("^I am on My Store form$")
	public void i_am_on_My_Store_form() throws Throwable {
		registrationPage.gotoHomePage();
		assertTrue(registrationPage.isYourLogoImageDisplayed());
	}
	
	@When("^I click on Sign in option$")
	public void i_click_on_Sign_in_option() throws Throwable {
		registrationPage.clickOnSignInButton();
	}

	@Then("^I should be able to login successfully$")
	public void i_should_be_able_to_login_successfully() throws Throwable {
		assertTrue(registrationPage.welcomeToYourAccountSuccesfulMessage().contains("Welcome to your account"));
	}

	@And("^I create an account with following personal information to register$")
	public void iCreateAnAccountWithFollowingPersonalInformationToRegister(Map<String, String> personalInfo) throws Throwable {
		email_id=registrationPage.enterEmailAddress();
		registrationPage.clickOnCreateAnAccountButton();
		registrationPage.selectTitle(personalInfo.get("Title"));
		registrationPage.enterCustFirstName(personalInfo.get("FirstName"));
		registrationPage.enterCustLastName(personalInfo.get("LastName"));
		password=personalInfo.get("Password");
		registrationPage.enterPassword(password);;
		registrationPage.enterAddressLine1(personalInfo.get("AddressLine1"));
		registrationPage.enterCity(personalInfo.get("City"));
		registrationPage.selectState(personalInfo.get("State"));
		registrationPage.enterPostCode(personalInfo.get("PostCode"));
		registrationPage.selectCountry(personalInfo.get("Country"));
		registrationPage.enterMobilePhone(personalInfo.get("MobilePhone"));
		registrationPage.clickOnRegisterButton();
	}

	@And("^I should be able to see account has been created successfully$")
	public void iShouldBeAbleToSeeAccountHasBeenCreatedSuccessfully() throws Throwable {
		assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.",registrationPage.welcomeToYourAccountSuccesfulMessage());
	}

	@When("^I login back to my store portal using above created user$")
	public void iLoginBackToMyStorePortalUsingAboveCreatedUser() throws Throwable {
		registrationPage.clickOnSignInButton();
		registrationPage.enterRegisterEmailAddress(email_id);
		registrationPage.enterRegisteredUserPassword(password);
		registrationPage.clickOnSignForLogin();
	}

	@When("^I find the most expensive dress and add it to the cart$")
	public void iFindTheMostExpensiveDressAndAddItToTheCart() throws Throwable {
		registrationPage.clickOnDressesOption();
		registrationPage.selectMostExpensiveDress();
		registrationPage.clickOnAddToCartButton();
		Thread.sleep(2000);
		registrationPage.clickonProceedToCheckOutAfterAddingCart();
	}

	@And("^I logout from the application$")
	public void iLogoutFromTheApplication() throws Throwable {
		registrationPage.clickOnLogoutOption();
	}

	@And("^I should see that added item in the cart is still available$")
	public void iShouldSeeThatAddedItemInTheCartIsStillAvailable() throws Throwable {
		List<String> cartMessages=registrationPage.getCartItems();
		assertTrue(cartMessages.contains("1"));
	}
}
