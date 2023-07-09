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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Customer Transactions Tests")
@Test(description = "Customer Transactions Tests")
public class CustomerTransactionsTest extends WebHook {
    CustomerAccountPage customerAccountPage;

    String messageExceedBalanceLimit = "Transaction Failed. You can not withdraw amount more than the balance.";
    String messageDepositSuccessfully = "Deposit Successful";
    String messageWithdrawSuccessfully = "Transaction successful";

    int amount = 200;
    String depositType = "Credit";
    String withdrawType = "Debit";

    @BeforeMethod
    public void classSetUp() {
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerAccountPage = customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Customer deposits money with valid amount")
    public void customerDepositsMoneyWithValidAmount() {
        int currentBalance = customerAccountPage.getBalance();
        customerAccountPage.depositMoney().withAmount(amount);

        int expectedBalance = currentBalance + amount;

        customerAccountPage.depositMoney().verifyMessage(messageDepositSuccessfully);
        customerAccountPage.verifyBalance(expectedBalance);

        customerAccountPage.transactions().verifyLastCustomerTransaction(amount, depositType);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Customer is unable to withdraw the money that exceed the balance")
    public void customerIsUnableToWithdrawTheMoneyThatExceedTheBalance() {
        customerAccountPage.depositMoney().withAmount(amount);

        int currentBalance = customerAccountPage.getBalance();

        int exceedBalanceLimitNumber = currentBalance + 1;

        customerAccountPage.openWithdrawTab();
        customerAccountPage.withdraw().withAmount(exceedBalanceLimitNumber);

        customerAccountPage.withdraw().verifyMessage(messageExceedBalanceLimit);
        customerAccountPage.verifyBalance(currentBalance);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Customer withdraws money with valid amount")
    public void customerWithdrawsMoneyWithValidAmount() {
        customerAccountPage.depositMoney().withAmount(amount);
        int currentBalance = customerAccountPage.getBalance();

        customerAccountPage.openWithdrawTab();
        customerAccountPage.withdraw().withAmount(amount);

        int expectedBalance = currentBalance - amount;
        customerAccountPage.withdraw().verifyMessage(messageWithdrawSuccessfully);
        customerAccountPage.verifyBalance(expectedBalance);
        customerAccountPage.transactions().verifyLastCustomerTransaction(amount, withdrawType);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Customer resets the customer transactions")
    @Description("Customer is able to remove all of the their transactions")
    public void customerResetsTheCustomerTransactions() {
        customerAccountPage.depositMoney().withAmount(amount);
        customerAccountPage.depositMoney().withAmount(amount);
        CustomerTransactionsPage transactionPage = customerAccountPage.transactions();
        transactionPage.reset();
        transactionPage.verifyNumberOfTransactions(0);
    }
}
