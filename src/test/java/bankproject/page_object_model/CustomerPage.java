package bankproject.page_object_model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CustomerPage extends BasePage {
    By homeBtn = By.className("home");
    By logoutBtn = By.className("logout");

    @Step("user logout")
    public CustomerLoginPage logout() {
        selenium.waitUntil(logoutBtn).visible().click();

        return new CustomerLoginPage(driver);
    }
}
