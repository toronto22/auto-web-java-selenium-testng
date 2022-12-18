package bankproject.feature.manager;

import bankproject.WebHook;
import bankproject.helper.junit.category.SmokeTests;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.HomePage;
import bankproject.page_object_model.OpenAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Opening Account Tests")
@DisplayName("Opening Account Tests")
@Category(SmokeTests.class)
public class OpeningAccountTest extends WebHook {
    OpenAccountPage openAccountPage;

    String validationMessage = "Please select an item in the list.";


    @Before
    public void classSetUp() {
        openAccountPage = new OpenAccountPage(driver);
        openAccountPage.goTo();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Manager open account for the customer")
    public void managerOpenAccountForTheCustomer() {
        openAccountPage.openAccount(BankConstants.CustomerAccountValid.CustomerName, BankConstants.CustomerAccountValid.Currency);
        String newAccountNumber = openAccountPage.getAccountNumberIsCreatedInAlert();
        openAccountPage.verifyAccountIsOpenedAndCloseAlert();

        HomePage homePage = new HomePage(driver);
        homePage.goTo();
        CustomerLoginPage customerLoginPage = homePage.customerLogin();
        CustomerAccountPage customerAccountPage = customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
        customerAccountPage.verifyCustomerHaveAccount(newAccountNumber);
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Manager is unable to open account with empty customer name")
    @Description("Opening account validation of customer name field")
    public void managerOpenAccountWithEmptyCustomerName() {
        openAccountPage.selectCurrency(BankConstants.CustomerAccountValid.Currency);
        openAccountPage.ClickOnProcessButton();
        openAccountPage.verifyCustomerNameValidationMessage(validationMessage);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Manager is unable to open account without currency")
    @Description("Opening account validation of currency field")
    public void managerIsUnableToOpenAccountWithoutCurrency() {
        openAccountPage.selectCustomer(BankConstants.CustomerAccountValid.CustomerName);
        openAccountPage.ClickOnProcessButton();
        openAccountPage.verifyCurrencyValidationMessage(validationMessage);
    }
}
