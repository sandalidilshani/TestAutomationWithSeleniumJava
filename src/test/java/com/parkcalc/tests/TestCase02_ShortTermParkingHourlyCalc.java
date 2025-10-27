package com.parkcalc.tests;

import com.parkcalc.base.BaseTest;
import com.parkcalc.pages.ParkCalcPage;
import com.parkcalc.pages.ShortTermParkingHourlyCalc;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase02_ShortTermParkingHourlyCalc extends BaseTest {

    private ParkCalcPage parkCalcPage;
    private ShortTermParkingHourlyCalc shortTermCalc;
    boolean isOpened;
    String selectedDate;

    @BeforeMethod
    public void setUpPage() {
        parkCalcPage = new ParkCalcPage();
        shortTermCalc = new ShortTermParkingHourlyCalc();
    }

    @Test(description = "Short-Term Parking Hourly Calculation")
    public void verifyShortTermParkingHourlyCalculation() {
        logger.info("Starting Short-Term Parking hourly cost test");

        try {

            parkCalcPage.chooseParkingLot("Short");

            String selectedType = parkCalcPage.chekSlectedType();
            Assert.assertTrue(
                    selectedType.contains("Short-Term Parking"),
                    "ASSERTION FAILED: Short-Term Parking type was not selected. Expected to contain 'Short-Term Parking' but got: '" + selectedType + "'"
            );
            logger.info("Short-Term Parking type successfully selected");


            parkCalcPage.openStartClender();

            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(
                    isOpened,
                    "ASSERTION FAILED: Start date calendar popup did not open. Expected popup to be visible but it was not."
            );


            logger.info("Setting start date to May 23, 2025...");
            parkCalcPage.setDate(2025, "23", "May");
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



            parkCalcPage.setDate(2025, "23", "May");
            selectedDate = parkCalcPage.SelectedDate();

            Assert.assertNotNull(
                    selectedDate,
                    "ASSERTION FAILED: End date was not set. Expected a date value but got null."
            );



            boolean isSame = shortTermCalc.validateSameDate("05/23/2025", "05/23/2025");
            Assert.assertTrue(
                    isSame,
                    "ASSERTION FAILED: Start and end dates must be the same. Validation failed."
            );
            logger.info("Start and end dates are the same");


            parkCalcPage.setStartTime("10:00");
            parkCalcPage.setEndTime("13:00");



            parkCalcPage.calculate();

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


            double expectedCost = shortTermCalc.calculate(actualHours);


            Assert.assertEquals(
                    actualCost,
                    expectedCost,
                    "ASSERTION FAILED: Parking cost mismatch! Expected cost to be $" + expectedCost + " but got $" + actualCost
            );
            logger.info("Calculated cost matches expected cost ($" + actualCost + ")");

            logger.info("Test02 - ALL ASSERTIONS PASSED");

        } catch (Exception e) {
            logger.error("Test02 - FAILED - Exception occurred: " + e.getClass().getSimpleName(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage() + "\nStack trace: " + e);
        }
    }
}