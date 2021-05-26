package BankProject;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
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
