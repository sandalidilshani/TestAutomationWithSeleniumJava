package com.expandtesting.tests;

import com.demoqa.base.BaseTest;
import com.expandtesting.pages.TablePage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TestCase01_ValidateHeader extends BaseTest {

    TablePage tablePage = new TablePage();
    @Test
    public void validateTableHeader(){
        System.out.println(tablePage.getTableHeaders());
        List<String>headers=tablePage.getTableHeaders();
        assertFalse(headers.isEmpty(),"Table headers should not be null");
        assertTrue(headers.size() > 0, "Table should have at least one header");
        assertEquals(5, headers.size(), "Table should have exactly 4 headers");

    }


}
