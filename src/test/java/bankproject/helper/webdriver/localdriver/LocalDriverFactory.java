package bankproject.helper.webdriver.localdriver;

import bankproject.helper.webdriver.DriverGenerator;
import org.openqa.selenium.WebDriver;

public class LocalDriverFactory implements DriverGenerator {
    public WebDriver generateDriver(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome":
                return new LocalDriverChrome().init();
            case "firefox":
                return new LocalDriverFirefox().init();
            default:
                return null;
        }
    }
}
