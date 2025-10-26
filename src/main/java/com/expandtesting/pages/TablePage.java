package com.expandtesting.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TablePage extends BasePage {

    private final By tableHeader = By.xpath("//table[@class=\"table table-striped\"]/thead/tr/th");
    private final By tableRows = By.xpath("//table[@class=\"table table-striped\"]/tbody/tr");
    private final By tableCells = By.xpath("//table[@class=\"table table-striped\"]/tbody/tr/td");

    public List<String> getTableHeaders(){
        List<WebElement> headerElements = driver.findElements(tableHeader);
        List<String> headers = new ArrayList<>();

        for (WebElement element : headerElements) {
            headers.add(element.getText());
        }
        return headers;
    }

    public List<String> getTableRows(){
        List<WebElement> rowElements = driver.findElements(tableRows);
        List<String> rows = new ArrayList<>();

        for (WebElement element : rowElements) {
            rows.add(element.getText());
        }
        return rows;
    }

    public int tableRowCount(){
        return getTableRows().size();
    }


    public List<WebElement> getTableCells(){
        List<String> allRows = getTableRows();
        int randomIndex = (int) (Math.random() * allRows.size());
        List<WebElement> rowElements = driver.findElements(tableRows);
        List<WebElement> cellElements = rowElements.get(randomIndex).findElements(By.tagName("td"));
        return cellElements;
    }

    public List<String> getRandomCellData(){
        List<WebElement> cellElements = getTableCells();
        List<String> cellData = new ArrayList<>();

        for (WebElement element : cellElements) {
            cellData.add(element.getText());
        }
        return cellData;
    }
}