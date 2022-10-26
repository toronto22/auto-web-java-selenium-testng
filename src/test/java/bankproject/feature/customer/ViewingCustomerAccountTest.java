package bankproject.feature.customer;

import org.junit.Before;
import org.junit.Test;

import bankproject.model.BankConstants;
import bankproject.WebHook;
import bankproject.model.Account;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;

public class ViewingCustomerAccountTest extends WebHook {
    CustomerAccountPage customerAccountPage;
    Account account;

    @Before
    public void classSetUp() {
        account = BankConstants.CustomerAccountValid;
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerAccountPage = customerLoginPage.login(account.CustomerName);
    }

    @Test
    public void customer_view_account_information_details() {
        customerAccountPage.verifyTheCustomerInformation(account);
    }

    @Test
    public void customer_the_other_account_information_details() {
        customerAccountPage.selectAccount(BankConstants.CustomerAccountValidOther.AccountNumber);
        customerAccountPage.verifyTheCustomerInformation(BankConstants.CustomerAccountValidOther);
    }
}
