package com.parkcalc.base;

import base.BasePage;
import com.parkcalc.pages.ParkCalcPage;
import main.mainTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends mainTest {


    // The site to test
    private  String PARKCALC_URL = "https://www.shino.de/parkcalc/";

    @BeforeMethod
    public void LoadingApp(){
        driver.get(PARKCALC_URL);
        ParkCalcPage parkCalcPage = new ParkCalcPage();
        parkCalcPage.setDriver(getDriver());
    }


}
