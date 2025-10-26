package com.demoqa.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.io.File;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class DemoqaPage extends BasePage {

    // Locators for the form fields
    private final By firstNameField = By.id("firstName");
    private final By lastNameField = By.id("lastName");
    private final By userEmailField = By.id("userEmail");
    private final By genderMaleRadio = By.xpath("//label[@for='gender-radio-1']");
    private final By genderFemaleRadio = By.xpath("//label[@for='gender-radio-2']");
    private final By genderOtherRadio = By.xpath("//label[@for='gender-radio-3']");
    private final By userNumberField = By.id("userNumber");
    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By yearDropdown = By.className("react-datepicker__year-select");
    private final By monthDropdown = By.className("react-datepicker__month-select");
    private final By dateDropdown = By.className("react-datepicker__day");
    private final By subjectsInput = By.id("subjectsInput");
    private final By hobbiesSportsCheckbox = By.xpath("//label[text()='Sports']");
    private final By currentAddressField = By.id("currentAddress");
    private final By SportLabel = By.xpath("//label[@for='hobbies-checkbox-1']");
    private final By ReadingLabel = By.xpath("//label[@for='hobbies-checkbox-2']");
    private final By MusicLabel = By.xpath("//label[@for='hobbies-checkbox-3']");
    private final By uploadPictureButton = By.id("uploadPicture");
    private final By stateContainer = By.id("state");
    private final By cityContainer = By.id("city");
    private final By submitButton = By.id("submit");
    private final By popupReguster = By.className("modal-content");


    // Methods to interact with the form

    public void registartionForm(String fristName, String lastName, String email, String gender, String mobile, String dateOfBirth,
                                 String subject, String[] hobbies, String imagePath, String address, String state, String city) throws InterruptedException {
        set(firstNameField, fristName);
        set(lastNameField, lastName);
        setUserEmail(email);
        setGender(gender);
        setUserNumber(mobile);
        setDateOfBirth(dateOfBirth);
        setSubjects(subject);
        selectHobbies(hobbies);
        uploadProfileImage(imagePath);
        setCurrentAddress(address);
        setState(state);
        setCity(city);
    }

    public void setGender(String gender){

        switch (gender.toLowerCase()){
            case "male":
                click(genderMaleRadio);
                break;
            case "female":
                click(genderFemaleRadio);
                break;
            case "other":
                click(genderOtherRadio);
                break;
        }

    }



    public void setUserEmail(String email) {
        set(userEmailField, email);
    }

    public void setUserNumber(String userNumber) {
        set(userNumberField, userNumber);
    }

    private int getMonthIndex(String monthName) {
        return switch (monthName.toLowerCase()) {
            case "january" -> 0;
            case "february" -> 1;
            case "march" -> 2;
            case "april" -> 3;
            case "may" -> 4;
            case "june" -> 5;
            case "july" -> 6;
            case "august" -> 7;
            case "september" -> 8;
            case "october" -> 9;
            case "november" -> 10;
            case "december" -> 11;
            default -> throw new IllegalArgumentException("Invalid month: " + monthName);
        };
    }

    public void setDateOfBirth(String date) {
        // Expected format: "26 October 1999"
        String[] parts = date.split(" ");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];
        int monthInt = getMonthIndex(month);


        // Click input to open datepicker
        click(dateOfBirthInput);

        // Select year
        Select yearSelect = new Select(find (yearDropdown));
        yearSelect.selectByVisibleText(year);

        // Select month
        Select monthSelect = new Select(find(monthDropdown));
        monthSelect.selectByValue(new String(String.valueOf(monthInt)));

        // Select day
        String dayXpath = "//div[@role='option' and contains(@class, 'react-datepicker__day') and not(contains(@class, 'outside-month')) and text()='" + day + "']";
        driver.findElement(By.xpath(dayXpath)).click();

    }


    public void setSubjects(String subject) {
        set(subjectsInput, subject);
        // After typing, you might need to press Enter or select from a dropdown
        find(subjectsInput).sendKeys(Keys.ENTER);
    }

    public void selectHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            switch (hobby.toLowerCase()) {
                case "sports":
                    click(SportLabel);
                    break;
                case "reading":
                    click(ReadingLabel);
                    break;
                case "music":
                    click(MusicLabel);
                    break;

            }
            ;
        }}


    public void uploadProfileImage(String imagePath) throws InterruptedException {
        try {
            // Get absolute path
            String absolutePath = new File(imagePath).getAbsolutePath();
            System.out.println("Uploading file: " + absolutePath);

            // Find the file input element using your find() method
            WebElement fileInput = find(uploadPictureButton);

            // Send the file path directly
            fileInput.sendKeys(absolutePath);

            // Wait for upload to complete
            Thread.sleep(2000);

            // Verify upload was successful
            List<WebElement> uploadedPath = driver.findElements(By.cssSelector("p#uploadedFilePath"));

            if (uploadedPath.size() > 0 && !uploadedPath.get(0).getText().isEmpty()) {
                System.out.println("✓ File Uploaded successfully: " + uploadedPath.get(0).getText());
            } else {
                System.out.println("✗ Upload verification failed");
            }

        } catch (Exception e) {
            System.out.println("✗ File upload error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setCurrentAddress(String address) {
        set(currentAddressField, address);
    }

    public void setState(String state) {
        WebElement stateInput = find(stateContainer);
        WebElement reactSelectInput = driver.findElement(By.id("react-select-3-input"));
        reactSelectInput.sendKeys(state);

        // Navigate and select
        reactSelectInput.sendKeys(Keys.ARROW_DOWN);
        reactSelectInput.sendKeys(Keys.ENTER);

    }
    public void setCity(String city) {
        WebElement citContain = find(cityContainer);
        WebElement reactSelectInput = driver.findElement(By.id("react-select-4-input"));
        reactSelectInput.sendKeys(city);

        // Navigate and select
        reactSelectInput.sendKeys(Keys.ARROW_DOWN);
        reactSelectInput.sendKeys(Keys.ENTER);

    }


    public void submitForm() {
        click(submitButton);
    }

    public boolean registerValidation() {
        WebElement popup = find(popupReguster);
        return popup.isDisplayed();

    }



}