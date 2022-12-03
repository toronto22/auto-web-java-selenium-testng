package bankproject.page_object_model;

import bankproject.helper.interaction.ui.Selenium;
import bankproject.model.Account;
import bankproject.model.WebUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class CustomerAccountPage extends CustomerPage {
    By customerNameTxt = By.className("fontBig");
    By accountSelect = By.id("accountSelect");
    By accountNumberTxt = By.xpath("//*[@ng-hide='noAccount']/strong[1]");
    By balanceTxt = By.xpath("//*[@ng-hide='noAccount']/strong[2]");
    By currencyTxt = By.xpath("//*[@ng-hide='noAccount']/strong[3]");
    By transactionBtn = By.xpath("//*[@ng-class='btnClass1']");
    By depositBtn = By.xpath("//*[@ng-class='btnClass2']");
    By withdrawBtn = By.xpath("//*[@ng-class='btnClass3']");

    public CustomerAccountPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.CustomerAccount;
        this.selenium = Selenium.Init(driver);
    }

    public DepositPanel depositMoney() {
        selenium.waitUntil(depositBtn).visible().click();
        selenium.sleep(2);

        return new DepositPanel(driver);
    }

    @Step("Verify customer have account")
    public void verifyCustomerHaveAccount(String accountNumber) {
        List<WebElement> accounts = selenium.waitUntil(accountSelect).visible().findElements(By.tagName("option"));
        List<String> accountNumbers = new ArrayList<>();
        for (WebElement account : accounts) {
            accountNumbers.add(account.getText());
        }

        assertThat(accountNumbers, hasItem(accountNumber));
    }

    public WithdrawPanel withdraw() {
        selenium.waitUntil(withdrawBtn).visible().click();
        selenium.sleep(2);

        return new WithdrawPanel(driver);
    }

    @Step("Get balance")
    public int getBalance() {
        String balance = selenium.waitUntil(balanceTxt).visible().getText();

        return Integer.parseInt(balance);
    }

    @Step("Verify balance")
    public void verifyBalance(int expectedBalance) {
        int currentBalance = getBalance();

        assertThat(currentBalance, is(expectedBalance));
    }

    @Step("Get account information")
    public Account getAccountInformation() {
        String customerName = selenium.waitUntil(customerNameTxt).visible().getText();
        String accountNumber = selenium.waitUntil(accountNumberTxt).visible().getText();
        int balance = getBalance();
        String currency = selenium.waitUntil(currencyTxt).visible().getText();

        return new Account(customerName, accountNumber, balance, currency);
    }

    @Step("Select account")
    public void selectAccount(String accountId) {
        selenium.select(accountSelect).byText(accountId);
    }

    public CustomerTransactionsPage transactions() {
        selenium.waitUntil(transactionBtn).visible().click();

        return new CustomerTransactionsPage(driver);
    }

    @Step("Verify the customer is logged in")
    public void verifyTheCustomerIsLoggedIn(String customerName) {
        String userName = selenium.waitUntil(customerNameTxt).visible().getText();

        assertThat(userName, is(customerName));
    }

    @Step("Verify the customer information")
    public void verifyTheCustomerInformation(Account account) {
        Account currentAccount = getAccountInformation();

        assertThat(currentAccount, is(samePropertyValuesAs(account)));
    }

    public static class DepositPanel extends BasePage {
        By amountInput = By.xpath("//input[@ng-model='amount']");
        By depositBtn = By.xpath("//form[@name='myForm']/button");
        By errorMessageTxt = By.className("error");

        public DepositPanel(WebDriver driver) {
            this.driver = driver;
            this.selenium = Selenium.Init(driver);
        }

        @Step("Deposit with amount")
        public void withAmount(int amount) {
            selenium.waitUntil(amountInput).visible().sendKeys(String.valueOf(amount));
            selenium.waitUntil(depositBtn).visible().click();
        }

        @Step("Verify deposit message")
        public void verifyMessage(String expectedMessage) {
            String currentMessage = selenium.waitUntil(errorMessageTxt).visible().getText();

            assertThat(currentMessage, is(expectedMessage));
        }
    }

    public static class WithdrawPanel extends BasePage {
        By amountInput = By.xpath("//input[@ng-model='amount']");
        By withdrawBtn = By.xpath("//form[@name='myForm']/button");
        By errorMessageTxt = By.className("error");

        public WithdrawPanel(WebDriver driver) {
            this.driver = driver;
            this.selenium = Selenium.Init(driver);
        }

        @Step("Withdraw with amount")
        public void withAmount(int amount) {
            selenium.waitUntil(amountInput).visible().sendKeys(String.valueOf(amount));
            selenium.waitUntil(withdrawBtn).visible().click();
        }

        @Step("Verify withdraw message")
        public void verifyMessage(String expectedErrorMessage) {
            String currentErrorMessage = selenium.waitUntil(errorMessageTxt).visible().getText();

            assertThat(currentErrorMessage, is(expectedErrorMessage));
        }
    }

}