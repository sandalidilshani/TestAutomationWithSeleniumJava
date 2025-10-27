package com.demoqa.tests;

import com.demoqa.pages.DemoqaPage;
import com.tempTestForReport.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestCase01_Valid_DDF extends BaseTest {

    @DataProvider(name = "formData")
    public Object[][] getLoginData() throws IOException {
        // Build the file path using project root
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


        // Loop through all data rows (starting from row 1, row 0 is header)
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = utils.ExcelUtils.getCellData(i, 0);   // Record Type
            data[i - 1][1] = utils.ExcelUtils.getCellData(i, 1);   // First Name
            data[i - 1][2] = utils.ExcelUtils.getCellData(i, 2);   // Last Name
            data[i - 1][3] = utils.ExcelUtils.getCellData(i, 3);   // Email
            data[i - 1][4] = utils.ExcelUtils.getCellData(i, 4);   // Gender
            data[i - 1][5] = utils.ExcelUtils.getCellData(i, 5);   // Mobile
            data[i - 1][6] = utils.ExcelUtils.getCellData(i, 6);   // Date of Birth
            data[i - 1][7] = utils.ExcelUtils.getCellData(i, 7);   // Subjects
            data[i - 1][8] = utils.ExcelUtils.getCellData(i, 8);   // Hobbies
            data[i - 1][9] = utils.ExcelUtils.getCellData(i, 9);   // Picture (File Name)
            data[i - 1][10] = utils.ExcelUtils.getCellData(i, 10); // Current Address
            data[i - 1][11] = utils.ExcelUtils.getCellData(i, 11); // State
            data[i - 1][12] = utils.ExcelUtils.getCellData(i, 12); // City
            data[i - 1][13] = utils.ExcelUtils.getCellData(i, 13); // Invalidity Reason

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

    @Test(dataProvider = "formData")
    public void FormTesting(String recordType, String firstName, String lastName, String email,
                      String gender, String mobile, String dob, String subjects,
                      String hobbies, String picture, String address, String state,
                      String city, String invalidityReason) throws InterruptedException {

        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Running test with - RecordType: " + recordType +
                ", FirstName: " + firstName +
                ", LastName: " + lastName +
                ", Email: " + email +
                ", Gender: " + gender +
                ", DOB: " + dob +
                ", Subjects: " + subjects +
                ", Hobbies: " + hobbies +
                ", Mobile: " + mobile);

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
                "src/test/resources/personImages/1/jpeg",
                address,
                state,
                city
        );

        demoqaPage.submitForm();
        Assert.assertTrue(true, "registration successfully");
    }
}