package bankproject.page_object_model;

import bankproject.interaction.ui.Selenium;
import bankproject.model.WebUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ManagerPage extends BasePage {
    By addCustomerBtn = By.xpath("//*[@ng-class='btnClass1']");
    By openAccountBtn = By.xpath("//*[@ng-class='btnClass2']");
    By CustomersBtn = By.xpath("//*[@ng-class='btnClass3']");

    public ManagerPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.Manager;
        selenium = Selenium.Init(driver);
    }

    public void verifyPageIsActive() {
        boolean isDisplayed = selenium.waitUntil(addCustomerBtn).visible().isDisplayed();

        assertThat(isDisplayed, is(true));
    }

    public AddCustomerPage goToAddCustomer() {
        selenium.waitUntil(addCustomerBtn).visible().click();
        return new AddCustomerPage(driver);
    }

    public OpenAccountPage goToOpenAccount() {
        selenium.waitUntil(openAccountBtn).visible().click();
        return new OpenAccountPage(driver);
    }

    public ListCustomersPage goToCustomers() {
        selenium.waitUntil(CustomersBtn).visible().click();
        return new ListCustomersPage(driver);
    }
}
