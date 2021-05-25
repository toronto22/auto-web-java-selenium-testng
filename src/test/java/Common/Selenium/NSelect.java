package Common.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NSelect {
    Select select;

    public NSelect(WebDriver driver, By by){
        NSelenium selenium = NSelenium.Init(driver);
        WebElement element = selenium.waitUntil(by).visible();
        select = new Select(element);
    }

    public void byText(String text){
        select.selectByVisibleText(text);
    }

    public void byValue(String value){
        select.selectByValue(value);
    }

    public void byIndex(int index){
        select.selectByIndex(index);
    }
}
