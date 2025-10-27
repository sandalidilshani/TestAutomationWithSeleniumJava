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

            parkCalcPage.chooseParkingLot("Long-Garage");

            String selectedType = parkCalcPage.chekSlectedType();
            Assert.assertTrue(
                    selectedType.contains("Long-Term Garage Parking"),
                    "ASSERTION FAILED: Long-Term Garage Parking type was not selected. Expected to contain 'Long-Term Garage Parking' but got: '" + selectedType + "'"
            );



            parkCalcPage.openStartClender();

            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(
                    isOpened,
                    "ASSERTION FAILED: Start date calendar popup did not open. Expected popup to be visible but it was not."
            );



            parkCalcPage.setDate(2025, "20", "October");
            selectedDate = parkCalcPage.SelectedDate();

            Assert.assertNotNull(
                    selectedDate,
                    "ASSERTION FAILED: Start date was not set. Expected a date value but got null."
            );



            parkCalcPage.openLeaveClender();
            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(
                    isOpened,
                    "ASSERTION FAILED: End date calendar popup did not open. Expected popup to be visible but it was not."
            );



            parkCalcPage.setDate(2025, "29", "October");
            selectedDate = parkCalcPage.SelectedDate();

            Assert.assertNotNull(
                    selectedDate,
                    "ASSERTION FAILED: End date was not set. Expected a date value but got null."
            );



            parkCalcPage.setStartTime("08:00");
            parkCalcPage.setEndTime("08:00");



            parkCalcPage.calculate();
            logger.info("Calculate button clicked successfully");

            double actualCost = parkCalcPage.getTotalCost();
            int actualHours = parkCalcPage.getTotalHours();

            Assert.assertTrue(
                    actualHours > 0,
                    "ASSERTION FAILED: Parking duration is invalid. Expected hours to be greater than 0 but got: " + actualHours
            );
            logger.info("Parking duration is valid (" + actualHours + " hours)");

            Assert.assertTrue(
                    actualCost > 0,
                    "ASSERTION FAILED: Parking cost is invalid. Expected cost to be greater than 0 but got: $" + actualCost
            );
            logger.info("Parking cost is valid ($" + actualCost + ")");

            double expectedCost = longTermCalc.calculate("10/20/2025", "08:00", "10/27/2025", "08:00");
            logger.info("Expected Cost: " + expectedCost);

            Assert.assertEquals(
                    actualCost,
                    expectedCost,
                    "ASSERTION FAILED: Parking cost mismatch! Expected cost to be $" + expectedCost + " but got $" + actualCost
            );
            logger.info("Calculated cost matches expected cost ($" + actualCost + ")");

            Assert.assertEquals(
                    expectedCost,
                    72.0,
                    "ASSERTION FAILED: Weekly parking rate is incorrect. Expected cost to be $72.00 but got $" + expectedCost
            );

            logger.info("Test03 - ALL ASSERTIONS PASSED");

        } catch (Exception e) {
            logger.error("Test03 - FAILED - Exception occurred: " + e.getClass().getSimpleName(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage() + "\nStack trace: " + e);
        }
    }
}