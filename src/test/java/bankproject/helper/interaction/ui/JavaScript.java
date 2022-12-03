package bankproject.helper.interaction.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScript {
    WebDriver driver;

    public JavaScript(WebDriver driver) {
        this.driver = driver;
    }

    public Object execute(String script, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, element);
    }
}
