package bankproject.feature.customer;

import bankproject.WebHook;
import bankproject.helper.junit.category.SmokeTests;
import bankproject.model.Account;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Viewing Customer Account Tests")
@DisplayName("Viewing Customer Account Tests")
@Category(SmokeTests.class)
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
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Customer view account information details")
    public void customerViewsAccountInformationDetails() {
        customerAccountPage.verifyTheCustomerInformation(account);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Customer views the other account information details")
    public void customerViewsTheOtherAccountInformationDetails() {
        customerAccountPage.selectAccount(BankConstants.CustomerAccountValidOther.AccountNumber);
        customerAccountPage.verifyTheCustomerInformation(BankConstants.CustomerAccountValidOther);
    }
}
