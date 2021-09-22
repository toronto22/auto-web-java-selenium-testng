package bankproject.feature;

import org.junit.Before;
import org.junit.Test;

import bankproject.model.BankConstants;
import bankproject.WebHook;
import bankproject.model.Account;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;

public class CustomerAccountTest extends WebHook {
    CustomerAccountPage customerAccountPage;
    Account account;

    @Before
    public void ClassSetUp() {
        account = BankConstants.CustomerAccountValid;
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerAccountPage = customerLoginPage.login(account.CustomerName);
    }

    @Test
    public void CustomerAccountPageDisplayedCorrectInformation() {
        customerAccountPage.verifyTheCustomerInformation(account);
    }

    @Test
    public void ChooseOtherAccountOfTheCustomer() {
        customerAccountPage.selectAccount(BankConstants.CustomerAccountValidOther.AccountNumber);
        customerAccountPage.verifyTheCustomerInformation(BankConstants.CustomerAccountValidOther);
    }
}
