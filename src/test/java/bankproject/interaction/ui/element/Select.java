package bankproject.interaction.ui.element;

import bankproject.interaction.ui.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Select {
    org.openqa.selenium.support.ui.Select select;

    public Select(WebDriver driver, By by) {
        Selenium selenium = Selenium.Init(driver);
        WebElement element = selenium.waitUntil(by).visible();
        select = new org.openqa.selenium.support.ui.Select(element);
    }

    public void byText(String text) {
        select.selectByVisibleText(text);
    }

    public void byValue(String value) {
        select.selectByValue(value);
    }

    public void byIndex(int index) {
        select.selectByIndex(index);
    }
}
