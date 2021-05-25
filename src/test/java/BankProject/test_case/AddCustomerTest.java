package BankProject.test_case;

import org.junit.Before;
import org.junit.Test;

import BankProject.BaseTest;
import BankProject.page_object_model.AddCustomerPage;
import BankProject.page_object_model.CustomerLoginPage;
import BankProject.page_object_model.ManagerPage;

public class AddCustomerTest extends BaseTest {
    AddCustomerPage addCustomerPage;

    String errorMessage = "Please fill in this field.";

    @Before
    public void ClassSetUp() {
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.goTo();
        addCustomerPage = managerPage.goToAddCustomer();
    }

    @Test
    public void AddACustomer() {
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
    public void AddACustomerWithoutFirstName() {
        String firstName = "";
        String lastName = "Haa";
        String postCode = "123";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyFirstNameValidationMessage(errorMessage);
    }

    @Test
    public void AddACustomerWithoutLastName() {
        String firstName = "W33";
        String lastName = "";
        String postCode = "123";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyLastNameValidationMessage(errorMessage);
    }

    @Test
    public void AddACustomerWithoutPostCode() {
        String firstName = "W33";
        String lastName = "Haa";
        String postCode = "";
        addCustomerPage.addCustomer(firstName, lastName, postCode);
        addCustomerPage.verifyPostCodeValidationMessage(errorMessage);
    }
}
