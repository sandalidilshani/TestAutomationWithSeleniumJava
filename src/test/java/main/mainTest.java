package main;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class mainTest {
    public Logger logger;
    protected WebDriver driver;

    // Define your base page (if you use a Page Object Model)
    protected BasePage basePage;
    @BeforeClass
    public void setup() {

        logger=LogManager.getLogger(this.getClass());
        // Start the Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        basePage = new BasePage();
        basePage.setDriver(driver);
    }





    @AfterClass
    public void tearDown() {
        // Close the browser after all tests finish
        if (driver != null) {
            //driver.quit();
        }
    }

    // Helper for child classes to access the WebDriver
    public WebDriver getDriver() {
        return driver;
    }
}
