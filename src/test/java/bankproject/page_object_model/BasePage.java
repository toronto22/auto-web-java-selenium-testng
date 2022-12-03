package bankproject.page_object_model;

import bankproject.helper.interaction.ui.Selenium;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    protected String url;
    protected Selenium selenium;

    public void goTo() {
        driver.navigate().to(url);
    }
}
