package com.tempTestForReport.base;

import com.demoqa.pages.DemoqaPage;
import main.mainTest;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest extends mainTest {
    private static final String DEMOQA_URL = "https://demoqa.com/automation-practice-form";

    @BeforeMethod
    public void setUp() {
        logger.info("Opening DemoQA Automation Practice Form...");

        // Initialize DemoqaPage
        basePage = new DemoqaPage();
        basePage.setDriver(driver);
        basePage.navigateToUrl(DEMOQA_URL);

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger.info("DemoQA page loaded successfully.");
    }
}
