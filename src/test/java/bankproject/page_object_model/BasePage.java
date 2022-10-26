package bankproject.page_object_model;

import org.openqa.selenium.WebDriver;

import bankproject.interaction.ui.Selenium;

public class BasePage {
    protected WebDriver driver;
    protected String url;
    protected Selenium selenium;

    public void goTo() {
        driver.navigate().to(url);
    }
}
