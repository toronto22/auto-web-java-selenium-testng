package bankproject.feature.test_case;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import bankproject.model.BankConstants;
import bankproject.WebHook;
import bankproject.model.Customer;
import bankproject.page_object_model.AddCustomerPage;
import bankproject.page_object_model.ListCustomersPage;
import bankproject.page_object_model.OpenAccountPage;

public class ListCustomerTest extends WebHook {
    ListCustomersPage listCustomersPage;

    int numberOfItem = 5;

    @Before
    public void ClassSetUp()
    {
        listCustomersPage = new ListCustomersPage(driver);
        listCustomersPage.goTo();
       // driver.DeleteStorage();
    }

    @Test
    public void ShowListCustomersTable()
    {
        listCustomersPage.goTo();
        listCustomersPage.verifyListCustomerTable(numberOfItem);
    }

    @Test
    public void ShowCustomerInformationOnTableWhenAddANewOne()
    {
        Customer customer = new Customer("W33", "Haa", "1234");

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.goTo();
        addCustomerPage.addCustomer(customer);
        addCustomerPage.VerifyAlertCustomerIsAddedAndCloseTheAlert();

        listCustomersPage.goTo();
        listCustomersPage.verifyListCustomerTable(numberOfItem + 1);

        OpenAccountPage openAccountPage = new OpenAccountPage(driver);
        openAccountPage.goTo();
        String customerName = customer.FirstName + " " + customer.LastName;
        openAccountPage.openAccount(customerName, BankConstants.DefaultCurrency);
        String acountNumbers = openAccountPage.getAccountNumberIsCreatedInAlert();
        customer.AccountNumbers = new ArrayList<String>();
        customer.AccountNumbers.add(acountNumbers);
        openAccountPage.closeAlert();

        listCustomersPage.goTo();
        listCustomersPage.verifyCustomerIsExisted(customer);
    }

    @Test
    public void DeleteCustomerInTheTable() {

        Customer customer = new Customer("W33", "Haa", "1234");

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.goTo();
        addCustomerPage.addCustomer(customer);
        addCustomerPage.VerifyAlertCustomerIsAddedAndCloseTheAlert();

        OpenAccountPage openAccountPage = new OpenAccountPage(driver);
        openAccountPage.goTo();
        String customerName = customer.FirstName + " " + customer.LastName;
        openAccountPage.openAccount(customerName, BankConstants.DefaultCurrency);
        String acountNumbers = openAccountPage.getAccountNumberIsCreatedInAlert();
        openAccountPage.closeAlert();

        listCustomersPage.goTo();
        listCustomersPage.deleteCustomer(acountNumbers);
        listCustomersPage.verifyListCustomerTable(numberOfItem);
    }
}