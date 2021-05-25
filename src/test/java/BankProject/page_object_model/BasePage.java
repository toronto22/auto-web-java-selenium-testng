package BankProject.page_object_model;

import org.openqa.selenium.WebDriver;

import Common.Selenium.NSelenium;

public class BasePage {
    protected WebDriver driver;
    protected String url;
    protected NSelenium selenium;

    public void goTo(){
        driver.navigate().to(url);
    }
}
