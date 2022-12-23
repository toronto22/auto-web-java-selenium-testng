package bankproject.feature.customer;

import bankproject.WebHook;
import bankproject.model.Account;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Viewing Customer Account Tests")
@Test(description = "Viewing Customer Account Tests")
public class ViewingCustomerAccountTest extends WebHook {
    CustomerAccountPage customerAccountPage;
    Account account;

    @BeforeMethod
    public void classSetUp() {
        account = BankConstants.CustomerAccountValid;
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        customerAccountPage = customerLoginPage.login(account.CustomerName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Customer view account information details")
    public void customerViewsAccountInformationDetails() {
        customerAccountPage.verifyTheCustomerInformation(account);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Customer views the other account information details")
    public void customerViewsTheOtherAccountInformationDetails() {
        customerAccountPage.selectAccount(BankConstants.CustomerAccountValidOther.AccountNumber);
        customerAccountPage.verifyTheCustomerInformation(BankConstants.CustomerAccountValidOther);
    }
}
