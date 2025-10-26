package com.parkcalc.tests;

import com.parkcalc.base.BaseTest;
import com.parkcalc.pages.ParkCalcPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase02_ShortTermParkingHourlyCalc extends BaseTest {

    private ParkCalcPage parkCalcPage;
    boolean isOpened;
    String selectedDate;

    @BeforeMethod
    public void setUpPage() {
        // 🟢 Always create the page object before each test
        parkCalcPage = new ParkCalcPage();
    }

    @Test(description = "Test Case 1: Valet Parking Daily Calculation")
    public void chooseParkingLot() {
        logger.info("Test01 - TestCase01 started");

        try {
            logger.info("Selecting 'Valet Parking'...");
            parkCalcPage.chooseParkingLot("Valet");

            // Check Selection
            String selectedType = parkCalcPage.chekSlectedType();
            Assert.assertTrue(selectedType.contains("Valet Parking"));
            logger.info("Test01 - Passed ✅");

            parkCalcPage.openStartClender();
            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(isOpened, "❌ Calendar popup did not open!");
            parkCalcPage.setDate(2025,"23","May");
            selectedDate=parkCalcPage.SelectedDate();

            parkCalcPage.openLeaveClender();
            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(isOpened, "❌ Calendar popup did not open!");
            parkCalcPage.setDate(2025,"23","May");
            selectedDate=parkCalcPage.SelectedDate();

            parkCalcPage.setStartTime("10.00");
            parkCalcPage.setEndTime("13.00");

            parkCalcPage.calculate();

            String actualCost = parkCalcPage.getTotalCost();
            int actualHours = parkCalcPage.getTotalHours();

            Assert.assertEquals(actualCost, "$ 12.00", "❌ Total cost mismatch!");
            Assert.assertEquals(actualHours, 3, "❌ Total hours mismatch!");


        } catch (Exception e) {
            logger.error("Test01 - Failed ❌", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

}
