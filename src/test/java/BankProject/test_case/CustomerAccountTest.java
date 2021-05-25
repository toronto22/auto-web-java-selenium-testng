package BankProject.test_case;

import org.junit.Before;
import org.junit.Test;

import BankProject.BankConstants;
import BankProject.BaseTest;
import BankProject.model.Account;
import BankProject.page_object_model.CustomerAccountPage;
import BankProject.page_object_model.CustomerLoginPage;

public class CustomerAccountTest extends BaseTest {
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
