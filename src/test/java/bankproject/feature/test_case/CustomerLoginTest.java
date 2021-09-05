package bankproject.feature.test_case;

import org.junit.Before;
import org.junit.Test;

import bankproject.model.BankConstants;
import bankproject.WebHook;
import bankproject.page_object_model.CustomerAccountPage;
import bankproject.page_object_model.CustomerLoginPage;

public class CustomerLoginTest extends WebHook {
    CustomerLoginPage customerLoginPage;
    String yourName = BankConstants.CustomerAccountValid.CustomerName;
    String yourNameDefaultValue = "---Your Name---";
    
    @Before
    public void ClassSetUp() {
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
    }

    @Test
    public void LoginButtonDoNotDisplayedWhenDoNotChooseYourName() {
        customerLoginPage.verifyLoginButtonDisplayed(false);
    }

    @Test
    public void LoginButtonDisapearedWhenDeselectYourName()
    {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.selectYourName(yourNameDefaultValue);
        customerLoginPage.verifyLoginButtonDisplayed(false);
    }

    @Test
    public void LoginButtonDisplayedWhenSelectYourName() {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.verifyLoginButtonDisplayed(true);
    }

    @Test
    public void Login_Successfully() {
        CustomerAccountPage customerAccountPage = customerLoginPage.login(yourName);
        customerAccountPage.verifyTheCustomerIsLoggedIn(yourName);
    }
}
