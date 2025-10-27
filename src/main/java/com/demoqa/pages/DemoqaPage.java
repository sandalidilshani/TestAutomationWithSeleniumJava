package com.demoqa.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public void setDateOfBirth(String date) throws InterruptedException {
        // Expected format: "26 October 1999"
        String[] parts = date.split(" ");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];
        int monthInt = getMonthIndex(month);


        WebElement dateInput = find(dateOfBirthInput);

        // Scroll the date input into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dateInput);
        Thread.sleep(500);

        // Close any visible ads/iframes by scrolling
        js.executeScript("window.scrollBy(0, 300);");
        Thread.sleep(500);

        click(dateOfBirthInput);
        Thread.sleep(1000);

        // Select year
        Select yearSelect = new Select(find(yearDropdown));
        yearSelect.selectByVisibleText(year);
        Thread.sleep(500);

        // Select month
        Select monthSelect = new Select(find(monthDropdown));
        monthSelect.selectByValue(String.valueOf(monthInt));
        Thread.sleep(500);

        // Select day with retry logic
        String dayXpath = "//div[@role='option' and contains(@class, 'react-datepicker__day') and not(contains(@class, 'outside-month')) and text()='" + day + "']";
        WebElement dayElement = driver.findElement(By.xpath(dayXpath));

        // Scroll day element into view
        js.executeScript("arguments[0].scrollIntoView(true);", dayElement);
        Thread.sleep(500);

        // Try to click with Actions if regular click fails
        try {
            dayElement.click();
        } catch (Exception e) {
            System.out.println("Regular click failed, trying with Actions...");
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(dayElement).click().perform();
            Thread.sleep(500);

        }
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
            String ePath = new File(imagePath).getAbsolutePath();
            System.out.println("Uploading file: " + ePath);
            WebElement fileInput = find(uploadPictureButton);
            fileInput.sendKeys(ePath);
            Thread.sleep(2000);

            List<WebElement> uploadedPath = driver.findElements(By.cssSelector("p#uploadedFilePath"));

            if (uploadedPath.size() > 0 && !uploadedPath.get(0).getText().isEmpty()) {
                System.out.println("File Uploaded successfully: " + uploadedPath.get(0).getText());
            } else {
                System.out.println(" verification failed");
            }

        } catch (Exception e) {
            System.out.println("File  error: " + e.getMessage());
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


    public boolean submitForm() {
        click(submitButton);
        return registerValidation();
    }

    public boolean registerValidation() {
        WebElement popup = find(popupReguster);
        return popup.isDisplayed();
    }






}