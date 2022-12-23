package bankproject.feature.manager;

import bankproject.WebHook;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.HomePage;
import bankproject.page_object_model.OpenAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Opening Account Tests")
@Test(description = "Opening Account Tests")
public class OpeningAccountTest extends WebHook {
    OpenAccountPage openAccountPage;

    String validationMessage = "Please select an item in the list.";


    @BeforeMethod
    public void classSetUp() {
        openAccountPage = new OpenAccountPage(driver);
        openAccountPage.goTo();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Manager open account for the customer")
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


    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Manager is unable to open account with empty customer name")
    @Description("Opening account validation of customer name field")
    public void managerOpenAccountWithEmptyCustomerName() {
        openAccountPage.selectCurrency(BankConstants.CustomerAccountValid.Currency);
        openAccountPage.ClickOnProcessButton();
        openAccountPage.verifyCustomerNameValidationMessage(validationMessage);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Manager is unable to open account without currency")
    @Description("Opening account validation of currency field")
    public void managerIsUnableToOpenAccountWithoutCurrency() {
        openAccountPage.selectCustomer(BankConstants.CustomerAccountValid.CustomerName);
        openAccountPage.ClickOnProcessButton();
        openAccountPage.verifyCurrencyValidationMessage(validationMessage);
    }
}
