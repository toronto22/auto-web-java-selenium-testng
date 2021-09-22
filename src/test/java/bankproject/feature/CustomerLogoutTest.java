package bankproject.feature;

import org.junit.Before;
import org.junit.Test;

import bankproject.model.BankConstants;
import bankproject.WebHook;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.CustomerTransactionsPage;

public class CustomerLogoutTest extends WebHook {
    CustomerLoginPage customerLoginPage;

    @Before
    public void ClassSetUp() {
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
    }

    @Test
    public void CustomerLogoutFromAccountPage() {
        CustomerAccountPage customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.goTo();
        customerAccountPage.logout();
        customerLoginPage.verifyPageIsActive();
    }

    @Test
    public void CustomerLogoutFromTransactionsPage() {
        CustomerTransactionsPage customerTransactionPage = new CustomerTransactionsPage(driver);
        customerTransactionPage.goTo();
        customerTransactionPage.logout();
        customerLoginPage.verifyPageIsActive();
    }
}
