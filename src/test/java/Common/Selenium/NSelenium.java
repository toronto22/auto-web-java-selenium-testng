package Common.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NSelenium {

    WebDriver driver;

    private NSelenium(WebDriver driver) {
        this.driver = driver;
    }

    public static NSelenium Init(WebDriver driver) {
        return new NSelenium(driver);
    }

    public WaitUntil waitUntil(By by) {
        WaitUntil wait = new WaitUntil(driver, by);

        return wait;
    }

    public JavaScript javaScript() {
        return new JavaScript(driver);
    }

    public NTable table(By by) {
        return new NTable(driver, by);
    }

    public String getValidationMessage(By by) {
        WebElement element = waitUntil(by).visible();
        String getValidationMessageScript = "return arguments[0].validationMessage;";
        return javaScript().execute(getValidationMessageScript, element).toString();
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception ignore) {
            //e.printStackTrace();
        }
    }

    public NSelect select(By by){
        return new NSelect(driver, by);
    }

}
