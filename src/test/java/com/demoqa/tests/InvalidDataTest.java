package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.DemoqaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidDataTest extends BaseTest {

    DemoqaPage demoqaPage = new DemoqaPage();

    @Test
    public void validateFormDoesNotSubmitWithoutRequiredFields() throws InterruptedException {
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted with empty required fields but it was displayed."
        );
    }

    @Test
    public void validateFormWithOnlyFirstName() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "",
                "",
                "",
                "",
                "",
                "",
                new String[]{},
                "",
                "",
                "",
                ""
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted with only First Name but it was displayed."
        );
    }

    @Test
    public void validateFormWithFirstAndLastName() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "",
                "",
                "",
                "",
                "",
                new String[]{},
                "",
                "",
                "",
                ""
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without email but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutEmail() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without email but it was displayed."
        );
    }

    @Test
    public void validateFormWithInvalidEmail() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "invalid-email",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted with invalid email format but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutPhoneNumber() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without phone number but it was displayed."
        );
    }

    @Test
    public void validateFormWithInvalidPhoneNumber() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "123",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted with invalid phone number but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutGender() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without gender selection but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutDateOfBirth() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without date of birth but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutSubject() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "26 May 1999",
                "",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without subject but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutHobbies() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without hobbies but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutFileUpload() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without file upload but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutState() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without state selection but it was displayed."
        );
    }

    @Test
    public void validateFormWithoutCity() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                ""
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertFalse(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup should NOT be displayed when form is submitted without city selection but it was displayed."
        );
    }

    @Test
    public void validateFormWithValidData() throws InterruptedException {
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports", "Reading", "Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"
        );
        demoqaPage.submitForm();
        Thread.sleep(1000);
        boolean isPopupDisplayed = false;
        try {
            isPopupDisplayed = demoqaPage.registerValidation();
        } catch (Exception e) {
            isPopupDisplayed = false;
        }
        Assert.assertTrue(
                isPopupDisplayed,
                "ASSERTION FAILED: Registration popup SHOULD be displayed when form is submitted with valid data but it was not displayed."
        );
    }
}