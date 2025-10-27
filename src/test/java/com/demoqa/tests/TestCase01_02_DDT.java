package com.demoqa.tests;

import com.demoqa.pages.DemoqaPage;
import com.demoqa.pages.RegistrationValidation;
import com.tempTestForReport.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestCase01_02_DDT extends BaseTest {

    @DataProvider(name = "formData")
    public Object[][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir") +
                "/src/test/resources/demoQAFormData/formDetails.xlsx";
        System.out.println("Attempting file path: " + filePath);

        java.io.File file = new java.io.File(filePath);

        if (!file.exists()) {
            throw new IOException("Excel file not found at: " + filePath);
        }

        System.out.println("File found at: " + filePath);
        utils.ExcelUtils.loadExcel(filePath, "details");
        int rowCount = utils.ExcelUtils.getRowCount();
        System.out.println("Total rows in Excel: " + rowCount);

        Object[][] data = new Object[rowCount - 1][14];

        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = utils.ExcelUtils.getCellData(i, 0);
            data[i - 1][1] = utils.ExcelUtils.getCellData(i, 1);
            data[i - 1][2] = utils.ExcelUtils.getCellData(i, 2);
            data[i - 1][3] = utils.ExcelUtils.getCellData(i, 3);
            data[i - 1][4] = utils.ExcelUtils.getCellData(i, 4);
            data[i - 1][5] = utils.ExcelUtils.getCellData(i, 5);
            data[i - 1][6] = utils.ExcelUtils.getCellData(i, 6);
            data[i - 1][7] = utils.ExcelUtils.getCellData(i, 7);
            data[i - 1][8] = utils.ExcelUtils.getCellData(i, 8);
            data[i - 1][9] = utils.ExcelUtils.getCellData(i, 9);
            data[i - 1][10] = utils.ExcelUtils.getCellData(i, 10);
            data[i - 1][11] = utils.ExcelUtils.getCellData(i, 11);
            data[i - 1][12] = utils.ExcelUtils.getCellData(i, 12);
            data[i - 1][13] = utils.ExcelUtils.getCellData(i, 13);

            System.out.println("Row " + i + " - RecordType: " + data[i - 1][0] +
                    ", FirstName: " + data[i - 1][1] +
                    ", LastName: " + data[i - 1][2] +
                    ", Email: " + data[i - 1][3] +
                    ", Gender: " + data[i - 1][4] +
                    ", Mobile: " + data[i - 1][5]);
        }

        utils.ExcelUtils.closeExcel();
        System.out.println("Total test data sets loaded: " + data.length);
        return data;
    }

    DemoqaPage demoqaPage = new DemoqaPage();
    RegistrationValidation registrationValidation=new RegistrationValidation();

    @Test(dataProvider = "formData")
    public void FormTesting(String recordType, String firstName, String lastName, String email,
                            String gender, String mobile, String dob, String subjects,
                            String hobbies, String picture, String address, String state,
                            String city, String invalidityReason) throws InterruptedException {

        logger.info("Page Title: " + driver.getTitle());
        logger.info("Running test with - RecordType: " + recordType +
                ", FirstName: " + firstName +
                ", LastName: " + lastName +
                ", Email: " + email +
                ", Gender: " + gender +
                ", DOB: " + dob +
                ", Subjects: " + subjects +
                ", Hobbies: " + hobbies +
                ", Mobile: " + mobile);

        try {
            String[] hobbiesArray = hobbies != null ? hobbies.split(",\\s*") : new String[0];

            demoqaPage.registartionForm(
                    firstName,
                    lastName,
                    email,
                    gender,
                    mobile,
                    dob,
                    subjects,
                    hobbiesArray,
                    "src/test/resources/personImages/1.jpeg",
                    address,
                    state,
                    city
            );

            boolean registerStatus = false;
            try {
                registerStatus = demoqaPage.submitForm();
            } catch (Exception e) {
                registerStatus = false;
            }

            if (recordType.equalsIgnoreCase("Valid")) {
                Assert.assertTrue(
                        registerStatus,
                        "Form submission should succeed for VALID record type but it failed. Reason: " + invalidityReason
                );
                logger.info("PASS: Form successfully submitted with valid data - RecordType:" + recordType);

                registrationValidation.validatePopupData(firstName, lastName, email, gender, mobile, dob, subjects, hobbies, address, state, city);

            } else if (recordType.equalsIgnoreCase("Invalid")) {
                Assert.assertFalse(
                        registerStatus,
                        "ASSERTION FAILED: Form submission should fail for INVALID record type but it succeeded. Reason: " + invalidityReason
                );
                logger.info("PASS: Form submission blocked for invalid data - RecordType: " + recordType + ", Reason: " + invalidityReason);

            } else {
                Assert.fail("ASSERTION FAILED: Unknown record type: " + recordType + ". Expected 'Valid' or 'Invalid'");
            }

        } catch (Exception e) {
            logger.error("Test FAILED" + e.getClass().getSimpleName(), e);
            Assert.fail("Test failed" + e.getMessage());
        }
    }
}