package com.expandtesting.tests;

import com.demoqa.base.BaseTest;
import com.expandtesting.pages.TablePage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TestCase02_ValidateRow extends BaseTest {

    TablePage tablePage = new TablePage();
    @Test
    public void validateTableHeader() throws InterruptedException {

       int beforerefreshcount=tablePage.tableRowCount();
       assertTrue(beforerefreshcount > 0, "Table should have at least one row");

       List<String> initialRows = tablePage.getTableRows();
       String firstRowInitialData = initialRows.get(0);
       assertNotNull(firstRowInitialData, "Row data should not be null");

       driver.navigate().refresh();
        Thread.sleep(1000);

        int afterRefreshCount= tablePage.tableRowCount();
       assertEquals(beforerefreshcount, afterRefreshCount,"count should be equla");


    }


}
