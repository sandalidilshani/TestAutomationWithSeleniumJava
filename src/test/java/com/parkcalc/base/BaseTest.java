package com.parkcalc.base;

import com.parkcalc.pages.ParkCalcPage;
import main.mainTest;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends mainTest {

    private static final String PARKCALC_URL = "https://www.shino.de/parkcalc/";

    @BeforeMethod
    public void loadApp() {
        logger.info("Opening ParkCalc application...");

        // Navigate to site
        driver.get(PARKCALC_URL);

        // Initialize the ParkCalc page object
        basePage = new ParkCalcPage();
        basePage.setDriver(driver);

        logger.info("ParkCalc application loaded successfully.");
    }
}
