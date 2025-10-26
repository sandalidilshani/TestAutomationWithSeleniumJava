package com.demoqa.tests;

import com.demoqa.pages.DemoqaPage;
import com.tempTestForReport.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.tempTestForReport.tests.My.class)
public class temp extends BaseTest {

    DemoqaPage demoqaPage= new DemoqaPage();



    @Test
    public void temp(){
        demoqaPage.setState("Rasa");
        demoqaPage.setCity("Jaipur");


    }

}
