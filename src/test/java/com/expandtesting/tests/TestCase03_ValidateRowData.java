package com.expandtesting.tests;

import com.demoqa.base.BaseTest;
import com.expandtesting.pages.TablePage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TestCase03_ValidateRowData extends BaseTest {

    TablePage tablePage = new TablePage();

    @Test
    public void validateRandomRowData(){

        int rowCount = tablePage.tableRowCount();
        assertTrue(rowCount > 0, "Table should have at least one row");

        List<String> rowData = tablePage.getRandomCellData();
        assertNotNull(rowData, "Row data should not be null");


        String name = rowData.get(0);
        String memory = rowData.get(1);
        String disk = rowData.get(2);
        String network = rowData.get(3);
        String cpu = rowData.get(4);

        assertFalse(name.isEmpty(), "Name should have value");
        assertFalse(memory.isEmpty(), "Memory should have value");
        assertFalse(disk.isEmpty(), "Disk should have value");
        assertFalse(network.isEmpty(), "Network should have value");
        assertFalse(cpu.isEmpty(), "CPU should have value");

        System.out.println("Name: " + name);
        System.out.println("Memory: " + memory);
        System.out.println("Disk: " + disk);
        System.out.println("Network: " + network);
        System.out.println("CPU: " + cpu);
    }

}