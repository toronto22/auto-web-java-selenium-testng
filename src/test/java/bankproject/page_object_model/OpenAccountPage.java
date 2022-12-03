package bankproject.page_object_model;

import bankproject.helper.interaction.data_process.DataProcess;
import bankproject.helper.interaction.ui.Selenium;
import bankproject.model.WebUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class OpenAccountPage extends BasePage {
    By customerSelect = By.id("userSelect");
    By currencySelect = By.id("currency");
    By processBtn = By.xpath("//button[text()='Process']");

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.OpenAccount;
        this.selenium = Selenium.Init(driver);
    }

    @Step("Select customer")
    public void selectCustomer(String customer) {
        selenium.select(customerSelect).byText(customer);
    }

    @Step("Select currency")
    public void selectCurrency(String currency) {
        selenium.select(currencySelect).byText(currency);
    }

    @Step("Click on process button")
    public void ClickOnProcessButton() {
        selenium.waitUntil(processBtn).visible().click();
    }

    @Step("Verify customer name validation message")
    public void verifyCustomerNameValidationMessage(String message) {
        String currentMessage = selenium.getValidationMessage(customerSelect);

        assertThat(currentMessage, is(message));
    }

    @Step("Verify currency validation message")
    public void verifyCurrencyValidationMessage(String message) {
        String currentMessage = selenium.getValidationMessage(currencySelect);

        assertThat(message, is(currentMessage));
    }

    @Step("Open account")
    public void openAccount(String customer, String currency) {
        selectCustomer(customer);
        selectCurrency(currency);
        ClickOnProcessButton();
    }

    @Step("Get account number is created in alert")
    public String getAccountNumberIsCreatedInAlert() {
        String alertMessage = driver.switchTo().alert().getText();
        int accountNumber = DataProcess.extractNumber(alertMessage);

        return String.valueOf(accountNumber);
    }

    @Step("Close alert")
    public void closeAlert() {
        driver.switchTo().alert().accept();
    }

    @Step("Verify account is opened and close alert")
    public void verifyAccountIsOpenedAndCloseAlert() {
        String alertMessage = driver.switchTo().alert().getText();
        closeAlert();
        String expectedText = "Account created successfully with account Number";

        assertThat(alertMessage, containsString(expectedText));
    }
}
