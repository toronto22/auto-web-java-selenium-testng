package bankproject.helper.webdriver.remotedriver;

import bankproject.helper.webdriver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class RemoteDriverFirefox implements Driver {
    @Override
    public WebDriver init() {
        WebDriver driver = null;

        FirefoxOptions options = new FirefoxOptions();
        if (config.IsHeadless) {
            options.addArguments("--headless");
        }

        try {
            driver = new RemoteWebDriver(new URL(config.GridHubUri), options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }
}
