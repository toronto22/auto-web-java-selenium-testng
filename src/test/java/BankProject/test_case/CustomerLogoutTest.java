package BankProject.test_case;

import org.junit.Before;
import org.junit.Test;

import BankProject.BankConstants;
import BankProject.BaseTest;
import BankProject.page_object_model.CustomerAccountPage;
import BankProject.page_object_model.CustomerLoginPage;
import BankProject.page_object_model.CustomerTransactionsPage;

public class CustomerLogoutTest extends BaseTest{
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
