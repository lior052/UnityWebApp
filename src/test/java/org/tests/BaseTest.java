package org.tests;

import org.api.ApiClient;
import org.framework.DataManager;
import org.framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {


    protected WebDriver driver;
    public LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver("chrome");
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(DataManager.getUrl());

        loginPage = new LoginPage(driver);
    }

    @AfterSuite
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
