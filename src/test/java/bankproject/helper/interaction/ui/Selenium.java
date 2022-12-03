package bankproject.helper.interaction.ui;

import bankproject.helper.interaction.ui.element.Select;
import bankproject.helper.interaction.ui.element.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Selenium {

    WebDriver driver;

    private Selenium(WebDriver driver) {
        this.driver = driver;
    }

    public static Selenium Init(WebDriver driver) {
        return new Selenium(driver);
    }

    public WaitUntil waitUntil(By by) {

        return new WaitUntil(driver, by);
    }

    public JavaScript javaScript() {
        return new JavaScript(driver);
    }

    public Table table(By by) {
        return new Table(driver, by);
    }

    public String getValidationMessage(By by) {
        WebElement element = waitUntil(by).visible();
        String getValidationMessageScript = "return arguments[0].validationMessage;";
        return javaScript().execute(getValidationMessageScript, element).toString();
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (Exception ignore) {
            //e.printStackTrace();
        }
    }

    public Select select(By by) {
        return new Select(driver, by);
    }

}
