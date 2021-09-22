package bankproject.feature;

import org.junit.Before;
import org.junit.Test;

import bankproject.WebHook;
import bankproject.page_object_model.AddCustomerPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.ManagerPage;

public class AddCustomerTest extends WebHook {
    AddCustomerPage addCustomerPage;

    String errorMessage = "Please fill out this field.";

    @Before
    public void classSetUp() {
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.goTo();
        addCustomerPage = managerPage.goToAddCustomer();
    }

    @Test
    public void should_be_able_to_add_customer() {
        String firstName = "W33";
        String lastName = "Haa";
        String postCode = "123";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.VerifyAlertCustomerIsAddedAndCloseTheAlert();

        CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
        String newCustomerName = firstName + " " + lastName;
        customerLoginPage.login(newCustomerName).verifyTheCustomerIsLoggedIn(newCustomerName);
    }

    @Test
    public void should_be_able_to_validate_first_name_is_empty() {
        String firstName = "";
        String lastName = "Haa";
        String postCode = "123";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyFirstNameValidationMessage(errorMessage);
    }

    @Test
    public void should_be_able_to_validate_last_name_is_empty() {
        String firstName = "W33";
        String lastName = "";
        String postCode = "123";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyLastNameValidationMessage(errorMessage);
    }

    @Test
    public void able_to_validate_post_code_is_empty() {
        String firstName = "W33";
        String lastName = "Haa";
        String postCode = "";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyPostCodeValidationMessage(errorMessage);
    }
}
