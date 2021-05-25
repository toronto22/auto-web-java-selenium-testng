package Common.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUntil {
    WebDriver driver;
    long timeout = 10;
    By by;

    public WaitUntil(WebDriver driver, By by){
        this.driver = driver;
        this.by = by;
    }

    public WaitUntil(WebDriver driver, By by, long timeOut){
        this.driver = driver;
        this.timeout = timeOut;
        this.by = by;
    }

    public WebElement visible() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        return element;
    }

    public WebElement exist() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        return element;
    }
}
