package com.parkcalc.tests;

import com.parkcalc.base.BaseTest;
import com.parkcalc.pages.ParkCalcPage;
import com.parkcalc.pages.ValetParkingCalc;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase01_ValetParkingCalc extends BaseTest {

    private ParkCalcPage parkCalcPage;
    private ValetParkingCalc valetParkingCalc;
    boolean isOpened;
    String selectedDate;

    @BeforeMethod
    public void setUpPage() {
        parkCalcPage = new ParkCalcPage();
        valetParkingCalc = new ValetParkingCalc();
    }

    @Test(description = "Test Case 1: Valet Parking Daily Calculation")
    public void chooseParkingLot() {
        logger.info("Test01 - TestCase01 started");

        try {
            logger.info("Selecting 'Valet Parking'...");
            parkCalcPage.chooseParkingLot("Valet");

            String selectedType = parkCalcPage.chekSlectedType();
            Assert.assertTrue(
                    selectedType.contains("Valet Parking"),
                    "ASSERTION FAILED: Valet Parking type was not selected. Expected to contain 'Valet Parking' but got: '" + selectedType + "'"
            );
            logger.info("Valet Parking type successfully selected");

            logger.info("Opening start date calendar...");
            parkCalcPage.openStartClender();

            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(
                    isOpened,
                    "ASSERTION FAILED: Start date calendar popup did not open. Expected popup to be visible but it was not."
            );
            logger.info("Start date calendar popup opened successfully");

            logger.info("Setting start date to May 23, 2025...");
            parkCalcPage.setDate(2025, "23", "May");
            selectedDate = parkCalcPage.SelectedDate();

            Assert.assertNotNull(
                    selectedDate,
                    "ASSERTION FAILED: Start date was not set. Expected a date value but got null."
            );
            logger.info("Start date successfully set to " + selectedDate);

            logger.info("Opening end date calendar...");
            parkCalcPage.openLeaveClender();

            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(
                    isOpened,
                    "ASSERTION FAILED: End date calendar popup did not open. Expected popup to be visible but it was not."
            );
            logger.info("End date calendar popup opened successfully");

            logger.info("Setting end date to May 23, 2025...");
            parkCalcPage.setDate(2025, "23", "May");
            selectedDate = parkCalcPage.SelectedDate();

            Assert.assertNotNull(
                    selectedDate,
                    "ASSERTION FAILED: End date was not set. Expected a date value but got null."
            );
            logger.info("End date successfully set to " + selectedDate);


            parkCalcPage.setStartTime("10.00");
            logger.info("Start time set to 10:00 AM");


            parkCalcPage.setEndTime("13.00");
            logger.info("End time set to 1:00 PM");


            parkCalcPage.calculate();
            logger.info("Calculate button clicked successfully");

            double actualCost = parkCalcPage.getTotalCost();
            int actualHours = parkCalcPage.getTotalHours();

            logger.info("Parking Duration: " + actualHours + " hours");
            logger.info("Calculated Cost: $" + actualCost);

            Assert.assertTrue(
                    actualHours > 0,
                    "ASSERTION FAILED: Parking duration is invalid. Expected hours to be greater than 0 but got: " + actualHours
            );


            Assert.assertTrue(
                    actualCost > 0,
                    "ASSERTION FAILED: Parking cost is invalid. Expected cost to be greater than 0 but got: $" + actualCost
            );


            double expectedCost = valetParkingCalc.calculate("10/23/2025", "10:00", "10/23/2025", "13:00");
            logger.info("Expected Cost: $" + expectedCost);

            Assert.assertEquals(
                    actualCost,
                    expectedCost,
                    "ASSERTION FAILED: Parking cost mismatch! Expected cost to be $" + expectedCost + " but got $" + actualCost
            );
            logger.info("Calculated cost matches expected cost ($" + actualCost + ")");

            logger.info("Test01 - ALL ASSERTIONS PASSED");

        } catch (Exception e) {
            logger.error("Test01 - FAILED ❌ - Exception occurred: " + e.getClass().getSimpleName(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage() + "\nStack trace: " + e);
        }
    }
}