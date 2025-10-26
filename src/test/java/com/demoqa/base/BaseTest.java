package com.demoqa.base;
import com.demoqa.pages.DemoqaPage;
import main.mainTest;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;


public class BaseTest extends mainTest {
    String DEMOQA_URL = "https://demoqa.com/automation-practice-form";

    @BeforeMethod
    public void setUp(){

        basePage = new DemoqaPage();
        basePage.setDriver(getDriver());
        basePage.navigateToUrl(DEMOQA_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
}
