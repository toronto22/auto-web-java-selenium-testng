package bankproject.feature.manager;

import bankproject.WebHook;
import bankproject.model.BankConstants;
import bankproject.model.Customer;
import bankproject.page_object_model.AddCustomerPage;
import bankproject.page_object_model.ListCustomersPage;
import bankproject.page_object_model.OpenAccountPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

@Feature("Viewing List Customer Tests")
@Test(description = "Viewing List Customer Tests")
public class ViewingListCustomerTest extends WebHook {
    ListCustomersPage listCustomersPage;

    int numberOfItem = 5;

    @BeforeMethod
    public void classSetUp() {
        listCustomersPage = new ListCustomersPage(driver);
        listCustomersPage.goTo();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Manager view list customer table")
    public void managerViewListCustomerTable() {
        listCustomersPage.goTo();
        listCustomersPage.verifyListCustomerTable(numberOfItem);
    }

    @Severity(SeverityLevel.CRITICAL)
    public void managerCreateNewCustomer() {
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
        String accountNumbers = openAccountPage.getAccountNumberIsCreatedInAlert();
        customer.AccountNumbers = new ArrayList<>();
        customer.AccountNumbers.add(accountNumbers);
        openAccountPage.closeAlert();

        listCustomersPage.goTo();
        listCustomersPage.verifyCustomerIsExisted(customer);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Manager delete the customer")
    public void managerDeleteTheCustomer() {

        Customer customer = new Customer("W33", "Haa", "1234");

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.goTo();
        addCustomerPage.addCustomer(customer);
        addCustomerPage.VerifyAlertCustomerIsAddedAndCloseTheAlert();

        OpenAccountPage openAccountPage = new OpenAccountPage(driver);
        openAccountPage.goTo();
        String customerName = customer.FirstName + " " + customer.LastName;
        openAccountPage.openAccount(customerName, BankConstants.DefaultCurrency);
        String accountNumbers = openAccountPage.getAccountNumberIsCreatedInAlert();
        openAccountPage.closeAlert();

        listCustomersPage.goTo();
        listCustomersPage.deleteCustomer(accountNumbers);
        listCustomersPage.verifyListCustomerTable(numberOfItem);
    }
}
