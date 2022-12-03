package bankproject.helper.webdriver.remotedriver;

import bankproject.helper.webdriver.DriverGenerator;
import bankproject.helper.webdriver.localdriver.LocalDriverChrome;
import bankproject.helper.webdriver.localdriver.LocalDriverFirefox;
import org.openqa.selenium.WebDriver;

public class RemoteDriverFactory implements DriverGenerator {
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
