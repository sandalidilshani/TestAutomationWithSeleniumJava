package com.demoqa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationValidation extends DemoqaPage {
    public String getPopupStudentName() {
        WebElement studentNameCell = driver.findElement(By.xpath("//td[text()='Student Name']/following-sibling::td"));
        return studentNameCell.getText();
    }

    public String getPopupStudentEmail() {
        WebElement emailCell = driver.findElement(By.xpath("//td[text()='Student Email']/following-sibling::td"));
        return emailCell.getText();
    }

    public String getPopupGender() {
        WebElement genderCell = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td"));
        return genderCell.getText();
    }

    public String getPopupMobile() {
        WebElement mobileCell = driver.findElement(By.xpath("//td[text()='Mobile']/following-sibling::td"));
        return mobileCell.getText();
    }

    public String getPopupDateOfBirth() {
        WebElement dobCell = driver.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td"));
        return dobCell.getText();
    }

    public String getPopupSubjects() {
        WebElement subjectsCell = driver.findElement(By.xpath("//td[text()='Subjects']/following-sibling::td"));
        return subjectsCell.getText();
    }

    public String getPopupHobbies() {
        WebElement hobbiesCell = driver.findElement(By.xpath("//td[text()='Hobbies']/following-sibling::td"));
        return hobbiesCell.getText();
    }

    public String getPopupPicture() {
        WebElement pictureCell = driver.findElement(By.xpath("//td[text()='Picture']/following-sibling::td"));
        return pictureCell.getText();
    }

    public String getPopupAddress() {
        WebElement addressCell = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td"));
        return addressCell.getText();
    }

    public String getPopupStateAndCity() {
        WebElement stateAndCityCell = driver.findElement(By.xpath("//td[text()='State and City']/following-sibling::td"));
        return stateAndCityCell.getText();
    }

    public void validatePopupData(String firstName, String lastName, String email, String gender,
                                  String mobile, String dob, String subjects, String hobbies,
                                  String address, String state, String city) {

        String expectedStudentName = firstName + " " + lastName;
        String actualStudentName = getPopupStudentName();

        System.out.println("Student Name - Expected: " + expectedStudentName + ", Actual: " + actualStudentName);
        assert actualStudentName.equalsIgnoreCase(expectedStudentName) :
                "Student Name mismatch! Expected: " + expectedStudentName + ", but got: " + actualStudentName;

        String actualEmail = getPopupStudentEmail();
        System.out.println("Email - Expected: " + email + ", Actual: " + actualEmail);
        assert actualEmail.equalsIgnoreCase(email) :
                "Email mismatch! Expected: " + email + ", but got: " + actualEmail;

        String actualGender = getPopupGender();
        System.out.println("Gender - Expected: " + gender + ", Actual: " + actualGender);
        assert actualGender.equalsIgnoreCase(gender) :
                "Gender mismatch! Expected: " + gender + ", but got: " + actualGender;

        String actualMobile = getPopupMobile();
        System.out.println("Mobile - Expected: " + mobile + ", Actual: " + actualMobile);
        assert actualMobile.equals(mobile) :
                "Mobile mismatch! Expected: " + mobile + ", but got: " + actualMobile;

        String actualDob = getPopupDateOfBirth().replaceAll(","," ");
        String removeDob = actualDob.replaceAll(",", " ");
        System.out.println("Date of Birth - Expected: " + removeDob + ", Actual: " + actualDob);
        assert actualDob.contains(removeDob) :
                "Date of Birth mismatch! Expected: " + removeDob + ", but got: " + actualDob;

        String actualSubjects = getPopupSubjects();
        System.out.println("Subjects - Expected: " + subjects + ", Actual: " + actualSubjects);
        if (subjects != null && !subjects.isEmpty()) {
            assert actualSubjects.equalsIgnoreCase(subjects) :
                    "Subjects mismatch! Expected: " + subjects + ", but got: " + actualSubjects;
        }

        String actualHobbies = getPopupHobbies();
        System.out.println("Hobbies - Expected: " + hobbies + ", Actual: " + actualHobbies);
        assert actualHobbies.equalsIgnoreCase(hobbies) :
                "Hobbies mismatch! Expected: " + hobbies + ", but got: " + actualHobbies;

        String actualAddress = getPopupAddress();
        System.out.println("Address - Expected: " + address + ", Actual: " + actualAddress);
        assert actualAddress.contains(address) :
                "Address mismatch! Expected: " + address + ", but got: " + actualAddress;

        String expectedStateAndCity = state + " " + city;
        String actualStateAndCity = getPopupStateAndCity();
        System.out.println("State and City - Expected: " + expectedStateAndCity + ", Actual: " + actualStateAndCity);
        assert actualStateAndCity.equalsIgnoreCase(expectedStateAndCity) :
                "State and City mismatch! Expected: " + expectedStateAndCity + ", but got: " + actualStateAndCity;

    }

}
