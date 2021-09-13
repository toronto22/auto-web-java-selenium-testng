package bankproject;

import bankproject.helper.ConfigurationHelper;
import bankproject.helper.webdriver.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class WebHook {
    protected WebDriver driver;

    @Before
    public void BaseSetup() {
        ConfigurationHelper config = ConfigurationHelper.instance();
        Boolean isGrid = config.IsSeleniumGrid;
        String browser = System.getProperty("browsers");
        if (browser == null) {
            browser = config.Browser;
        }
        if (isGrid) {
            driver = DriverFactory.generateGridDriver(browser);
        } else {
            driver = DriverFactory.generateDriver(browser);
        }
    }

    @After
    public void BaseTearDown() {
        driver.quit();
    }

}
