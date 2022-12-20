package bankproject;

import bankproject.helper.ConfigurationHelper;
import bankproject.helper.junit.category.RegressionTests;
import bankproject.helper.webdriver.DriverFactory;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

@Category(RegressionTests.class)
public class WebHook {

    protected WebDriver driver;
    ConfigurationHelper config = ConfigurationHelper.instance();

    @Before
    @Step("Open browser")
    public void BaseSetup() {
        Boolean isGrid = config.IsSeleniumGrid;
        String browser = config.Browser;

        driver = DriverFactory.generateDriver(browser, isGrid);
    }

    @After
    @Step("Close browser")
    public void BaseTearDown() {
        driver.quit();
    }

}
