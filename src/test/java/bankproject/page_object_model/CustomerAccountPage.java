package bankproject.page_object_model;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bankproject.model.WebUrl;
import bankproject.model.Account;
import bankproject.interaction.ui.Selenium;

public class CustomerAccountPage extends CustomerPage {
    By customerNameTxt = By.className("fontBig");
    By accountSelect = By.id("accountSelect");
    By accountNumberTxt = By.xpath("//*[@ng-hide='noAccount']/strong[1]");
    By balanceTxt = By.xpath("//*[@ng-hide='noAccount']/strong[2]");
    By currencyTxt = By.xpath("//*[@ng-hide='noAccount']/strong[3]");
    By transactionBtn = By.xpath("//*[@ng-class='btnClass1']");
    By depositBtn = By.xpath("//*[@ng-class='btnClass2']");
    By withdrawlBtn = By.xpath("//*[@ng-class='btnClass3']");

    public CustomerAccountPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.CustomerAccount;
        this.selenium = Selenium.Init(driver);
    }

    public DepositPanel deposit() {
        selenium.waitUntil(depositBtn).visible().click();
        selenium.sleep(2);

        return new DepositPanel(driver);
    }

    public void verifyCutomerHaveAccount(String accountNumber) {
        List<WebElement> accounts = selenium.waitUntil(accountSelect).visible().findElements(By.tagName("option"));
        List<String> accountNumbers = new ArrayList<String>();
        for (WebElement account : accounts) {
            accountNumbers.add(account.getText());
        }

        assertThat(accountNumbers, hasItem(accountNumber));
    }

    public WithdrawlPanel withdrawl() {
        selenium.waitUntil(withdrawlBtn).visible().click();
        selenium.sleep(2);

        return new WithdrawlPanel(driver);
    }

    public int getBalance() {
        String balance = selenium.waitUntil(balanceTxt).visible().getText();

        return Integer.parseInt(balance);
    }

    public void verifyBalance(int expectedBalance) {
        int currentBalance = getBalance();

        assertThat(currentBalance, is(expectedBalance));       
    }

    public Account getAccountInformation() {
        String customerName = selenium.waitUntil(customerNameTxt).visible().getText();
        String accountNumber = selenium.waitUntil(accountNumberTxt).visible().getText();
        int balance = getBalance();
        String currency = selenium.waitUntil(currencyTxt).visible().getText();

        Account result = new Account(customerName, accountNumber, balance, currency);
        return result;
    }

    public void selectAccount(String accountId) {
        selenium.select(accountSelect).byText(accountId);
    }

    public CustomerTransactionsPage transactions() {
        selenium.waitUntil(transactionBtn).visible().click();

        return new CustomerTransactionsPage(driver);
    }

    public void verifyTheCustomerIsLoggedIn(String customerName) {
        String userName = selenium.waitUntil(customerNameTxt).visible().getText();

        assertThat(userName, is(customerName));
    }

    public void verifyTheCustomerInformation(Account account) {
        Account currentAccount = getAccountInformation();

        assertThat(currentAccount, is(samePropertyValuesAs(account)));
    }

    public class DepositPanel extends BasePage {
        By amountInput = By.xpath("//input[@ng-model='amount']");
        By depositBtn = By.xpath("//form[@name='myForm']/button");
        By errorMessageTxt = By.className("error");

        public DepositPanel(WebDriver driver) {
            this.driver = driver;
            this.selenium = Selenium.Init(driver);
        }

        public void withAmount(int amount) {
            selenium.waitUntil(amountInput).visible().sendKeys(String.valueOf(amount));
            selenium.waitUntil(depositBtn).visible().click();
        }

        public void verifyMessage(String expectedMessage) {
            String currentmessage = selenium.waitUntil(errorMessageTxt).visible().getText();

            assertThat(currentmessage, is(expectedMessage));
        }
    }

    public class WithdrawlPanel extends BasePage {
        By amountInput = By.xpath("//input[@ng-model='amount']");
        By withdrawlBtn = By.xpath("//form[@name='myForm']/button");
        By errorMessageTxt = By.className("error");

        public WithdrawlPanel(WebDriver driver) {
            this.driver = driver;
            this.selenium = Selenium.Init(driver);
        }

        public void withAmount(int amount) {
            selenium.waitUntil(amountInput).visible().sendKeys(String.valueOf(amount));
            selenium.waitUntil(withdrawlBtn).visible().click();
        }

        public void verifyMessage(String expectedErrorMessage) {
            String currentErrorMessage = selenium.waitUntil(errorMessageTxt).visible().getText();

            assertThat(currentErrorMessage, is(expectedErrorMessage));
        }
    }

}