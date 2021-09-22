package bankproject.feature;

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
    public void classSetUp() {
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.goTo();
    }

    @Test
    public void should_not_see_the_login_button_in_default() {
        customerLoginPage.verifyLoginButtonDisplayed(false);
    }

    @Test
    public void login_button_should_disappear_when_deselect_your_name()
    {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.selectYourName(yourNameDefaultValue);
        customerLoginPage.verifyLoginButtonDisplayed(false);
    }

    @Test
    public void should_see_the_login_button_when_select_your_name() {
        customerLoginPage.selectYourName(yourName);
        customerLoginPage.verifyLoginButtonDisplayed(true);
    }

    @Test
    public void should_able_to_login_successfully() {
        CustomerAccountPage customerAccountPage = customerLoginPage.login(yourName);
        customerAccountPage.verifyTheCustomerIsLoggedIn(yourName);
    }
}
