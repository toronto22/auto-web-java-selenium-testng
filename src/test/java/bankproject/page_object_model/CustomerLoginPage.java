package bankproject.page_object_model;


import bankproject.helper.interaction.ui.Selenium;
import bankproject.model.WebUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomerLoginPage extends BasePage {
    By yourNameSelect = By.id("userSelect");
    By loginBtn = By.xpath("//button[text()='Login']");

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.CustomerLogin;
        this.selenium = Selenium.Init(driver);
    }

    @Step("Login")
    public CustomerAccountPage login(String yourName) {
        selectYourName(yourName);
        selenium.waitUntil(loginBtn).visible().click();

        return new CustomerAccountPage(driver);
    }

    @Step("Verify login button is displayed")
    public void verifyLoginButtonIsDisplayed(boolean displayed) {
        boolean isDisplayed = selenium.waitUntil(loginBtn).exist().isDisplayed();

        assertThat(displayed, is(isDisplayed));
    }

    @Step("Verify page is active")
    public void verifyPageIsActive() {
        boolean isDisplayed = selenium.waitUntil(yourNameSelect).visible().isDisplayed();

        assertThat(isDisplayed, is(true));
    }

    @Step("Select your name field")
    public void selectYourName(String yourName) {
        selenium.select(yourNameSelect).byText(yourName);
    }
}
