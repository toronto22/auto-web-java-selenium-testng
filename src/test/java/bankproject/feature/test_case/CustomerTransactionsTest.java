package bankproject.feature.test_case;

import org.junit.Before;
import org.junit.Test;

import bankproject.model.BankConstants;
import bankproject.WebHook;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.CustomerTransactionsPage;

public class CustomerTransactionsTest extends WebHook {
    CustomerAccountPage customerAccountPage;

         String messageExceedBalanceLitmit = "Transaction Failed. You can not withdraw amount more than the balance.";
         String messageDepositSuccessfully = "Deposit Successful";
         String messageWithdrawlSuccessfully = "Transaction successful";

         int amount = 200;
         String depositType = "Credit";
         String withdrawlType = "Debit";

        @Before
        public void ClassSetUp()
        {
       
            CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
            customerLoginPage.goTo();
            customerAccountPage = customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
        }

        @Test
        public void DepositMoney()
        {
            int currentBalance = customerAccountPage.getBalance();
            customerAccountPage.deposit().withAmount(amount);

            int expectedBalance = currentBalance + amount;

            customerAccountPage.deposit().verifyMessage(messageDepositSuccessfully);
            customerAccountPage.verifyBalance(expectedBalance);

            customerAccountPage.transactions().VerifyLastCustomerTransaction(amount,depositType);
        }

        @Test
        public void WithDrawlMoneyExceedBalance() {
            customerAccountPage.deposit().withAmount(amount);

            int currentBalance = customerAccountPage.getBalance();
            int exceedBalanceLimitNumber = currentBalance + 1;
            customerAccountPage.withdrawl().withAmount(exceedBalanceLimitNumber);

            customerAccountPage.withdrawl().verifyMessage(messageExceedBalanceLitmit);
            customerAccountPage.verifyBalance(currentBalance);
        }

        @Test
        public void WithDrawlValidAmountOfMoney() {
            customerAccountPage.deposit().withAmount(amount);
            int currentBalance = customerAccountPage.getBalance();

            customerAccountPage.withdrawl().withAmount(amount);
            int expectedBalance = currentBalance - amount;

            customerAccountPage.withdrawl().verifyMessage(messageWithdrawlSuccessfully);
            customerAccountPage.verifyBalance(expectedBalance);
            customerAccountPage.transactions().VerifyLastCustomerTransaction(amount, withdrawlType);
        }

        @Test
        public void ResetCustomerTransactions() {
            customerAccountPage.deposit().withAmount(amount);
            customerAccountPage.deposit().withAmount(amount);
            CustomerTransactionsPage transactionPage = customerAccountPage.transactions();
            transactionPage.reset();
            transactionPage.verifyNumberOfTransactions(0);
        }
}
