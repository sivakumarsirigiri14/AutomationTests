package com.hmrc.tests.pages;

import com.hmrc.tests.test.configutils.ConfigUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyStorePortalPage {

    public String email;
    private WebDriver driver;

    @FindBy(css = ".login")
    private WebElement signIn;

    @FindBy(id = "email_create")
    private WebElement emailCreate;

    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccount;

    @FindBy(id = "id_gender1")
    private WebElement titleMr;

    @FindBy(id = "id_gender2")
    private WebElement titleMrs;

    @FindBy(id = "customer_firstname")
    private WebElement custFirstName;

    @FindBy(id = "customer_lastname")
    private WebElement custLastName;

    @FindBy(id = "email")
    private WebElement emailAddress;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "firstname")
    private WebElement firstNameConfirm;

    @FindBy(id = "lastname")
    private WebElement lastNameConfirm;

    @FindBy(id = "address1")
    private WebElement AddressLine1;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "id_state")
    private WebElement state;

    @FindBy(id = "postcode")
    private WebElement postCode;

    @FindBy(id = "id_country")
    private WebElement country;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhone;

    @FindBy(id = "submitAccount")
    private WebElement RegisterButton;

    @FindBy(css = ".logo.img-responsive")
    private WebElement yourLogoImage;

    @FindBy(css = ".info-account")
    private WebElement welcomeToYourAccount;

    @FindBy(css = ".logout")
    private WebElement logOut;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLogin;

    @FindBy(xpath = ".//*[@id='block_top_menu']/ul/li[2]/a")
    private WebElement dressesButton;

    @FindBy(css = "p#add_to_cart>.exclusive")
    private WebElement addToCartButton;

    @FindBy(css = "h2 .ajax_cart_product_txt")
    private WebElement ItemInYourCartMessage;

    @FindBy(css = ".btn.btn-default.button.button-medium>span")
    private WebElement proceedToCheckOutAfterAddingCart;

    @FindBy(css = ".right-block>div>span.price.product-price")
    private List<WebElement> pricesElements;

    @FindBy(css = ".shopping_cart>a>span")
    private List<WebElement> cartText;


    public MyStorePortalPage(WebDriver driver) {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(finder, this);
        this.driver = driver;
    }

    public void gotoHomePage() {
        driver.get(ConfigUtils.getWebEndPoint());
    }

    public void clickOnSignInButton() {
        signIn.click();
    }

    public String enterEmailAddress() {
        String emailfirstPart = RandomStringUtils.randomAlphabetic(5).toLowerCase();
        String emailSecPart = RandomStringUtils.randomAlphabetic(5).toLowerCase();
        String email = emailfirstPart + "@" + emailSecPart + ".com";
        emailCreate.sendKeys(email);
        return email;
    }

    public void clickOnCreateAnAccountButton() {
        createAnAccount.click();
    }

    public void selectTitle(String title) {
        if (title.toLowerCase().contains("mrs.")) {
            titleMrs.click();
        } else {
            titleMr.click();
        }
    }

    public void enterCustFirstName(String firstName) {
        custFirstName.sendKeys(firstName);
    }

    public void enterCustLastName(String lastName) {
        custLastName.sendKeys(lastName);
    }


    public void enterPassword(String passwrd) {
        passwordField.sendKeys(passwrd);
    }


    public void enterAddressLine1(String addressLine1) {
        AddressLine1.sendKeys(addressLine1);
    }

    public void enterCity(String cityName) {
        city.sendKeys(cityName);
    }

    public void enterState(String stateName) {
        state.sendKeys(stateName);
    }

    public void selectState(String countryVal) {
        Select dropdown = new Select(state);
        dropdown.selectByVisibleText(countryVal);
    }

    public void enterPostCode(String postCodeNo) {
        postCode.sendKeys(postCodeNo);
    }

    public void selectCountry(String countryVal) {
        Select dropdown = new Select(country);
        dropdown.selectByVisibleText(countryVal);
    }

    public void enterMobilePhone(String mobileNo) {
        mobilePhone.sendKeys(mobileNo);
    }


    public boolean isYourLogoImageDisplayed() {
        return yourLogoImage.isDisplayed();
    }

    public void clickOnRegisterButton() {
        RegisterButton.click();
    }

    public String welcomeToYourAccountSuccesfulMessage() {
        return welcomeToYourAccount.getText();
    }

    public void clickOnLogoutOption() {
        logOut.click();
    }

    public void enterRegisterEmailAddress(String email_id) {
        emailAddress.clear();
        emailAddress.sendKeys(email_id);
    }

    public void enterRegisteredUserPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);

    }

    public void clickOnSignForLogin() {
        submitLogin.click();
    }

    public void clickOnDressesOption() {
        dressesButton.click();
    }


    public void clickOnAddToCartButton() {
        driver.findElement(By.cssSelector("p#add_to_cart>.exclusive")).click();
    }


    public void clickonProceedToCheckOutAfterAddingCart() {
        proceedToCheckOutAfterAddingCart.click();
    }


    public void selectMostExpensiveDress() {
        List<Float> prices = new ArrayList<Float>();
        for (WebElement element : pricesElements) {
            String price = element.getText().replace("$", "");
            prices.add(Float.valueOf(price));

        }
        Collections.sort(prices);
        Float x = prices.get(prices.size() - 1);
        for (int i = 0; i < pricesElements.size(); i++) {
            if (pricesElements.get(i).getText().contains(x.toString())) {
                WebElement el = driver.findElement(By.cssSelector(".product_list.grid.row>li:nth-child(" + (i + 1) + ")>div>div.right-block>h5>a"));
                el.click();
                break;
            }
        }

    }

    public List<String> getCartItems() {
        List<String> cartMessages=new ArrayList<String>();
        for (WebElement element : cartText) {
            String text = element.getText();
            cartMessages.add(text);
        }

        return cartMessages;
    }
}
