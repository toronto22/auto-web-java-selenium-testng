package BankProject.page_object_model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import BankProject.WebUrl;
import Common.Selenium.NSelenium;

public class CustomerLoginPage extends BasePage {
    By yourNameSelect = By.id("userSelect");
    By loginBtn = By.xpath("//button[text()='Login']");

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.CustomerLogin;
        this.selenium = NSelenium.Init(driver);
    }

    public CustomerAccountPage login(String yourName) {
        selectYourName(yourName);
        selenium.waitUntil(loginBtn).visible().click();

        return new CustomerAccountPage(driver);
    }

    public void verifyLoginButtonDisplayed(boolean displayed) {
        boolean isDisplayed = selenium.waitUntil(loginBtn).exist().isDisplayed();

        assertThat(displayed,is(isDisplayed));
    }

    public void verifyPageIsActive() {
        boolean isDisplayed = selenium.waitUntil(yourNameSelect).visible().isDisplayed();

        assertThat(isDisplayed,is(true));
    }

    public void selectYourName(String yourName) {
        selenium.select(yourNameSelect).byText(yourName);
    }
}
