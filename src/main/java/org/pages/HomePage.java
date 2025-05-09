package org.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // locators
    @FindBy(xpath = "//h2[normalize-space()='Welcome, Candidate!']")
    private WebElement welcomeMessage;

    @FindBy(className = "arrow-box")
    private WebElement happyFolderMenuBtn;


    // constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    // methods
    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public boolean isWelcomeMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public HappyFolderMenuPage openMenu() {
        happyFolderMenuBtn.click();
        return new HappyFolderMenuPage(driver);
    }
}
