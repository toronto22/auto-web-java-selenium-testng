package bankproject.page_object_model;

import bankproject.helper.interaction.ui.Selenium;
import bankproject.model.WebUrl;
import io.qameta.allure.Step;
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

    @Step("Verify page is active")
    public void verifyPageIsActive() {
        boolean isDisplayed = selenium.waitUntil(addCustomerBtn).visible().isDisplayed();

        assertThat(isDisplayed, is(true));
    }

    @Step("Go to add customer page")
    public AddCustomerPage goToAddCustomerPage() {
        selenium.waitUntil(addCustomerBtn).visible().click();
        return new AddCustomerPage(driver);
    }
}
