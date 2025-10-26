package com.parkcalc.tests;

import com.parkcalc.base.BaseTest;
import com.parkcalc.pages.LongTermGarageWeeklyCalc;
import com.parkcalc.pages.ParkCalcPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase03_LongTermGarageWeeklyCalc extends BaseTest {

    private ParkCalcPage parkCalcPage;
    private LongTermGarageWeeklyCalc longTermCalc;
    boolean isOpened;
    String selectedDate;

    @BeforeMethod
    public void setUpPage() {
        parkCalcPage = new ParkCalcPage();
        longTermCalc = new LongTermGarageWeeklyCalc();
    }

    @Test(description = "Long-Term Garage Weekly Parking Calculation")
    public void verifyLongTermGarageWeeklyCalculation() {
        logger.info("Starting Long-Term Garage Parking weekly cost test");

        try {
            // Select the correct parking type
            parkCalcPage.chooseParkingLot("Long-Garage");
            String selectedType = parkCalcPage.chekSlectedType();
            Assert.assertTrue(selectedType.contains("Long-Term Garage Parking"), "Wrong parking type selected!");

            // Select entry date
            parkCalcPage.openStartClender();
            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(isOpened, "Start calendar popup did not open!");
            parkCalcPage.setDate(2025, "20", "October");
            selectedDate = parkCalcPage.SelectedDate();
            logger.info("Start Date: " + selectedDate);

            // Select exit date
            parkCalcPage.openLeaveClender();
            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(isOpened, "Leaving calendar popup did not open!");
            parkCalcPage.setDate(2025, "29", "October");
            selectedDate = parkCalcPage.SelectedDate();
            logger.info("Leaving Date: " + selectedDate);

            // Set entry and exit times
            parkCalcPage.setStartTime("08:00");
            parkCalcPage.setEndTime("08:00");
            parkCalcPage.calculate();

            // Get actual cost and duration from the app
            double actualCost = parkCalcPage.getTotalCost();
            int actualHours = parkCalcPage.getTotalHours();

            logger.info("Duration: " + actualHours + " hours");
            logger.info("Displayed Cost: " + actualCost);

            double expectedCost = longTermCalc.calculate("10/20/2025", "08:00", "10/27/2025", "08:00");


            logger.info("Expected Cost: " + expectedCost);

            Assert.assertEquals(actualCost, expectedCost, "Total cost mismatch!");
            Assert.assertEquals(expectedCost, 72.0, "Expected weekly rate should be $72.00");
            logger.info("✅ Long-Term Garage weekly calculation passed successfully");

        } catch (Exception e) {
            logger.error("❌ Long-Term Garage weekly calculation failed", e);
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
