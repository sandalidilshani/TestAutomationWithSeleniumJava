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
        shortTermCalc=new ShortTermParkingHourlyCalc();
    }

    @Test(description = "Short-Term Parking Hourly Calculation")
    public void verifyShortTermParkingHourlyCalculation() {
        logger.info("Starting Short-Term Parking hourly cost test");

        try {
            parkCalcPage.chooseParkingLot("Short");
            String selectedType = parkCalcPage.chekSlectedType();
            Assert.assertTrue(selectedType.contains("Short-Term Parking"), "Wrong parking type selected!");

            parkCalcPage.openStartClender();
            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(isOpened, "Start calendar popup did not open!");
            parkCalcPage.setDate(2025, "23", "May");
            selectedDate = parkCalcPage.SelectedDate();
            logger.info("Start Date: " + selectedDate);

            parkCalcPage.openLeaveClender();
            isOpened = parkCalcPage.isCalenderPopUpOpned();
            Assert.assertTrue(isOpened, "Leaving calendar popup did not open!");
            parkCalcPage.setDate(2025, "23", "May");
            selectedDate = parkCalcPage.SelectedDate();
            logger.info("Leaving Date: " + selectedDate);

            boolean isSame=shortTermCalc.validateSameDate("05/23/2025", "05/28/2025");
            Assert.assertTrue(isSame, "❌ Start and end dates must be the same!");

            parkCalcPage.setStartTime("10:00");
            parkCalcPage.setEndTime("13:00");
            parkCalcPage.calculate();

            String actualCost = parkCalcPage.getTotalCost();
            int actualHours = parkCalcPage.getTotalHours();

            logger.info("Duration: " + actualHours + " hours");
            logger.info("Cost: " + actualCost);

            String expectedCost = "$ 6.00";
            int expectedHours = 3;

            Assert.assertEquals(actualCost, expectedCost, "Total cost mismatch!");
            Assert.assertEquals(actualHours, expectedHours, "Total hours mismatch!");

            logger.info("✅ Short-Term Parking hourly calculation passed successfully");

        } catch (Exception e) {
            logger.error("❌ Short-Term Parking hourly calculation failed", e);
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
