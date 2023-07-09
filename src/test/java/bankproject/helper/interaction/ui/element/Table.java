package bankproject.helper.interaction.ui.element;

import bankproject.helper.interaction.ui.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table {

    WebElement table;
    Selenium selenium;

    public Table(WebDriver driver, By by) {
        selenium = Selenium.Init(driver);
        table = selenium.waitUntil(by).visible();
    }

    public List<List<String>> getTableData() {
        List<List<String>> results = new ArrayList<>();
        WebElement tbody = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            results.add(rowData);
        }

        return results;
    }
}
