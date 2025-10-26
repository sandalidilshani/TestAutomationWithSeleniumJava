package com.parkcalc.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

public class ParkCalcPage extends BasePage {
    private static final String PARKCALC_URL = "https://www.shino.de/parkcalc/";
    WebElement parkingTypeList = find(By.id("ParkingLot"));
    Select objSelect = new Select(parkingTypeList);
    private By selectStartDate = By.id("StartingDate");
    private By selectLeavingDate = By.id("LeavingDate");
    private By selectStartTime = By.id("StartingTime");
    private By selectEndTime = By.id("LeavingTime");
    private By Startcalender = By.cssSelector("a[href*='StartingDate'] img");
    private By Leavecalender= By.cssSelector("a[href*='LeavingDate'] img");
    private By CalculateButton =By.name("Submit");
    private By totalCost = By.cssSelector("span.SubHead b");
    private By durationText = By.cssSelector("span.BodyCopy b");



    public void chooseParkingLot(String type) {

        objSelect.selectByValue(type);

    }

    public String chekSlectedType() {
        String selectedType = objSelect.getFirstSelectedOption().getText();
        return selectedType;
    }


    public void setStartTime(String time) {
        set(selectStartTime, time);

    }

    public void setEndTime(String time) {
        set(selectEndTime, time);

    }

    public void openStartClender() {
        click(Startcalender);
    }
    public void openLeaveClender() {
        click(Leavecalender);
    }

    public boolean isCalenderPopUpOpned() {
        Set<String> allWindows = driver.getWindowHandles();
        return allWindows.size() > 1;
    }

    public void SwitchToPopUpWindow() {
        Set<String> allWindows = driver.getWindowHandles();
        String mainWindow = driver.getWindowHandle();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                return;
            }
        }
    }

    public void setDate(int year, String date ,String month) {
        SwitchToPopUpWindow();
        setYear(year);
        selectMonth(month);
        selectDay(date);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains("PARKING COST CALCULATOR")) { // main window check
                break;
            }
        }


    }

    public void setYear(int inputyear) {
        By yearTextLocator = By.xpath("//td[@align='right']//font/b");
        By prevYearButton = By.xpath("//a[contains(@href,'DecYear()')]");
        By nextYearButton = By.xpath("//a[contains(@href,'IncYear()')]");
        int currentYear = Integer.parseInt(find(yearTextLocator).getText().trim());
        while (currentYear != inputyear) {
            if (inputyear < currentYear) {
                click(prevYearButton); // Go back one year
            } else {
                click(nextYearButton); // Go forward one year
            }
            currentYear = Integer.parseInt(find(yearTextLocator).getText().trim());
        }

    }
    public void selectDay(String day) {
        // The calendar days are just <a> elements with text = day number
        By dayLocator = By.xpath("//a[text()='" + day + "']");
        click(dayLocator);
    }

    public void selectMonth(String inputmonth){
        WebElement month = find(By.name("MonthSelector"));
        Select objmonth = new Select(month);
        objmonth.selectByVisibleText(inputmonth);

    }

    public String SelectedDate(){
        String selectDate=find(selectStartDate).getText();
        return selectDate;

    }
    public void timeSetter(String timeinput){
        String[] parts = timeinput.split(":");
        int hour = Integer.parseInt(parts[0]);
        String minute = parts[1];

        String ampm;
        if (hour == 0) {
            hour = 12;
            ampm = "AM";
        } else if (hour < 12) {
            ampm = "AM";
        } else if (hour == 12) {
            ampm = "PM";
        } else {
            hour = hour- 12;
            ampm = "PM";
        }

        String time12 = String.format("%02d:%s", hour, minute);

        WebElement timeInput = find(By.id("StartingTime"));
        timeInput.clear();
        timeInput.sendKeys(time12);


        if (ampm.equals("AM")) click(By.xpath("//input[@name='StartingTimeAMPM' and @value='AM']"));
        else click(By.xpath("//input[@name='StartingTimeAMPM' and @value='PM']"));
    }
    public void calculate(){
        click(CalculateButton);
    }

    public String getTotalCost() {
        return find(totalCost).getText().trim();
    }

    public String getDuration() {
        return find(durationText).getText().trim();
    }

    public int getTotalHours() {
        String duration = getDuration(); //
        duration = duration.replaceAll("[()]", "");
        String[] parts = duration.split(",");

        int days = Integer.parseInt(parts[0].trim().split(" ")[0]);
        int hours = Integer.parseInt(parts[1].trim().split(" ")[0]);
        int minutes = Integer.parseInt(parts[2].trim().split(" ")[0]);

        return days * 24 + hours + (minutes > 0 ? 1 : 0);
    }





}

