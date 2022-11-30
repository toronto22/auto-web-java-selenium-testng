package bankproject.feature.manager;

import bankproject.WebHook;
import bankproject.page_object_model.AddCustomerPage;
import bankproject.page_object_model.CustomerLoginPage;
import bankproject.page_object_model.ManagerPage;
import org.junit.Before;
import org.junit.Test;

public class AddingCustomerTest extends WebHook {
    AddCustomerPage addCustomerPage;

    String errorMessage = "Please fill out this field.";

    @Before
    public void classSetUp() {
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.goTo();
        addCustomerPage = managerPage.goToAddCustomer();
    }

    @Test
    public void manager_add_customer_with_valid_customer_information() {
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
    public void validate_manager_add_customer_with_the_empty_first_name() {
        String firstName = "";
        String lastName = "Haa";
        String postCode = "123";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyFirstNameValidationMessage(errorMessage);
    }

    @Test
    public void validate_manager_add_customer_with_the_empty_last_name() {
        String firstName = "W33";
        String lastName = "";
        String postCode = "123";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyLastNameValidationMessage(errorMessage);
    }

    @Test
    public void validate_manager_add_customer_with_the_empty_post_code() {
        String firstName = "W33";
        String lastName = "Haa";
        String postCode = "";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyPostCodeValidationMessage(errorMessage);
    }
}
