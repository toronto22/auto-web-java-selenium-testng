package bankproject.feature.customer;

import bankproject.WebHook;
import bankproject.model.BankConstants;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Before;
import org.junit.Test;

@Feature("Customer Access Control Tests")
@DisplayName("Customer Login Tests")
public class CustomerLoginTest extends WebHook {
    CustomerLoginPage customerLoginPage;
    String yourName = BankConstants.CustomerAccountValid.CustomerName;
    String yourNameDefaultValue = "---Your Name---";

    @Before
    public void classSetUp() {
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Login button is hidden in default")
    @Description("Customer login page hide the login button in default")
    public void loginButtonIsHiddenInDefault() {
        customerLoginPage.verifyLoginButtonIsDisplayed(false);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Login button is hidden when deselect your name")
    public void loginButtonIsHiddenWhenDeselectYourName() {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.selectYourName(yourNameDefaultValue);
        customerLoginPage.verifyLoginButtonIsDisplayed(false);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("The login button is shown when select your name")
    public void theLoginButtonIsShownWhenSelectYourName() {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.verifyLoginButtonIsDisplayed(true);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Login successfully with valid credential")
    @Tag("Smoke")
    public void loginSuccessfullyWithValidCredential() {
        CustomerAccountPage customerAccountPage = customerLoginPage.login(yourName);
        customerAccountPage.verifyTheCustomerIsLoggedIn(yourName);
    }
}
