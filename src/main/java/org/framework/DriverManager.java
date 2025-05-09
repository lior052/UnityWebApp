package org.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    // ThreadLocal is used to ensure that each thread has its own instance of WebDriver
    // This is important in a multi-threaded environment, such as when running tests in parallel
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void initDriver(String browser) {
        if (browser == null)
            browser = "chrome";

        switch (browser.toLowerCase()) {
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver.set(new FirefoxDriver());
                break;

            default:
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
        }

        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
