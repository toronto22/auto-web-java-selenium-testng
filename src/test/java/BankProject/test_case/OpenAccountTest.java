package BankProject.test_case;

import org.junit.Before;
import org.junit.Test;

import BankProject.BankConstants;
import BankProject.BaseTest;
import BankProject.page_object_model.CustomerAccountPage;
import BankProject.page_object_model.CustomerLoginPage;
import BankProject.page_object_model.HomePage;
import BankProject.page_object_model.OpenAccountPage;

public class OpenAccountTest extends BaseTest{
    OpenAccountPage openAccountPage;

    String validationMessage = "Please select an item in the list.";


    @Before
    public void ClassSetUp() {
        openAccountPage = new OpenAccountPage(driver);
        openAccountPage.goTo();
    }

    @Test
    public void OpenAnAccount() {
        openAccountPage.openAccount(BankConstants.CustomerAccountValid.CustomerName, BankConstants.CustomerAccountValid.Currency);
        String newAccountNumber = openAccountPage.getAccountNumberIsCreatedInAlert();
        openAccountPage.verifyAccountIsOpenedAndCloseAlert();

        HomePage homePage = new HomePage(driver);
        homePage.goTo();
        CustomerLoginPage customerLoginPage = homePage.customerLogin();
        CustomerAccountPage customerAccountPage = customerLoginPage.login(BankConstants.CustomerAccountValid.CustomerName);
        customerAccountPage.verifyCutomerHaveAccount(newAccountNumber);
    }


    @Test
    public void OpenAnAccountWithoutSelectCustomerName()
    {
        openAccountPage.selectCurrency(BankConstants.CustomerAccountValid.Currency);
        openAccountPage.ClickOnProcessButton();
        openAccountPage.verifyCustomerNameValidationMessage(validationMessage);
    }

    @Test
    public void OpenAnAccountWithoutSelectCurrency()
    {
        openAccountPage.selectCustomer(BankConstants.CustomerAccountValid.CustomerName);
        openAccountPage.ClickOnProcessButton();
        openAccountPage.verifyCurrencyValidationMessage(validationMessage);
    }
}
