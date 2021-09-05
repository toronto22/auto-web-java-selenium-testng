package bankproject;

import bankproject.helper.SeleniumHelper;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class WebHook {
    protected WebDriver driver;

    @Before
    public void BaseSetup() {
        driver = SeleniumHelper.initDriver();
    }

    @After
    public  void BaseTearDown() {
        driver.quit();
    }

}
