package bankproject;

import bankproject.helper.ConfigurationHelper;
import bankproject.helper.webdriver.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class WebHook {
    protected WebDriver driver;
    ConfigurationHelper config = ConfigurationHelper.instance();

    @Before
    public void BaseSetup() {
        Boolean isGrid = config.IsSeleniumGrid;
        String browser = config.Browser;

        driver = DriverFactory.generateDriver(browser, isGrid);
    }

    @After
    public void BaseTearDown() {
        driver.quit();
    }

}
