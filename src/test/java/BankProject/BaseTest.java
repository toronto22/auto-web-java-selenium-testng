package BankProject;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void oneTimeSetup() {
         driver = SeleniumHelper.initDriver();
    }

    @After
    public  void OneTimeTearDown() {
        driver.quit();
    }

}
