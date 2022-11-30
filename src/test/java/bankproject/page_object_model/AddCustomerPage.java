package bankproject.page_object_model;

import bankproject.interaction.ui.Selenium;
import bankproject.model.Customer;
import bankproject.model.WebUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class AddCustomerPage extends BasePage {
    By firstNameInput = By.xpath("//input[@ng-model='fName']");
    By lastNameInput = By.xpath("//input[@ng-model='lName']");
    By postCodeInput = By.xpath("//input[@ng-model='postCd']");
    By submitBtn = By.xpath("//button[text()='Add Customer']");

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.AddCustomer;
        this.selenium = Selenium.Init(driver);
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        selenium.waitUntil(firstNameInput).visible().sendKeys(firstName);
        selenium.waitUntil(lastNameInput).visible().sendKeys(lastName);
        selenium.waitUntil(postCodeInput).visible().sendKeys(postCode);
        selenium.waitUntil(submitBtn).visible().click();
    }

    public void addCustomer(Customer customer) {
        addCustomer(customer.FirstName, customer.LastName, customer.PostCode);
    }

    public void verifyFirstNameValidationMessage(String message) {
        String currentMessage = selenium.getValidationMessage(firstNameInput);

        assertThat(currentMessage, equalTo(message));
    }

    public void verifyLastNameValidationMessage(String message) {
        String currentMessage = selenium.getValidationMessage(lastNameInput);

        assertThat(currentMessage, equalTo(message));
    }

    public void verifyPostCodeValidationMessage(String message) {
        String currentMessage = selenium.getValidationMessage(postCodeInput);

        assertThat(currentMessage, equalTo(message));
    }

    public void VerifyAlertCustomerIsAddedAndCloseTheAlert() {
        String addedSuccessfullyMessage = "Customer added successfully with customer id";
        String currentAlertContent = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        assertThat(currentAlertContent, containsString(addedSuccessfullyMessage));
    }

}
