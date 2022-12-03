package bankproject.page_object_model;

import bankproject.helper.interaction.ui.Selenium;
import bankproject.model.CustomerTransaction;
import bankproject.model.WebUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class CustomerTransactionsPage extends CustomerPage {
    By backBtn = By.xpath("//button[text()='Back']");
    By resetBtn = By.xpath("//button[text()='Reset']");
    By startDatePicker = By.id("start");
    By endDatePicker = By.id("end");
    By transactionTable = By.tagName("table");

    public CustomerTransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.CustomerTransaction;
        selenium = Selenium.Init(driver);
    }

    @Step("Verify customer transaction page is active")
    public void verifyPageIsActive() {
        boolean isDisplayed = selenium.waitUntil(transactionTable).visible().isDisplayed();

        assertThat(isDisplayed, is(true));
    }

    @Step("Back to customer account page")
    public CustomerAccountPage backToCustomerAccountPage() {
        selenium.waitUntil(backBtn).visible().click();

        return new CustomerAccountPage(driver);
    }

    @Step("Reset")
    public void reset() {
        selenium.waitUntil(resetBtn).visible().click();
    }

    @Step("Select date")
    public void selectDate(String startDate, String endDate) {
        selenium.waitUntil(startDatePicker).visible().clear();
        selenium.waitUntil(startDatePicker).visible().sendKeys(startDate);
        selenium.waitUntil(endDatePicker).visible().clear();
        selenium.waitUntil(endDatePicker).visible().sendKeys(endDate);
    }

    @Step("Verify information")
    public void verifyInformation(CustomerTransaction customerTransaction) {
        List<CustomerTransaction> transactions = getTransactionInformation();

        assertThat(transactions, hasItem(customerTransaction));
    }

    @Step("Get transaction information")
    public List<CustomerTransaction> getTransactionInformation() {
        List<CustomerTransaction> transactions = new ArrayList<>();
        List<List<String>> data = selenium.table(transactionTable).getTableData();
        for (List<String> row : data) {
            CustomerTransaction transaction = new CustomerTransaction();
            transaction.DateTime = row.get(0);
            transaction.Amount = Integer.parseInt(row.get(1));
            transaction.Type = row.get(2);

            transactions.add(transaction);
        }

        return transactions;
    }

    @Step("Verify number of transaction")
    public void verifyNumberOfTransactions(int expectedNumber) {
        int numberOfTransaction = getTransactionInformation().size();

        assertThat(expectedNumber, is(numberOfTransaction));
    }

    @Step("Verify last customer transaction")
    public void verifyLastCustomerTransaction(int amount, String type) {
        List<CustomerTransaction> transactions = getTransactionInformation();
        CustomerTransaction lastTransaction = transactions.get(transactions.size() - 1);

        assertThat(lastTransaction.Amount, is(amount));
        assertThat(lastTransaction.Type, is(type));
    }
}
