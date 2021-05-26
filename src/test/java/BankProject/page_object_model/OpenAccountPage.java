package BankProject.page_object_model;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BankProject.WebUrl;
import Common.DataProcess.NData;
import Common.Selenium.NSelenium;

public class OpenAccountPage extends BasePage {
    By customerSelect = By.id("userSelect");
    By currencySelect = By.id("currency");
    By processBtn = By.xpath("//button[text()='Process']");

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.OpenAccount;
        this.selenium = NSelenium.Init(driver);
    }

    public void selectCustomer(String customer) {
        selenium.select(customerSelect).byText(customer);
    }

    public void selectCurrency(String currency) {
        selenium.select(currencySelect).byText(currency);
    }

    public void ClickOnProcessButton() {
        selenium.waitUntil(processBtn).visible().click();
    }

    public void verifyCustomerNameValidationMessage(String message) {
        String currentMessage = selenium.getValidationMessage(customerSelect);

        assertThat(currentMessage, is(message));
    }

    public void verifyCurrencyValidationMessage(String message) {
        String currentMessage = selenium.getValidationMessage(currencySelect);

        assertThat(message, is(currentMessage));
    }

    public void openAccount(String customer, String currency) {
        selectCustomer(customer);
        selectCurrency(currency);
        ClickOnProcessButton();
    }

    public String getAccountNumberIsCreatedInAlert() {
        String alertMessage = driver.switchTo().alert().getText();
        int accountNumber = NData.extractNumber(alertMessage);

        return String.valueOf(accountNumber);
    }

    public void closeAlert() {
        driver.switchTo().alert().accept();
    }

    public void verifyAccountIsOpenedAndCloseAlert() {
        String alertMessage = driver.switchTo().alert().getText();
        closeAlert();
        String expectedText = "Account created successfully with account Number";

        assertThat(alertMessage, containsString(expectedText));
    }
}
