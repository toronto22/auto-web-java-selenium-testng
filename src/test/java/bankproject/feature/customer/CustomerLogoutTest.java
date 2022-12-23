package bankproject.feature.customer;

import bankproject.WebHook;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.CustomerTransactionsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Customer Access Control Tests")
@Test(description = "Customer Logout Tests")
public class CustomerLogoutTest extends WebHook {
    CustomerLoginPage customerLoginPage;

    @BeforeMethod
    public void classSetUp() {
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
    }

    @Test(description = "Customer log out successfully from account page")
    @Severity(SeverityLevel.NORMAL)
    public void customerLogOutSuccessfullyFromAccountPage() {
        CustomerAccountPage customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.goTo();
        customerAccountPage.logout();
        customerLoginPage.verifyPageIsActive();
    }

    @Test(description = "Customer log out successfully from transaction page")
    @Severity(SeverityLevel.NORMAL)
    public void customerLogOutSuccessfullyFromTransactionPage() {
        CustomerTransactionsPage customerTransactionPage = new CustomerTransactionsPage(driver);
        customerTransactionPage.goTo();
        customerTransactionPage.logout();
        customerLoginPage.verifyPageIsActive();
    }
}
