package bankproject.page_object_model;

import bankproject.helper.interaction.ui.Selenium;
import bankproject.model.Customer;
import bankproject.model.WebUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListCustomersPage extends BasePage {
    By searchCustomerInput = By.xpath("//intpu[@ng-model='searchCustomer']");
    By customerTable = By.tagName("table");

    public ListCustomersPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.ListCustomers;
        this.selenium = Selenium.Init(driver);
    }


    By deleteCustomerLocator(String accountId) {
        return By.xpath("//span[contains(.,'" + accountId + "')]/../..//button");
    }


    @Step("Search customer")
    public void searchCustomer(String value) {
        selenium.waitUntil(searchCustomerInput).visible().sendKeys(value);
    }

    @Step("Get customer information")
    public List<Customer> getCustomerInformation() {
        List<Customer> result = new ArrayList<>();
        List<List<String>> items = selenium.table(customerTable).getTableData();
        for (List<String> item : items) {
            String firstName = item.get(0);
            String lastName = item.get(1);
            String postCode = item.get(2);
            List<String> accountNumbers = Arrays.asList(item.get(3).split(" "));
            Customer customer = new Customer(firstName, lastName, postCode, accountNumbers);
            result.add(customer);
        }
        return result;
    }

    @Step("Verify list customer table")
    public void verifyListCustomerTable(int numberOfItem) {
        int currentNumberOfItem = getCustomerInformation().size();

        assertThat(currentNumberOfItem, is(numberOfItem));
    }

    @Step("Verify customer is existed")
    public void verifyCustomerIsExisted(Customer customer) {
        List<Customer> customers = getCustomerInformation();
        boolean isContains = customers.stream().anyMatch(p -> p.FirstName.equals(customer.FirstName) && p.LastName.equals(customer.LastName) && p.PostCode.equals(customer.PostCode));
        assertThat(isContains, is(true));
    }

    @Step("Delete customer")
    public void deleteCustomer(String accountId) {
        selenium.waitUntil(deleteCustomerLocator(accountId)).visible().click();
    }
}
