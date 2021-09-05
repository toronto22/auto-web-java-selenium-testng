package bankproject.page_object_model;

import org.openqa.selenium.By;

public class CustomerPage extends BasePage {
    By homeBtn = By.className("home");
    By logoutBtn = By.className("logout");

    public void gotoHomePage() {
        selenium.waitUntil(homeBtn).visible().click();
    }

    public CustomerLoginPage logout() {
        selenium.waitUntil(logoutBtn).visible().click();

        return new CustomerLoginPage(driver);
    }
}
