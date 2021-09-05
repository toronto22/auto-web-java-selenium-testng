package bankproject.page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import bankproject.model.WebUrl;
import bankproject.model.Customer;
import bankproject.interaction.ui.Selenium;

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
        String currentmessage = selenium.getValidationMessage(firstNameInput);

        assertThat(currentmessage, equalTo(message));
    }

    public void verifyLastNameValidationMessage(String message) {
        String currentmessage = selenium.getValidationMessage(lastNameInput);

        assertThat(currentmessage, equalTo(message));
    }

    public void verifyPostCodeValidationMessage(String message) {
        String currentmessage = selenium.getValidationMessage(postCodeInput);

        assertThat(currentmessage, equalTo(message));
    }

    public void VerifyAlertCustomerIsAddedAndCloseTheAlert() {
        String addedsuccessfullyMessage = "Customer added successfully with customer id";
        String currentAlertContent = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        assertThat(currentAlertContent,containsString(addedsuccessfullyMessage));
    }

}
