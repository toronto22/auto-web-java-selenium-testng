package bankproject.feature.customer;

import bankproject.WebHook;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.CustomerTransactionsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

@Feature("Customer Transactions Tests")
@DisplayName("Customer Transactions Tests")
public class CustomerTransactionsTest extends WebHook {
    CustomerAccountPage customerAccountPage;

    String messageExceedBalanceLimit = "Transaction Failed. You can not withdraw amount more than the balance.";
    String messageDepositSuccessfully = "Deposit Successful";
    String messageWithdrawSuccessfully = "Transaction successful";

    int amount = 200;
    String depositType = "Credit";
    String withdrawType = "Debit";

    @Before
    public void classSetUp() {
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerAccountPage = customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Customer deposits money with valid amount")
    public void customerDepositsMoneyWithValidAmount() {
        int currentBalance = customerAccountPage.getBalance();
        customerAccountPage.depositMoney().withAmount(amount);

        int expectedBalance = currentBalance + amount;

        customerAccountPage.depositMoney().verifyMessage(messageDepositSuccessfully);
        customerAccountPage.verifyBalance(expectedBalance);

        customerAccountPage.transactions().verifyLastCustomerTransaction(amount, depositType);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Customer is unable to withdraw the money that exceed the balance")
    public void customerIsUnableToWithdrawTheMoneyThatExceedTheBalance() {
        customerAccountPage.depositMoney().withAmount(amount);

        int currentBalance = customerAccountPage.getBalance();

        int exceedBalanceLimitNumber = currentBalance + 1;
        customerAccountPage.withdraw().withAmount(exceedBalanceLimitNumber);

        customerAccountPage.withdraw().verifyMessage(messageExceedBalanceLimit);
        customerAccountPage.verifyBalance(currentBalance);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Customer withdraws money with valid amount")
    public void customerWithdrawsMoneyWithValidAmount() {
        customerAccountPage.depositMoney().withAmount(amount);
        int currentBalance = customerAccountPage.getBalance();

        customerAccountPage.withdraw().withAmount(amount);
        int expectedBalance = currentBalance - amount;

        customerAccountPage.withdraw().verifyMessage(messageWithdrawSuccessfully);
        customerAccountPage.verifyBalance(expectedBalance);
        customerAccountPage.transactions().verifyLastCustomerTransaction(amount, withdrawType);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Customer resets the customer transactions")
    @Description("Customer is able to remove all of the their transactions")
    public void customerResetsTheCustomerTransactions() {
        customerAccountPage.depositMoney().withAmount(amount);
        customerAccountPage.depositMoney().withAmount(amount);
        CustomerTransactionsPage transactionPage = customerAccountPage.transactions();
        transactionPage.reset();
        transactionPage.verifyNumberOfTransactions(0);
    }
}
