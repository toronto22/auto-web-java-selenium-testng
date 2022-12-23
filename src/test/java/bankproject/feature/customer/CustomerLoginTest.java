package bankproject.feature.customer;

import bankproject.WebHook;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Customer Access Control Tests")
@Test(description = "Customer Login Tests")
public class CustomerLoginTest extends WebHook {
    CustomerLoginPage customerLoginPage;
    String yourName = BankConstants.CustomerAccountValid.CustomerName;
    String yourNameDefaultValue = "---Your Name---";

    @BeforeMethod
    public void classSetUp() {
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
    }

    @Test(description = "Login button is hidden in default")
    @Severity(SeverityLevel.MINOR)
    @Description("Customer login page hide the login button in default")
    public void loginButtonIsHiddenInDefault() {
        customerLoginPage.verifyLoginButtonIsDisplayed(false);
    }

    @Test(description = "Login button is hidden when deselect your name")
    @Severity(SeverityLevel.MINOR)
    public void loginButtonIsHiddenWhenDeselectYourName() {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.selectYourName(yourNameDefaultValue);
        customerLoginPage.verifyLoginButtonIsDisplayed(false);
    }

    @Test(description = "The login button is shown when select your name")
    @Severity(SeverityLevel.NORMAL)
    public void theLoginButtonIsShownWhenSelectYourName() {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.verifyLoginButtonIsDisplayed(true);
    }

    @Test(description = "Login successfully with valid credential")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("Smoke")
    public void loginSuccessfullyWithValidCredential() {
        CustomerAccountPage customerAccountPage = customerLoginPage.login(yourName);
        customerAccountPage.verifyTheCustomerIsLoggedIn(yourName);
    }
}
