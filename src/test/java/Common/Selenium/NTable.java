package Common.Selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NTable {

    WebElement table;
    NSelenium selenium; 

    public NTable(WebDriver driver, By by){
        selenium = NSelenium.Init(driver);
        table = selenium.waitUntil(by).visible();
    }

    public List<List<String>> getTableData(){
        List<List<String>> retults = new ArrayList<List<String>>();
        WebElement tbody = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        for(WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<String>();
            for(WebElement cell : cells){
                rowData.add(cell.getText());
            }
            retults.add(rowData);
        }

        return retults;
    }
}
