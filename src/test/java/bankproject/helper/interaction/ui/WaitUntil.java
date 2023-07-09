package bankproject.helper.interaction.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUntil {
    WebDriver driver;
    Duration timeout = Duration.ofSeconds(30);
    By by;

    public WaitUntil(WebDriver driver, By by) {
        this.driver = driver;
        this.by = by;
    }

    public WebElement visible() {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement exist() {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void valueTobe(String value) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        wait.until(ExpectedConditions.attributeToBe(by, "value", value));
    }
}
