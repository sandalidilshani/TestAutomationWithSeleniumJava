package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.DemoqaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase01_Valid extends BaseTest {

    DemoqaPage demoqaPage= new DemoqaPage();

    @Test
    public void ValidForm()throws InterruptedException{
        System.out.println("Page Title: " + driver.getTitle());
        demoqaPage.registartionForm(
                "Sandali",
                "dilshani",
                "rkpsandali@gmail.com",
                "Male",
                "0711579375",
                "26 May 1999",
                "Maths",
                new String[]{"Sports","Reading","Music"},
                "src/test/resources/personImages/1.jpeg",
                "jayanthi",
                "Rajasthan",
                "Jaipur"

        );
        demoqaPage.submitForm();
        Assert.assertTrue(true,"registration sucessfully");


    }



}
