package bankproject.feature.customer;

import bankproject.WebHook;
import bankproject.helper.junit.category.SmokeTests;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.CustomerTransactionsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Customer Access Control Tests")
@DisplayName("Customer Logout Tests")
@Category(SmokeTests.class)
public class CustomerLogoutTest extends WebHook {
    CustomerLoginPage customerLoginPage;

    @Before
    public void classSetUp() {
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Customer log out successfully from account page")
    public void customerLogOutSuccessfullyFromAccountPage() {
        CustomerAccountPage customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.goTo();
        customerAccountPage.logout();
        customerLoginPage.verifyPageIsActive();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Customer log out successfully from transaction page")
    public void customerLogOutSuccessfullyFromTransactionPage() {
        CustomerTransactionsPage customerTransactionPage = new CustomerTransactionsPage(driver);
        customerTransactionPage.goTo();
        customerTransactionPage.logout();
        customerLoginPage.verifyPageIsActive();
    }
}
