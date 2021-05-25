package BankProject.page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BankProject.WebUrl;
import Common.Selenium.NSelenium;

public class HomePage extends BasePage {
    By customerLoginBtn = By.xpath("//button[text()='Customer Login']");
    By bankManagerLoginBtn = By.xpath("//button[text()='Bank Manager Login']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.url = WebUrl.Home;
        this.selenium = NSelenium.Init(driver);
    }

    public CustomerLoginPage customerLogin() {
        selenium.waitUntil(customerLoginBtn).visible().click();

        return new CustomerLoginPage(driver);
    }

    public ManagerPage bankManagerLoginPage() {
        selenium.waitUntil(bankManagerLoginBtn).visible().click();

        return new ManagerPage(driver);
    }
}
