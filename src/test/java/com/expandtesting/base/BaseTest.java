package com.expandtesting.base;

import com.demoqa.pages.DemoqaPage;
import main.mainTest;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest extends mainTest {
    private static final String DEMOQA_URL = "https://practice.expandtesting.com/dynamic-table?utm_source=chatgpt.com";

    @BeforeMethod
    public void setUp() {
        logger.info("Navigating to Expand Testing Dynamic Table...");

        // Initialize DemoqaPage (inherits BasePage)
        basePage = new DemoqaPage();
        basePage.setDriver(driver);
        basePage.navigateToUrl(DEMOQA_URL);

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger.info("Navigation complete. Test ready.");
    }
}
