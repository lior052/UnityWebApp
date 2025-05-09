package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;


    // locators
    @FindBy(css = "input[name='email']")
    private WebElement emailField;//By.cssSelector("input[name='email']")

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;//By.cssSelector("input[name='password']")

    @FindBy(css = "button.adminjs_Button")
    private WebElement loginButton;//By.cssSelector("button.adminjs_Button")

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Initializes @FindBy elements
    }


    // Methods
    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isLoginPageDisplayed() {
        return emailField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
    }

    public HomePage login(String email, String password) {
        if (isLoginPageDisplayed()) {
            enterEmail(email);
            enterPassword(password);
            clickLoginButton();
        }
        return new HomePage(driver);
    }
}
