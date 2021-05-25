package BankProject.test_case;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import BankProject.BankConstants;
import BankProject.BaseTest;
import BankProject.model.Customer;
import BankProject.page_object_model.AddCustomerPage;
import BankProject.page_object_model.ListCustomersPage;
import BankProject.page_object_model.OpenAccountPage;

public class ListCustomerTest extends BaseTest{
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
